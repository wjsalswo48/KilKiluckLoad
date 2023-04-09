package mz.sixsense.reportboard.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mz.sixsense.board.entity.ReportBoard;

public interface ReportBoardRepository extends CrudRepository<ReportBoard, Long>,
	QuerydslPredicateExecutor<ReportBoard> {

		@Query("SELECT r FROM ReportBoard r")
		Page<ReportBoard> getReportBoardList(Pageable pageable);

//		@Query("SELECT b FROM Board b Where CATEGORY = :keyword")
//		Page<ReportBoard> getCateBoardList(@Param("keyword") String keyword , Pageable pageable);
//		
//		@Query("DELETE FROM Board b Where BOARD_ID = :keyword")
//		@Modifying
//		@Transactional
//		void deleteBoard(@Param("keyword") String keyword);
//		
//		@Query("SELECT b FROM Board b Where MEMBER_ID = :keyword")
//		List<ReportBoard> countBoardList(@Param("keyword") String keyword);
}
