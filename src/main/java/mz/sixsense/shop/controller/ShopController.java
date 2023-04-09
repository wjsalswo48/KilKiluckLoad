package mz.sixsense.shop.controller;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import mz.sixsense.board.ScriptUtils;
import mz.sixsense.board.Search;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.entity.OrderHistory;
import mz.sixsense.member.history.service.OrderHistoryService;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.repository.MemberRepository;
import mz.sixsense.member.service.MemberService;
import mz.sixsense.security.SecurityUser;
import mz.sixsense.shop.domain.DeliveryStatus;
import mz.sixsense.shop.entity.Delivery;
import mz.sixsense.shop.entity.ProductGrade;
import mz.sixsense.shop.entity.Products;
import mz.sixsense.shop.file.entity.ProductFile;
import mz.sixsense.shop.file.service.ProductFileService;
import mz.sixsense.shop.service.DeliveryService;
import mz.sixsense.shop.service.ShopService;

@Controller
public class ShopController {
	
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
	private MemberRepository memberRepo; //테스트용

	
	@RequestMapping("shop/productsList")
	public String productsList(Model model, Search search
			,@AuthenticationPrincipal SecurityUser principal
			) {
	//검색기능 없음 , 페이징 없음.  보드에서 어필 
	search.setSearchCondition("");
	search.setSearchCategory("");
	search.setSearchKeyword("");
	
	
	Members member = memberRepo.findById(principal.getMember().getMemberID()).get();
//	Members member = memberRepo.findById("test").get();
	model.addAttribute("member", member);
	
	//평점 기능
	
	//멤버가 구매한 상품리스트 만들어주기 
	List<Delivery> deliveryListByMember = member.getDeliveryList();
	System.out.println(deliveryListByMember+"멤버가 가진 주문 목록");
	
	
	List<Products> deliveryProductList = new ArrayList<Products>();
	//구매목록에서 구매한 상품들만 따로 추려서 리스트 만들어줌 
	//여기안에 들어있으면 구매한 상품들임 -->> 여기 없으면? 구매안한상품 -> 구매해주세요 뜨게. 
	//아니생각해보니깐 없네 ㅋ 
		for(Delivery del : deliveryListByMember) {
		for(Products pros : del.getProductsList()) {
			System.out.println(del.getProductsList());
			if(!deliveryProductList.contains(pros)) {
				deliveryProductList.add(pros);
				}
			};
		}
	System.out.println(deliveryProductList+"배송목록");
	model.addAttribute("orderedProductList", deliveryProductList);
	
	
	
	
	//멤버가 평점매긴것들 리스트 가져온다. 
	List<ProductGrade> gradeListByMember = member.getProductGradeList();
	System.out.println("멤버가 가진 평점 리스트"+ gradeListByMember);
	
	List<Products> gradeProductList = new ArrayList<Products>();
	
	//멤버가 매긴 평점들의 상품을 가져와서 리스트에 넣어준다. 
	for(ProductGrade pg : gradeListByMember) {
		gradeProductList.add(pg.getProduct());
	}
	
	model.addAttribute("productListGradedByMember", gradeProductList);
	
	//몇명의 멤버들이 상품에 평점남겼나.  
	
	//평점 기능

	//페이지랑 서치내용 처리해주기 구현안함. 한페이지로 다보기 
	Page<Products> shopList = shopService.getProductsList(search, 1);
	model.addAttribute("productsList", shopList);
//	System.out.println("그레이드 리스트 중 현재 로그인한 맴버가 남긴 평점리스트 가져오고 거기서 그레이드리스트 중 멤버와 상품으로 거른 그레이드를 꺼내서 그걸 갖고 있는지 확인한다."
//	+ shopService.getProductGradeListByMember(member).contains(shopService.getProductGradeByProAndMem(member, shopService.getProduct(37L))));

	
	// 그레이드리스트 by 멤버. 그레이드 by 멤버and프로
	
	model.addAttribute("memberGradeList", shopService.getProductGradeListByMember(member));
	
		return "shop/productsList";
	
	}//list
	/* 기능 구현 목록 
	 *2. 물건 내용/사진 올리기 
	 */
	
