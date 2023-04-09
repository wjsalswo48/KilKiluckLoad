package mz.sixsense.member.history.service.impl;

import com.querydsl.core.BooleanBuilder;
import mz.sixsense.board.Search;
import mz.sixsense.board.entity.QFreeBoard;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.entity.PointHistory;
import mz.sixsense.member.history.entity.QPointHistory;
import mz.sixsense.member.history.repository.PointHistoryRepository;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PointHistoryServiceImpl implements PointHistoryService{
	@Autowired
	private PointHistoryRepository pointHistoryRepo;

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Page<PointHistory> getPointHistoryList(Search search,Members member, int page) {
		BooleanBuilder builder = new BooleanBuilder();
		//member 에는 찾아올 멤버의 아이디가 들어있어야함
//		Page<PointHistory> list = pointHistoryRepo.findAllBymemberID(member);

		QPointHistory qpointHistory = QPointHistory.pointHistory;
		builder.and(qpointHistory.memberID.eq(member));
		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qpointHistory.reason.like("%" + search.getSearchKeyword() + "%"));
		}
//		else if (search.getSearchCondition().equals("CONTENT")) {
//			builder.and(qpointHistory.content.like("%" + search.getSearchKeyword() + "%"));
//		}
		Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "pointID");
//		System.out.println("==========================>" + pointHistoryRepo.findAllBymemberID(member,builder, pageable));


		return pointHistoryRepo.findAll(builder, pageable);
	}

	@Override
	public void insertPointHistory(Members member, String reason, Long variancePoint) {

		PointHistory pointHistory = new PointHistory();
		
		pointHistory.setMemberID(memberRepository.findById(member.getMemberID()).get());
		pointHistory.setReason(reason);
		pointHistory.setVariancePoint(variancePoint);

		pointHistoryRepo.save(pointHistory);
	}

	@Override
	public PointHistory getOrderHistory(long pointID) {
		return pointHistoryRepo.findById(pointID).get();
	}

	@Override
	public void deleteOrderHistory(long pointID) {
		pointHistoryRepo.deleteById(pointID);
	}

	@Override
	public void deleteAllOrderHistory() {
		pointHistoryRepo.deleteAll();
	}

	@Override
	public void insertOrder(PointHistory pointHistory) {
		pointHistoryRepo.save(pointHistory);
	}
	

}//class
