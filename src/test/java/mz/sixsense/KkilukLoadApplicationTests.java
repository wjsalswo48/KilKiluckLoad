//package mz.sixsense;
//
//import mz.sixsense.board.entity.FreeBoard;
//import mz.sixsense.board.reply.entity.Reply;
//import mz.sixsense.board.reply.repository.ReplyRepository;
//import mz.sixsense.board.reply.service.ReplyService;
//import mz.sixsense.enums.Role;
//import mz.sixsense.member.entity.Members;
//import mz.sixsense.member.repository.MemberRepository;
//import mz.sixsense.shop.domain.DeliveryStatus;
//import mz.sixsense.shop.entity.Delivery;
//import mz.sixsense.shop.entity.Products;
//import mz.sixsense.shop.repository.DeliveryRepository;
//import mz.sixsense.shop.repository.ProductsRepository;
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
//class KkilukLoadApplicationTests {
//
//	@Autowired
//	private MemberRepository memberRepository;
//
//	@Autowired
//	private PasswordEncoder encoder;
//
//	@Autowired
//	private DeliveryRepository deliveryRepo;
//
//	//@Test
//	void memberTest() {
//
//		Members member1 = new Members();
//		member1.setMemberID("ssu");
////		member1.setAge(20);
//		member1.setBirthDate("19941018");
//		member1.setEmail("kimjh9334@naver.com");
//		member1.setGender("남자");
//		member1.setName("김석림");
//		member1.setNickName("쿠쿠");
//		member1.setPassword(encoder.encode("123"));
//		member1.setRole(Role.ROLE_MEMBER);
//
//		memberRepository.save(member1);
//
//
//
//	}
//
//	@Autowired
//	private ProductsRepository shopRepo;
//	@Autowired
//	private ReplyRepository replyRepository;
//
//	@Autowired
//	private ReplyService replyService;
//
//	@Test
//	void shopTest() {
//		Members mem = memberRepository.findById("오라!1").get();
//
//		for(int i = 1; i <10; i++) {
//			Delivery del = new Delivery();
//			del.setAddress("어딘가"+i);
//			del.setMember(mem);
//			del.setDeliveryStatus(DeliveryStatus.사고);
//			del.setProductsList(null);
//			deliveryRepo.save(del);
//		}
//
//		for(long i = 1; i <10; i++) {
//		Products pro = new Products();
//		pro.setName("상품"+i);
//		pro.setContent("상품설명"+i);
//		pro.setProductCategory("카테고리");
//		pro.setCashPrice((100L+i));
//		pro.setPointPrice((100L+i));
//		pro.setStock((1L+i));
//		pro.setDelivery(deliveryRepo.findById(i).get());
//		shopRepo.save(pro);
//		}
//
//		}//test
//
//	@Test
//	public void test(){
//		System.out.println(memberRepository.findById("test").get().getLikeCountList());
//
//	}
//
//	@Test
//	public void test2() {
//		Reply reply = new Reply();
//
//	    reply.setFreeBoard(freeboard);
//		reply.setMemberID(member);
//		reply.setContent("123");
//		reply.setCreateDate(new Date());
//		reply.setRecommendCnt(0L);
//
//		replyService.replyWrite();
//
//		replyRepository.save(reply);
//	}
//}