	@GetMapping("shop/productDetail")//productID 만 받아와서 찾음 완료 
	public String productDetail(Model model,Products product) {
		System.out.println(product.getProductID()+"여긴어디?");
		Products products = shopService.getProduct(product.getProductID());
		
		//파일 있을 때 보여주기 
		if(product.getProductFileList().isEmpty()) {
			List<ProductFile> files = productFileService.fileAllView(product.getProductID());
			model.addAttribute("filesList", files);
		}
		
		model.addAttribute("product", products);
		return "shop/productDetail";
	}//detail'
	
	
	
	@GetMapping("shop/images/{fileID}")
    @ResponseBody
    public Resource imageView(@PathVariable("fileID") Long fileID, Model model) throws IOException {
        ProductFile productFile = productFileService.downloadImage(fileID);

        String savePath = productFile.getSavePath();
        if(savePath.substring(savePath.length() - 3, savePath.length()).equals("png")
                || savePath.substring(savePath.length() - 3, savePath.length()).equals("jpg")
                || savePath.substring(savePath.length() - 3, savePath.length()).equals("gif")
                || savePath.substring(savePath.length() - 3, savePath.length()).equals("mp4")
        ) {
            UrlResource url = new UrlResource("file", savePath);
            model.addAttribute("test", "exe");
            return url;
        } else {
            model.addAttribute("test", "not");
        }
        return null;
    }   //imageView

    
    
	//상품등록창 이동
	@GetMapping("shop/insertProduct")
	public String productinsertPath() {
		return "shop/insertProduct";
	}
	
	//상품등록 후 리스트페이지 완료
	@PostMapping("shop/insertProduct")
	public String productInsert(Products ps,@AuthenticationPrincipal SecurityUser principal
			,@RequestParam("files") List<MultipartFile> files
	, HttpServletResponse response
	, HttpServletRequest request
					)throws Exception {
		if(ps.getContent().equals("")||ps.getName().equals("")||ps.getPointPrice()==0||
				ps.getStock()==0) {
			ScriptUtils.alertAndBackPage(response, "작성하지 않은 항목이 있는지 확인 해 주세요!");
			//뒤로가기 기능구현 
			String referer = request.getHeader("Referer"); 
			return "redirect" + referer;			
		}
		
		shopService.insertProduct(ps);
        for (MultipartFile multipartFile : files) {
		ps.setFileID(productFileService.saveFile(multipartFile, ps));
        }		
        //마지막 사진이 대표사진이 된다. 
        shopService.insertProduct(ps);
		return "redirect:/shop/productsList";
	}//insert
	

	
	
	@GetMapping("shop/productUpdateProc")
	public String productUpdateProc(Model model, Products ps
			)throws IOException {
		System.out.println(ps.getProductID()+"도대체 뭐들고옴?");
		Products findProduct = shopService.getProduct(ps.getProductID());
		
		model.addAttribute("product", findProduct);
		//파일 있을 때 보여주기 
		if(!findProduct.getProductFileList().isEmpty()) {
			List<ProductFile> filesList = productFileService.fileAllView(findProduct.getProductID());
			model.addAttribute("filesList", filesList);
		}
		
		return "shop/modifyProduct";
	}
	
	//수정시 받을 컨트롤러
	//상품 수정하면 새로운 대표사진을 지정해 줘야한다.. 
	@PostMapping("shop/productUpdating")
	public String productUpdating(Products pro
			, HttpServletResponse response
			,Model model
			,HttpServletRequest request
			,@RequestParam("files") List<MultipartFile> files
			)
	throws IOException {
		System.out.println("수정하는 카테고리@@=========="+pro.getProductCategory());
		if(pro.getProductCategory()=="") {
			ScriptUtils.alertAndBackPage(response, "카테고리를 지정해 주세요!");
			//뒤로가기 기능구현 
			String referer = request.getHeader("Referer"); 
			return "redirect" + referer;
		}
		Products findProduct = shopService.getProduct(pro.getProductID());
		findProduct.setName(pro.getName());
		findProduct.setContent(pro.getContent());
		findProduct.setPointPrice(pro.getPointPrice());
		findProduct.setStock(pro.getStock());
		findProduct.setProductCategory(pro.getProductCategory());
		//파일 이미지 등록 코드
		System.out.println(files.isEmpty()+"파일리스트안에든거");
		for (MultipartFile multipartFile : files) {
				findProduct.setFileID(productFileService.saveFile(multipartFile, findProduct));
		        }	
		 shopService.updateProduct(findProduct);
		
		return "redirect:/shop/productsList";
	}
	
