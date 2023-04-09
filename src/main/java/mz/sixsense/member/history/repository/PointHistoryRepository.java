package mz.sixsense.member.history.repository;

import com.querydsl.core.BooleanBuilder;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.entity.PointHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Locale;

public interface PointHistoryRepository extends JpaRepository<PointHistory,Long>,
	QuerydslPredicateExecutor<PointHistory>{

	@Query(value = " SELECT * FROM PointHistory WHERE memberID = :memberID", nativeQuery = true)
	Page<PointHistory> findAllBymemberID(@Param("memberID") Members memberID, Pageable pageable);

//	@Query(value = " SELECT * FROM POINT_HISTORY WHERE memberID = :memberID", nativeQuery = true)
//	Page<PointHistory> findAllBymember(@Param(("memberID")) String memberID, BooleanBuilder builder, Pageable pageable);
}
