package mz.sixsense.shop.file.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mz.sixsense.shop.entity.Products;
import mz.sixsense.shop.file.entity.ProductFile;

public interface ProductFileRepository 
extends JpaRepository<ProductFile, Long>, 
QuerydslPredicateExecutor<ProductFile>{
	
	   @Query(value = " SELECT * FROM PRODUCT_FILE WHERE product = :productID ", nativeQuery = true)
	   List<ProductFile> selectImageAllViewQuery(@Param("productID") Long productID);
	   
//	   @Transactional
//	   @Modifying
//	   @Query("DELETE from PRODUCT_FILE a where a.products.productID = :bid")
//	   void deleteBoard(@Param("bid")Long bid);
	   
	   void deleteAllByproduct(Products pd);
}
