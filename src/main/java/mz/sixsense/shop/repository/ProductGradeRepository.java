package mz.sixsense.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import mz.sixsense.member.entity.Members;
import mz.sixsense.shop.entity.ProductGrade;
import mz.sixsense.shop.entity.Products;

public interface ProductGradeRepository 
	extends JpaRepository<ProductGrade, Long>, 
		QuerydslPredicateExecutor<ProductGrade> {
	
	List<ProductGrade> findAllBymember(Members member);
	List<ProductGrade> findAllByproduct(Products product);
	//지리넹~
	ProductGrade findProductGradeByMemberAndProduct(Members member, Products product);
	
	void deleteAllByproduct(Products pd);
}