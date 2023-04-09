package mz.sixsense.board.service.impl;

import com.querydsl.core.BooleanBuilder;
import mz.sixsense.board.Search;
import mz.sixsense.board.entity.QReportBoard;
import mz.sixsense.board.entity.ReportBoard;
import mz.sixsense.board.file.repository.ReportBoardFileRepository;
import mz.sixsense.board.service.ReportBoardService;
import mz.sixsense.member.entity.Members;
import mz.sixsense.reportboard.persistence.ReportBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReportBoardServiceImpl implements ReportBoardService {


    @Autowired
    private ReportBoardRepository reportboardRepo;

    @Autowired
    private ReportBoardFileRepository reportboardfilerepository;

    @Override
    public void insertBoard(ReportBoard reportboard) {
        reportboardRepo.save(reportboard);
    }

    @Override
    public void updateBoard(ReportBoard reportboard) {
        ReportBoard findBoard = reportboardRepo.findById(reportboard.getReportID()).get();

        findBoard.setReportStatus(reportboard.getReportStatus());
        findBoard.setTitle(reportboard.getTitle());
        findBoard.setContent(reportboard.getContent());
        reportboardRepo.save(findBoard);
    }

    @Override
    public void deleteBoard(ReportBoard reportboard) {
        reportboardRepo.deleteById(reportboard.getReportID());
    }

    @Override
    public ReportBoard getReportBoard(ReportBoard reportboard) {
        return reportboardRepo.findById(reportboard.getReportID()).get();
    }

    @Override
    public Page<ReportBoard> getReportBoardList(Search search, int page) {
        BooleanBuilder builder = new BooleanBuilder();

        QReportBoard qReportBoard = QReportBoard.reportBoard;

        if (search.getSearchCondition().equals("TITLE")) {
            builder.and(qReportBoard.title.like("%" + search.getSearchKeyword() + "%"));
        } else if (search.getSearchCondition().equals("CONTENT")) {
            builder.and(qReportBoard.content.like("%" + search.getSearchKeyword() + "%"));
        }
        builder.and(qReportBoard.boardCategory.like("%" + search.getSearchCategory() + "%"));

        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "reportID");

        return reportboardRepo.findAll(builder, pageable);
    }

    @Override
    public Page<ReportBoard> myreportBoardList(Search search, Members member, int page) {
        BooleanBuilder builder = new BooleanBuilder();

        QReportBoard qReportBoard = QReportBoard.reportBoard;

        builder.and(qReportBoard.memberID.eq(member));
        if (search.getSearchCondition().equals("TITLE")) {
            builder.and(qReportBoard.title.like("%" + search.getSearchKeyword() + "%"));
        }
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "reportID");

        return reportboardRepo.findAll(builder, pageable);
    }

//    public void write(ReportBoardFile reportboardfile, MultipartFile file, ReportBoard reportBoard) throws Exception{
//		  //경로지정
//		  String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\reportboardimages";
//		  
//		  UUID uuid = UUID.randomUUID(); //랜덤이름을 만들어줌
//
//		  reportboardfile.setOriginName(file.getOriginalFilename());
//		  
//		  String fileName = uuid + "_" + file.getOriginalFilename(); // 랜덤이름 + _ + 원래 파일이름
//		  
//		  //File클래스로 파일을 생성해 줄건데, projectPath경로에 넣어줄것이고 이름은name으로 담긴다
//		  File saveFile = new File(projectPath, fileName) ;
//		  
//		  file.transferTo(saveFile);
//
//		  reportboardfile.setSaveName(fileName);
//		  reportboardfile.setSavPath("/reportboardimages/" + fileName);
//		  
//		  //System.out.println("================ reportBoard" + reportBoard.getMemberID());
//		  reportboardfile.setReportBoard(reportBoard);
//		  
//		  reportboardfilerepository.save(reportboardfile);
//				  
//	  }
}
