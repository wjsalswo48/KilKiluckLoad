package mz.sixsense.member.history.service;

import mz.sixsense.board.Search;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.entity.PointHistory;
import org.springframework.data.domain.Page;

public interface PointHistoryService {


	Page<PointHistory> getPointHistoryList(Search search, Members member, int page);

	void insertPointHistory(Members member, String reason, Long variancePoint);

	PointHistory getOrderHistory(long pointID);

	void deleteOrderHistory(long pointID);

	void deleteAllOrderHistory();

	void insertOrder(PointHistory pointHistory);


}
