package mz.sixsense.board.file.service;

import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.file.entity.FreeBoardFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FreeBoardFileService {

    Long saveFile(MultipartFile files, FreeBoard freeBoard) throws IOException;

    List<FreeBoardFile> view();

    List<FreeBoardFile> fileAllView(Long freeBID);

    FreeBoardFile downloadFile(@PathVariable("fileID") Long fileID);

}
