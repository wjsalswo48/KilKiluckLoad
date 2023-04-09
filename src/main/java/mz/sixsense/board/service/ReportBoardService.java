package mz.sixsense.board.service;

import mz.sixsense.board.Search;
import mz.sixsense.board.entity.ReportBoard;
import mz.sixsense.member.entity.Members;
import org.springframework.data.domain.Page;


public interface ReportBoardService {

    void insertBoard(ReportBoard reportboard);

    void updateBoard(ReportBoard reportboard);

    void deleteBoard(ReportBoard reportboard);

    ReportBoard getReportBoard(ReportBoard reportboard);

    Page<ReportBoard> getReportBoardList(Search search, int Page);

    Page<ReportBoard> myreportBoardList(Search search, Members member, int page);

    //글 작성 처리
//	  void write(ReportBoardFile reportboardfile, MultipartFile file, ReportBoard reportBoard) throws Exception;
}
