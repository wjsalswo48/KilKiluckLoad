package mz.sixsense.board.file.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import mz.sixsense.board.entity.ReportBoard;
import mz.sixsense.board.file.entity.ReportBoardFile;
import mz.sixsense.board.file.repository.ReportBoardFileRepository;
import mz.sixsense.board.file.service.ReportBoardFileService;

@Service
@RequiredArgsConstructor
public class ReportBoardFileServiceImpl implements ReportBoardFileService{

    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    private ReportBoardFileRepository reportBoardFileRepo;

    @Override
    public Long saveFile(MultipartFile files, ReportBoard reportBoard) throws IOException {
        if (files.isEmpty()) {
            return null;
        }
        String originName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        //확장자
        String extension = originName.substring(originName.lastIndexOf("."));
        String saveName = uuid + extension;
        String savPath = fileDir + saveName;

        ReportBoardFile reportBoardFile = ReportBoardFile.builder()
                                        .originName(originName)
                                        .saveName(saveName)
                                        .savPath(savPath)
                                        .build();

        reportBoardFile.setReportBoard(reportBoard);
        files.transferTo(new java.io.File(savPath));

        ReportBoardFile saveFile = reportBoardFileRepo.save(reportBoardFile);

        return saveFile.getFileID();
    }

    @Override
    public List<ReportBoardFile> fileAllView(Long reportFileID) {
        return reportBoardFileRepo.selectImageAllViewQuery(reportFileID);
    }

    @Override
    public ReportBoardFile downloadFile(Long reportID) {
        return reportBoardFileRepo.findById(reportID).orElse(null);
    }
//    
//	@Override
//	public void deleteFile(ReportBoard reportboard) {
//		reportBoardFileRepo.deleteFile(String.valueOf(reportboard.getReportID()));
//	}
    
}
