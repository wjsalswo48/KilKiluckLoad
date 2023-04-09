package mz.sixsense.board.file.service.impl;

import lombok.RequiredArgsConstructor;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.file.entity.FreeBoardFile;
import mz.sixsense.board.file.repository.FreeBoardFileRepository;
import mz.sixsense.board.file.service.FreeBoardFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FreeBoardFileServiceImpl implements FreeBoardFileService {

    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    private FreeBoardFileRepository freeBoardFileRepo;

    @Override
    public Long saveFile(MultipartFile files, FreeBoard freeBoard) throws IOException {
        if (files.isEmpty()) {
            return null;
        }
        String originName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        //확장자
        String extension = originName.substring(originName.lastIndexOf("."));
        String saveName = uuid + extension;
        String savePath = fileDir + saveName;

        FreeBoardFile freeBoardFile = FreeBoardFile.builder()
                                        .originName(originName)
                                        .saveName(saveName)
                                        .savePath(savePath)
                                        .build();

        freeBoardFile.setFreeBoard(freeBoard);
        File savePath2 = new File(savePath);
        if(savePath2.exists() == false) {
        	System.out.println("이런 경로가없군요~");
        	savePath2.mkdirs();
        }
        files.transferTo(savePath2);

        FreeBoardFile saveFile = freeBoardFileRepo.save(freeBoardFile);

        return saveFile.getFileID();
    }

    @Override
    public List<FreeBoardFile> view() {
        return freeBoardFileRepo.findAll();
    }

    @Override
    public List<FreeBoardFile> fileAllView(Long freeBID) {
        return freeBoardFileRepo.selectImageAllViewQuery(freeBID);
    }

    @Override
    public FreeBoardFile downloadFile(Long fileID) {
        return freeBoardFileRepo.findById(fileID).orElse(null);
    }
}
