package mz.sixsense.board.service;

import mz.sixsense.board.Search;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.like.entity.LikeCount;
import mz.sixsense.member.entity.Members;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FreeBoardService {

    void insertFreeBoard(FreeBoard freeBoard);
    void updateFreeBoard(FreeBoard freeBoard);
    void deleteFreeBoard(FreeBoard freeBoard);
    FreeBoard getFreeBoard(FreeBoard freeBoard);

    FreeBoard getFreeBoard(Long freeBoard);

    FreeBoard registerForm(FreeBoard freeBoard);

    Page<FreeBoard> getFreeBoardList(Search search, int page, FreeBoard freeBoard);

    FreeBoard likeUp(FreeBoard freeBoard);

    FreeBoard likeDown(FreeBoard freeBoard);


    List<LikeCount> getLikeListByFreeBoard(FreeBoard freeBoard);
    List<LikeCount> getLikeListByMember(Members member);
    LikeCount getLikeByFbAndMem(Members member, FreeBoard freeBoard);

    void insertLike(LikeCount likeCount);
    void deleteLike(long likeID);

    Page<FreeBoard> myfreeBoardList(Search search, Members member, int page);

}