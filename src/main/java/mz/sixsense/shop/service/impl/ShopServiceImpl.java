package mz.sixsense.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import mz.sixsense.board.Search;
import mz.sixsense.member.entity.Members;
import mz.sixsense.shop.entity.ProductGrade;
import mz.sixsense.shop.entity.Products;
import mz.sixsense.shop.entity.QProducts;
import mz.sixsense.shop.repository.ProductGradeRepository;
import mz.sixsense.shop.repository.ProductsRepository;
import mz.sixsense.shop.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ProductsRepository productsRepo;

	@Autowired
	private ProductGradeRepository productGradeRepo;

	public ShopServiceImpl() {
		super();
	}

	@Override
	public Page<Products> getProductsList(Search search, int page) {
		BooleanBuilder builder = new BooleanBuilder();

		QProducts qProducts = QProducts.products;

		if (search.getSearchCondition().equals("NAME")) {
			builder.and(qProducts.name.like("%" + search.getSearchKeyword() + "%"));
			builder.and(qProducts.productCategory.like("%" + search.getSearchCategory() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qProducts.content.like("%" + search.getSearchKeyword() + "%"));
			builder.and(qProducts.productCategory.like("%" + search.getSearchCategory() + "%"));
		}

		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "productID");
		// page 칸 수정해주기 페이지는 0부터 시작해서 없다고 뜬거다 ㅅ비...ㅋㅋ

		return productsRepo.findAll(builder, pageable);
	}

	@Override
	public Products getProduct(long pid) {
		Products product = productsRepo.findById(pid).get();

		return product;
	}

//멤버포인트는멤버에서 
//포인트 수급도 멤버에서 
	@Override
	public void insertProduct(Products ps) {
		productsRepo.save(ps);
	}// insert

	@Override // 업데이트 할때는 아이디로 찾아온다음에 하나씩 세팅해주기.
	public void updateProduct(Products oldItem) {
		Products pro = productsRepo.findById(oldItem.getProductID()).get();
		System.out.println(pro + "업뎃전엔 뭐들고있니");
		pro.setName(oldItem.getName());
		pro.setContent(oldItem.getContent());

		pro.setCashPrice(oldItem.getCashPrice());
		pro.setPointPrice(oldItem.getPointPrice());
		pro.setStock(oldItem.getStock());

		pro.setProductCategory(oldItem.getProductCategory());
		pro.setGrade(oldItem.getGrade());

		productsRepo.save(pro);
	}// update

	@Override
	public void deleteProduct(long pid) {
		productsRepo.deleteById(pid);
	}

	// grade 평점기능들
	@Override
	public List<ProductGrade> getProductGradeListByProduct(Products product) {
		return productGradeRepo.findAllByproduct(product);
	}

	@Override
	public List<ProductGrade> getProductGradeListByMember(Members member) {
		return productGradeRepo.findAllBymember(member);
	}

	@Override
	public void insertProductGrade(ProductGrade productGrade) {
		productGradeRepo.save(productGrade);
	}

	@Override
	public void deleteProductGrade(Long gradeSeq) {
		productGradeRepo.deleteById(gradeSeq);
	}

	@Override
	public ProductGrade getProductGradeByProAndMem(Members member, Products product) {
		return productGradeRepo.findProductGradeByMemberAndProduct(member, product);
	}

	@Override
	public void updateProductGrade(ProductGrade productGrade) {
		ProductGrade pg = productGradeRepo.getReferenceById(productGrade.getGradeSeq());
		pg.setGrades(productGrade.getGrades());
		productGradeRepo.save(pg);
	}
	
	@Override
	public void deleteAllByproductID(Products pd) {
		productGradeRepo.deleteAllByproduct(pd);
	}
	

}// class