	//상품 수정시 이미지 삭제  이미지삭제 위에 넣은 이유는 이미지먼저 제거하라고. 이미지 제거하면 redirect로 가서 밑에 수정한내용 초기화됨
	@GetMapping("shop/deleteFile")
	   public String deleteFile(ProductFile proFile, Products pro) throws IOException {
		productFileService.deleteFile(proFile.getFileID());
	      
	      return "forward:/shop/productUpdateProc?productID="+pro.getProductID();
	   }
	   
	
	//상품 삭제 
	@GetMapping("shop/productDelete")
	public String deleteProduct(Products ps) {
		System.out.println(ps.getProductGradeList()+"그레이드 리스트");
		Products findProduct = shopService.getProduct(ps.getProductID());
		System.out.println(findProduct.getProductFileList()+"파일 리스트");
		if(!productFileService.fileAllView(findProduct.getProductID()).isEmpty()) {
			List<ProductFile> list = productFileService.fileAllView(findProduct.getProductID());
			for(ProductFile pf : list) {
				productFileService.deleteFile(pf.getFileID());
			}
		System.out.println("파일리스트에 뭔가");
		}
		if(!shopService.getProductGradeListByProduct(findProduct).isEmpty()) {
		List<ProductGrade> list = shopService.getProductGradeListByProduct(findProduct);
		for(ProductGrade pg : list) {
			shopService.deleteProductGrade(pg.getGradeSeq());
		}
		System.out.println("그레이드 리스트에 뭔가");
		}
		
		shopService.deleteProduct(ps.getProductID());
		return "redirect:productsList";
	}

	
	
	
	
	/* 1-2. 물건 구매 
	 * 페이지 
	 */
	@RequestMapping("shop/productPurchase")
	public String purchase(Products products, Model model, Products product, Members member
			, @AuthenticationPrincipal SecurityUser principal
			){
		Products findProduct = shopService.getProduct(product.getProductID());
		Members findMember = memberRepo.findById(principal.getMember().getMemberID()).get();
//		Members findMember = memberRepo.findById("test").get();

		
		if(!findProduct.getProductFileList().isEmpty()) {
			List<ProductFile> files = productFileService.fileAllView(product.getProductID());
			model.addAttribute("filesList", files);
		}

		model.addAttribute("product", findProduct);
		model.addAttribute("member", findMember);

		return "shop/productPurchase";
	}
	
	
	
