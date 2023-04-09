package mz.sixsense.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import mz.sixsense.board.Search;
import mz.sixsense.member.entity.Members;
import mz.sixsense.shop.entity.ProductGrade;
import mz.sixsense.shop.entity.Products;

public interface ShopService {
	
	
	public Page<Products> getProductsList(Search search,int page );

	public Products getProduct(long pid);

	void insertProduct(Products ps);

	void updateProduct(Products oldItem);

	void deleteProduct(long pid);
	
	//평점기능 구현 
	List<ProductGrade> getProductGradeListByProduct(Products product);
	List<ProductGrade> getProductGradeListByMember(Members member);
	ProductGrade getProductGradeByProAndMem(Members member, Products product);
	
	void insertProductGrade(ProductGrade productGrade);

	
	void deleteProductGrade(Long gradeSeq);

	void updateProductGrade(ProductGrade productGrade);
	
	void deleteAllByproductID(Products pd);
}//class
