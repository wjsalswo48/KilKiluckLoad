package mz.sixsense.member.history.service;

import mz.sixsense.board.Search;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.entity.OrderHistory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderHistoryService {
	

	public void deleteAllOrderHistory();

	OrderHistory getOrderHistory(long orderID);

	void deleteOrderHistory(long orderID);
	
	void insertOrder(OrderHistory orderHistory);

	List<OrderHistory> getOrderHistoryList(Members member);

	Page<OrderHistory> getOrderHistoryList(Search search, Members member, int page);

}//class