	//구매 확정
	@RequestMapping("shop/purchaseProc")
	private String purchaseProc(Model model, Products product,
			Delivery delivery,
			@AuthenticationPrincipal SecurityUser principal,
			@RequestParam("howToPurchase") String howTo,
			@RequestParam("howMany") String howMany,
			HttpServletResponse response,
			HttpServletRequest request//뒤로가기 기능 구현
			)throws IOException {
		System.out.println("프록의 딜리버리 아이디있나?"+delivery);
		
		if(delivery.getAddress().equals("")) {
			ScriptUtils.alertAndBackPage(response, "주소를 입력해 주세요!");
			String referer = request.getHeader("Referer"); 
			return "redirect" + referer;
		}
		
		if(delivery.getAddressDetail().equals("")) {
			ScriptUtils.alertAndBackPage(response, "상세 주소를 입력해 주세요!");
			//뒤로가기 기능구현 
			String referer = request.getHeader("Referer"); 
			return "redirect" + referer;
			
		}
	
		Products pro = shopService.getProduct(product.getProductID());
		
		if(Integer.parseInt(howMany) >pro.getStock() ) {
			ScriptUtils.alertAndBackPage(response, "이런! 재고가 부족해요!");
			//뒤로가기 기능구현 
			String referer = request.getHeader("Referer"); 
			return "redirect" + referer;
		}
		
		
		//pro.setDelivery(delivery);
		//shopService.updateProduct(pro);
		
		Members mem = memberRepo.findById(principal.getMember().getMemberID()).get();

		
		
		long pp = -pro.getPointPrice();
		int amount = Integer.parseInt(howMany);
		long totalPay = pp*amount;
		//포인트 부족할 경우 
		if(-totalPay > mem.getTotalPoint() ) {
			ScriptUtils.alertAndBackPage(response, "이런! 포인트가 부족해요!");
			//뒤로가기 기능구현 
			String referer = request.getHeader("Referer"); 
			return "redirect" + referer;
		}

		//delivery test
		System.out.println(delivery+"배송주소");
		delivery.setDeliveryStatus(DeliveryStatus.상품준비중);
		delivery.setMemberID(mem);
		delivery.setOrderAmount(amount);
		
		
		//orderHistory
		OrderHistory oh = new OrderHistory();
		oh.setOrderDate(new Date());
		oh.setAmount(amount);
		oh.setProductName(pro.getName());
		oh.setMemberID(mem);
		
		if(howTo.equals("포인트로 상품 구매")){
			//업뎃 메소드가 변동시킬 포인트랑  변동시킬 멤버 가져가야함
			//포인트 업데이트시 히스토리 두개 쌍으로 들어가게 
			memberService.updatePoint(mem, totalPay);
			pointHistoryService.insertPointHistory(mem, "물품구매", totalPay);
			pro.setDelivery(delivery);
			deliveryService.insertDelivery(delivery);
			pro.setStock(pro.getStock()-amount);
			shopService.updateProduct(pro);
			//orderHistoryService.insertOrder(oh);
		}
		ScriptUtils.alertAndMovePage(response, "물품 구매 완료!","productsList" );
		return "redirect:/shop/productsList";
	}
	
	
	/* 
	 * 3. 별점 (grade)
	 */ 
	@RequestMapping("shop/grade")
	public String grading(Model model, Products products,
			ProductGrade pGrade,
			@AuthenticationPrincipal SecurityUser principal
			, HttpServletResponse response
			, HttpServletRequest request//뒤로가기 기능 구현

			)throws IOException {
		if(pGrade.getGrades()== 0) {
			ScriptUtils.alertAndBackPage(response,"평점을 매겨 주세요!" );
			//뒤로가기 기능구현 
			String referer = request.getHeader("Referer"); 
			return "redirect" + referer;
		}
		Members mem = memberRepo.findById(principal.getMember().getMemberID()).get();

		Products pro = shopService.getProduct(products.getProductID());
		
		if(shopService.getProductGradeByProAndMem(mem, products)!=null) {
		ProductGrade findPG	= shopService.getProductGradeByProAndMem(mem, products);
				findPG.setGrades(pGrade.getGrades());
			shopService.updateProductGrade(findPG);
		}else {
		
		pGrade.setMember(mem);
		pGrade.setProduct(products);
		
		
		//1 productGrade 추가.  근데 평가한게 있으면 평가안되게 -> 평가완료버튼띄우려고 이건 리스트에 넣어야지  
		//if()
		shopService.insertProductGrade(pGrade);
		}
		//2 그레이드 갯수 들고오기, 상품기준으로 
		List<ProductGrade> gradeList = 
				shopService.getProductGradeListByProduct(pro);
		System.out.println(gradeList);
		int gradeDivider = gradeList.size();
		//3 그레이드 다 합치기
		double allGrades = 0D;
		for(ProductGrade pg : gradeList) {
			allGrades += pg.getGrades();
		}
		
		//4 그레이드 나눠주기 
		double avgGrade = allGrades/gradeDivider;
		
		//5 그레이드 평균 프로덕트에 삽입하기 
		pro.setGrade(avgGrade);
		shopService.updateProduct(pro);
		
		
		return "redirect:/shop/productsList";
	}
	
	
	
