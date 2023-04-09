package mz.sixsense.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import mz.sixsense.member.history.service.OrderHistoryService;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.service.MemberService;
import mz.sixsense.shop.entity.ProductGrade;
import mz.sixsense.shop.entity.Products;
import mz.sixsense.shop.file.entity.ProductFile;
import mz.sixsense.shop.file.service.ProductFileService;
import mz.sixsense.shop.repository.ProductGradeRepository;
import mz.sixsense.shop.repository.ProductsRepository;
import mz.sixsense.shop.service.DeliveryService;
import mz.sixsense.shop.service.ShopService;

@RestController
@RequestMapping("/vue/")
public class VueShopController {
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ProductFileService productFileService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@Autowired
	private PointHistoryService pointHistoryService;
	
	@Autowired
	private ProductsRepository productsRepo;

	@Autowired
	private ProductGradeRepository pgRepo;
	
	@GetMapping("hi")
	public String hivue() {
		return "뷰가 왔습니다.";
	}
	
	//아니 재귀호출 에러 @jsonignore 이걸 엇다붙이는지 말해줘야지!!!! 그건바로 연관된 칼럼 위~ 
	@RequestMapping("productList")
	public List<Products> productsList() {
		List<Products> list = 
				productsRepo.findAll();
		
		return list;
	}
	
	@RequestMapping("productImgList")
	public List<ProductFile> productImgList(){
		List<ProductFile> imgList = productFileService.view();
		return imgList;
	}
	
	//상품 평가한 인원수 가져오기.
	@RequestMapping("productGradedList/{productID}")
	public List<ProductGrade> productGrade(@PathVariable("productID") Long pID){
		Products ps = shopService.getProduct(pID);
		
		List<ProductGrade> pgL = pgRepo.findAllByproduct(ps);
		return pgL;
		
	}
	
}//controller
