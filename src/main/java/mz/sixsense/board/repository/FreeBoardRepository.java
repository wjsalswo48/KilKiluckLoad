package mz.sixsense.board.repository;

import mz.sixsense.board.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>, QuerydslPredicateExecutor<FreeBoard> {
    //    @Transactional
//    @Modifying
//    @Query("update FreeBoard f set f.replyCnt = ?1")
//    int updateReplyCntBy(int replyCnt);
//    @Transactional
//    @Modifying
//    @Query("update FreeBoard f set f.replyCnt = ?1 where f.replyCnt between ?2 and ?3")
//    int updateReplyCntByReplyCntBetween(int replyCnt, @Nullable int replyCntStart, @Nullable int replyCntEnd);
    @Transactional
    @Modifying
    @Query(value = " UPDATE FREE_BOARD f SET REPLY_CNT = (SELECT COUNT(*) FROM REPLY r  WHERE f.FREEBID = r.FREEBID) ", nativeQuery = true)
    int updateReplyCntBy(int replyCnt);

    @Query(" select f from FreeBoard f ")
    Page<FreeBoard> getFreeBoardList(Pageable pageable);

    @Query(value = " select * from FREE_BOARD fb where BOARD_CATEGORY = '공지' ", nativeQuery = true)
    Page<FreeBoard> getFreeBoardNoticeList(Pageable pageable);

    @Query(value = " select * from FREE_BOARD where FREEBID = :freeboardBID ", nativeQuery = true)
    FreeBoard findFreeBoardLong(@Param("freeboardBID") Long freeboardBID);
}