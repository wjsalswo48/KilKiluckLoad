//package mz.sixsense;
//
//import mz.sixsense.board.entity.ReportBoard;
//import mz.sixsense.board.file.repository.FreeBoardFileRepository;
//import mz.sixsense.board.repository.FreeBoardRepository;
//import mz.sixsense.enums.Role;
//import mz.sixsense.member.entity.Members;
//import mz.sixsense.member.repository.MemberRepository;
//import mz.sixsense.reportboard.persistence.ReportBoardRepository;
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
//public class freeBoardTest {
//
//    @Autowired
//    private MemberRepository memberRepo;
//
//    @Autowired
//    private FreeBoardRepository freeBoardRepo;
//
//    @Autowired
//    private ReportBoardRepository reportBoardRepo;
//
//    @Autowired
//    private PasswordEncoder encoder;
//    @Autowired
//    private FreeBoardFileRepository freeBoardFileRepository;
//
//    @Test
//    public void testInsert() {
//        Members member1 = new Members();
//        member1.setMemberID("b");
//        member1.setPassword(encoder.encode("b"));
//        member1.setBirthDate("19970131");
//        member1.setCreateDate(new Date());
//        member1.setEmail("a@b.com");
//        member1.setGender("여자");
//        member1.setName("조미연");
//        member1.setNickName("메가존");
//        member1.setQrFlag(0);
//        member1.setRole(Role.ROLE_MEMBER);
//        member1.setTotalPoint(20180502L);
//        memberRepo.save(member1);
//
//        Members member2 = new Members();
//        member2.setMemberID("a");
//        member2.setPassword(encoder.encode("a"));
//        member2.setBirthDate("19930516");
//        member2.setCreateDate(new Date());
//        member2.setEmail("c@b.com");
//        member2.setGender("여자");
//        member2.setName("이지은");
//        member2.setNickName("관리자");
//        member2.setQrFlag(0);
//        member2.setRole(Role.ROLE_ADMIN);
//        member2.setTotalPoint(20080918L);
//        memberRepo.save(member2);
//
//        Members member3 = new Members();
//        member3.setMemberID("c");
//        member3.setPassword(encoder.encode("c"));
//        member3.setBirthDate("19970131");
//        member3.setCreateDate(new Date());
//        member3.setEmail("a@b.com");
//        member3.setGender("남자");
//        member3.setName("김지훈");
//        member3.setNickName("오늘뭐먹지");
//        member3.setQrFlag(0);
//        member3.setRole(Role.ROLE_MEMBER_PHONE);
//        member3.setTotalPoint(20180502L);
//        memberRepo.save(member3);
//
//        Members member4 = new Members();
//        member4.setMemberID("d");
//        member4.setPassword(encoder.encode("d"));
//        member4.setBirthDate("19970131");
//        member4.setCreateDate(new Date());
//        member4.setEmail("a@b.com");
//        member4.setGender("남자");
//        member4.setName("윤기영");
//        member4.setNickName("식스센스");
//        member4.setQrFlag(0);
//        member4.setRole(Role.ROLE_MEMBER_PHONE);
//        member4.setTotalPoint(20180502L);
//        memberRepo.save(member4);
//
//
//        Members member5 = new Members();
//        member5.setMemberID("e");
//        member5.setPassword(encoder.encode("e"));
//        member5.setBirthDate("19970131");
//        member5.setCreateDate(new Date());
//        member5.setEmail("a@b.com");
//        member5.setGender("여자");
//        member5.setName("김석림");
//        member5.setNickName("메가월드");
//        member5.setQrFlag(0);
//        member5.setRole(Role.ROLE_MEMBER_PHONE);
//        member5.setTotalPoint(20180502L);
//        memberRepo.save(member5);
//
//        Members member6 = new Members();
//        member6.setMemberID("f");
//        member6.setPassword(encoder.encode("f"));
//        member6.setBirthDate("19970131");
//        member6.setCreateDate(new Date());
//        member6.setEmail("a@b.com");
//        member6.setGender("여자");
//        member6.setName("손용균");
//        member6.setNickName("중고바다");
//        member6.setQrFlag(0);
//        member6.setRole(Role.ROLE_MEMBER_PHONE);
//        member6.setTotalPoint(20180502L);
//        memberRepo.save(member6);
//
//        Members member7 = new Members();
//        member7.setMemberID("g");
//        member7.setPassword(encoder.encode("g"));
//        member7.setBirthDate("19970131");
//        member7.setCreateDate(new Date());
//        member7.setEmail("a@b.com");
//        member7.setGender("여자");
//        member7.setName("전민재");
//        member7.setNickName("핏고잉");
//        member7.setQrFlag(0);
//        member7.setRole(Role.ROLE_MEMBER_PHONE);
//        member7.setTotalPoint(20180502L);
//        memberRepo.save(member7);
//
//
////        Members member8 = new Members();
////        member8.setMemberID("fakeAuthenticated");
////        member8.setPassword(encoder.encode("sixsense"));
////        member8.setBirthDate("19970131");
////        member8.setCreateDate(new Date());
////        member8.setEmail("a@b.com");
////        member8.setGender("여자");
////        member8.setName("정수승");
////        member8.setNickName("훨씬");
////        member8.setQrFlag(0);
////        member8.setRole(Role.ROLE_MEMBER);
////        member8.setTotalPoint(0L);
////        memberRepo.save(member8);
//
//
//        for (int i = 1; i < 43; i++) {
//            ReportBoard reportBoard = new ReportBoard();
//            reportBoard.setMemberID(member2);
//            reportBoard.setTitle(member2.getName() + "의 " + "페이징용 " + i + "번째 " + "게시글");
//            reportBoard.setContent(member2.getName() + "의 " + i + "번째 " + "게시글");
//            reportBoard.setBoardCategory("코스추천");
//            reportBoardRepo.save(reportBoard);
//        }
//
//        for (int i = 1; i < 42; i++) {
//            ReportBoard reportBoard = new ReportBoard();
//            reportBoard.setMemberID(member1);
//            reportBoard.setTitle(member1.getName() + "의 " + "페이징용 " + i + "번째 " + "게시글");
//            reportBoard.setContent(member1.getName() + "의 " + i + "번째 " + "게시글");
//            reportBoard.setBoardCategory("자유");
//            reportBoardRepo.save(reportBoard);
//        }
//
//        for (int i = 1; i < 40; i++) {
//            ReportBoard reportBoard = new ReportBoard();
//            reportBoard.setMemberID(member2);
//            reportBoard.setTitle(member2.getName() + "의 " + "페이징용 " + i + "번째 " + "게시글");
//            reportBoard.setContent(member2.getName() + "의 " + i + "번째 " + "게시글");
//            reportBoard.setBoardCategory("도보인증");
//            reportBoardRepo.save(reportBoard);
//        }
//
//    }   //testInsert
//}   //class
//
