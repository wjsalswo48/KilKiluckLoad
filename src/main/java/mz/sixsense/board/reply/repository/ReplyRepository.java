package mz.sixsense.board.reply.repository;

import mz.sixsense.board.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long>, QuerydslPredicateExecutor<Reply> {
    @Query(value = " select * from reply where freeBID = :freeBID ", nativeQuery = true)
    Page<Reply> selectReplyAllFind(@Param("freeBID") Long freeBID, Pageable pageable);

//    @Query(value = " update FreeBoard set replyCnt = '(select count(*) from reply where freeBoard.freeBID = reply.freeBID)' ")
//    Long getReplyCount();

    @Query(value = " select * from REPLY where REPLYID = :replyID ", nativeQuery = true)
    Reply findReplyID(@Param("replyID")Long replyID);
}
