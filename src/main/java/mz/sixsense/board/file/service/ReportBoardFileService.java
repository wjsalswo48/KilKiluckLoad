package mz.sixsense.board.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.entity.ReportBoard;
import mz.sixsense.board.file.entity.FreeBoardFile;
import mz.sixsense.board.file.entity.ReportBoardFile;

public interface ReportBoardFileService {

	    Long saveFile(MultipartFile files, ReportBoard reportBoard) throws IOException;

	    
//	    public void deleteFile(ReportBoard reportboard);
	    
	    List<ReportBoardFile> fileAllView(Long reportFileID);

	    ReportBoardFile downloadFile(@PathVariable("fileID") Long fileID);

}
