package mz.sixsense.board.like.repository;

import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.like.entity.LikeCount;
import mz.sixsense.member.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface LikeCountRepository extends JpaRepository<LikeCount, Long>, QuerydslPredicateExecutor<LikeCount> {

    List<LikeCount> findAllBymembers(Members member);
    List<LikeCount> findAllByfreeBoard(FreeBoard freeBoard);
    LikeCount findLikeCountByMembersAndFreeBoard(Members member, FreeBoard freeBoard);
}