	/* 4. 배송지 입력
	 */ 
//	 @RequestMapping("deliveryInsert")
//	 public String deliveryInsert(Delivery delivery) {
//		 
//		 deliveryService.insertDelivery(delivery);
//		 
//		 return "";
//	 }
	 
	 
	@RequestMapping("shop/deliveryUpdateForm")
	public String derliveryUpdateForm(Model model) {
		
		model.addAttribute("delivery", null);
		return "shop/deliveryUpdateForm";
	}
	
	@RequestMapping("shop/deliveryUpdateProc")
	public String deliveryUpdateProc(Delivery delivery) {
		deliveryService.updateDelivery(delivery);
		
		return "shop/deliveryUpdateProc";
	}
	
	
	// 목표 : 인덱스로 다 몰아서 인덱스를 쇼핑몰로 만든다
	//푸쉬하기전에 시큐리티 설정, 상품등록시 관리자만 등록되게
	//
/*	@RequestMapping("vue")
	public String vueGo(Model model, Search search
			,@AuthenticationPrincipal SecurityUser principal
			) {
		search.setSearchCondition("");
		search.setSearchCategory("");
		search.setSearchKeyword("");
		
		
		//권한 합칠때 수정해주기 
//		Members member = memberRepo.findById(principal.getMember().getMemberID()).get();
		Members member = memberRepo.findById("test").get();
		model.addAttribute("member", member);
		
		//평점 기능
		
		//멤버가 구매한 상품리스트 만들어주기 
		List<Delivery> deliveryListByMember = member.getDeliveryList();
		System.out.println(deliveryListByMember+"멤버가 가진 주문 목록");
		
		
		List<Products> deliveryProductList = new ArrayList<Products>();
		//구매목록에서 구매한 상품들만 따로 추려서 리스트 만들어줌 
		//여기안에 들어있으면 구매한 상품들임 -->> 여기 없으면? 구매안한상품 -> 구매해주세요 뜨게. 
		//아니생각해보니깐 없네 ㅋ 
			for(Delivery del : deliveryListByMember) {
			for(Products pros : del.getProductsList()) {
				System.out.println(del.getProductsList());
				if(!deliveryProductList.contains(pros)) {
					deliveryProductList.add(pros);
					}
				};
			}
		System.out.println(deliveryProductList+"배송목록");
		model.addAttribute("orderedProductList", deliveryProductList);
		
		//멤버가 평점매긴것들 리스트 가져온다. 
		List<ProductGrade> gradeListByMember = member.getProductGradeList();
		System.out.println("멤버가 가진 평점 리스트"+ gradeListByMember);
		
		List<Products> gradeProductList = new ArrayList<Products>();
		
		//멤버가 매긴 평점들의 상품을 가져와서 리스트에 넣어준다. 
		for(ProductGrade pg : gradeListByMember) {
			gradeProductList.add(pg.getProduct());
		}
		
		model.addAttribute("productListGradedByMember", gradeProductList);
		
		//몇명의 멤버들이 상품에 평점남겼나.  
		
		//평점 기능

		//페이지랑 서치내용 처리해주기 구현안함. 한페이지로 다보기 
		Page<Products> shopList = shopService.getProductsList(search, 1);
		model.addAttribute("productsList", shopList);
//		System.out.println("그레이드 리스트 중 현재 로그인한 맴버가 남긴 평점리스트 가져오고 거기서 그레이드리스트 중 멤버와 상품으로 거른 그레이드를 꺼내서 그걸 갖고 있는지 확인한다."
//		+ shopService.getProductGradeListByMember(member).contains(shopService.getProductGradeByProAndMem(member, shopService.getProduct(37L))));

		
		// 그레이드리스트 by 멤버. 그레이드 by 멤버and프로
		
		model.addAttribute("memberGradeList", shopService.getProductGradeListByMember(member));
		
		
		
		
		
		return "vue/index";
	}
	*/
	
}//class
