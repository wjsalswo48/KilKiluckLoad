package mz.sixsense.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import mz.sixsense.shop.entity.Products;

public interface ProductsRepository 
	extends JpaRepository<Products, Long>, 
		QuerydslPredicateExecutor<Products> {
    @Query(" select p from Products p ")
    Page<Products> getProductsList(Pageable pageable);
}