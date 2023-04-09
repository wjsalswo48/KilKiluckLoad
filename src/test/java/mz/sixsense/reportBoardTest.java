//package mz.sixsense;
//
//import mz.sixsense.board.entity.FreeBoard;
//import mz.sixsense.board.entity.ReportBoard;
//import mz.sixsense.board.file.repository.FreeBoardFileRepository;
//import mz.sixsense.board.file.repository.ReportBoardFileRepository;
//import mz.sixsense.board.repository.FreeBoardRepository;
//import mz.sixsense.enums.Role;
//import mz.sixsense.member.entity.Members;
//import mz.sixsense.member.repository.MemberRepository;
//import mz.sixsense.reportboard.persistence.ReportBoardRepository;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class reportBoardTest {
//
//    @Autowired
//    private MemberRepository memberRepo;
//
//    @Autowired
//    private ReportBoardRepository reportBoardRepo;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    @Autowired
//    private ReportBoardFileRepository reportBoardFileRepository;
//
//  // @Test
//    public void testInsert() {
//        Members member1 = new Members();
//        member1.setMemberID("member");
//        member1.setPassword(encoder.encode("member"));
//        member1.setBirthDate("19970131");
//        member1.setCreateDate(new Date());
//        member1.setEmail("a@b.com");
//        member1.setGender("여자");
//        member1.setName("조미연");
//        member1.setNickName("미연");
//        member1.setQrFlag(0);
//        member1.setRole(Role.ROLE_MEMBER_PHONE);
//        member1.setTotalPoint(20180502L);
//        memberRepo.save(member1);
//
////        Members member2 = new Members();
////        member2.setMemberID("admin");
////        member2.setPassword(encoder.encode("admin"));
////        member2.setBirthDate("19930516");
////        member2.setCreateDate(new Date());
////        member2.setEmail("c@b.com");
////        member2.setGender("여자");
////        member2.setName("이지은");
////        member2.setNickName("아이유");
////        member2.setQrFlag(0);
////        member2.setRole(Role.ROLE_ADMIN);
////        member2.setTotalPoint(20080918L);
////        memberRepo.save(member2);
////
////        Members member3 = new Members();
////        member3.setMemberID("fakeAuthenticated");
////        member3.setPassword(encoder.encode("sixsense"));
////        member3.setBirthDate("19970131");
////        member3.setCreateDate(new Date());
////        member3.setEmail("a@b.com");
////        member3.setGender("여자");
////        member3.setName("이름2");
////        member3.setNickName("닉네임2");
////        member3.setQrFlag(0);
////        member3.setRole(Role.ROLE_MEMBER);
////        member3.setTotalPoint(0L);
////        memberRepo.save(member3);
//
//        for (int i = 1; i < 101; i++) {
//            ReportBoard reportBoard = new ReportBoard();
//            reportBoard.setMemberID(member1);
//            reportBoard.setTitle(member1.getName() + "의 " + i + "번째 " + "게시글");
//            reportBoard.setContent(member1.getName() + "의 " + i + "번째 " + "게시글");
//            reportBoard.setBoardCategory("신고");
//            reportBoard.setReportStatus("처리중");
//            reportBoardRepo.save(reportBoard);
//        }
//
////        for (int i = 1; i < 101; i++) {
////            FreeBoard freeBoard = new FreeBoard();
////            freeBoard.setMemberID(member2);
////            freeBoard.setTitle(member2.getName() + "의 " + i + "번째 " + "게시글");
////            freeBoard.setContent(member2.getName() + "의 " + i + "번째 " + "게시글");
////            freeBoard.setBoardCategory("기타");
////            freeBoardRepo.save(freeBoard);
////        }
//    }
//}   //class
