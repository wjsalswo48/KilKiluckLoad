package mz.sixsense.member.history.service.impl;

import com.querydsl.core.BooleanBuilder;
import mz.sixsense.board.Search;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.entity.OrderHistory;
import mz.sixsense.member.history.entity.QOrderHistory;
import mz.sixsense.member.history.repository.OrderHistoryRepository;
import mz.sixsense.member.history.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
	@Autowired
	private OrderHistoryRepository orderHistoryRepo;

	@Override
	public List<OrderHistory> getOrderHistoryList(Members member) {
		//member 에는 찾아올 멤버의 아이디가 들어있어야함
		List<OrderHistory> list = orderHistoryRepo.findAllBymemberID(member);
		return list;
	}

	@Override
	public Page<OrderHistory> getOrderHistoryList(Search search, Members member, int page) {

		BooleanBuilder builder = new BooleanBuilder();

		QOrderHistory qOrderHistory = QOrderHistory.orderHistory;
		builder.and(qOrderHistory.memberID.eq(member));

		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qOrderHistory.productName.like("%" + search.getSearchKeyword() + "%"));
		}

		Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "orderHistoryID");

		return orderHistoryRepo.findAll(builder, pageable);

	}

	@Override
	public OrderHistory getOrderHistory(long orderID) {
		return orderHistoryRepo.findById(orderID).get();
	}

	@Override
	public void deleteOrderHistory(long orderID) {
		orderHistoryRepo.deleteById(orderID);
	}

	@Override
	public void deleteAllOrderHistory() {
		orderHistoryRepo.deleteAll();
	}

	@Override
	public void insertOrder(OrderHistory orderHistory) {
		orderHistoryRepo.save(orderHistory);
	}


}
