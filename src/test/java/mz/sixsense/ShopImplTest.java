//package mz.sixsense;
//
//import mz.sixsense.member.entity.Members;
//import mz.sixsense.member.history.entity.OrderHistory;
//import mz.sixsense.member.history.repository.OrderHistoryRepository;
//import mz.sixsense.member.history.service.OrderHistoryService;
//import mz.sixsense.member.repository.MemberRepository;
//import mz.sixsense.shop.entity.ProductGrade;
//import mz.sixsense.shop.entity.Products;
//import mz.sixsense.shop.repository.ProductGradeRepository;
//import mz.sixsense.shop.repository.ProductsRepository;
//import mz.sixsense.shop.service.ShopService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class ShopImplTest {
//	@Autowired
//	private OrderHistoryService oh;
//
//	@Autowired
//	private OrderHistoryRepository repo;
//
//	@Autowired
//	private MemberRepository memberRepo;
//
//	@Autowired
//	private OrderHistoryRepository orderHisRepo;
//
//	@Autowired
//	private ProductsRepository productsRepo;
//
//	@Autowired
//	private ShopService shopService;
//
//	@Autowired
//	private ProductGradeRepository pgrepo;
//
//		//@Test
//		public void test() {
//		Members mem = new Members();
//		mem.setMemberID("오라!1");//멤버랑 연결해놔서 멤버를 넣어줘야한다. 멤버아이디로 찾는다고 해도
//		List<OrderHistory> list = repo.findAllBymemberID(mem);
//		System.out.println("왜안뜸?"+list);
//		}
//
//		@Test
//		public void testtest() {
//			Products pt = shopService.getProduct(1L);
//			System.out.println(
//					pt.getProductFileList()
//					+"삭제뭐해?");
//			//pgrepo.deleteById(pt.getProductID());
//		}
//
//		//@Test
//		public void test3() {
//			Members mem = memberRepo.findById("test").get();
//			Products pro = productsRepo.findById(37L).get();
//			ProductGrade pg = shopService.getProductGradeByProAndMem(mem, pro);
////		List<ProductGrade> pgl= ss.getProductGradeListBytwoParam(mem, pro);
//			System.out.println(pg);
//		}
//
//		//@Test
////		 void test2() {
//////			for(int i=1; i<10; i++) {
//////				Members mem = new Members();
//////				mem.setMemberID("오라!"+i);
//////				mem.setName("무다!"+i);
//////				mem.setPassword("1");
//////				memberRepo.save(mem);
//////
//////				OrderHistory oh = new OrderHistory();
//////				oh.setAmount(11L);
//////				oh.setMember(mem);
//////				oh.setOrderDate(new Date());
//////				orderHisRepo.save(oh);
//////
//////			}
//////		}
//
//}
