package mz.sixsense.member.history.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.entity.OrderHistory;

public interface OrderHistoryRepository 
	extends JpaRepository<OrderHistory, Long>,
	QuerydslPredicateExecutor<OrderHistory>{
	
	List<OrderHistory> findAllBymemberID(Members memberID);
	
}
