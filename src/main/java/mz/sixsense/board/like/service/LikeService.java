package mz.sixsense.board.like.service;

import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.like.entity.LikeCount;
import mz.sixsense.member.entity.Members;

public interface LikeService {

    void insertLike(FreeBoard freeBoard, Members member);

    void updateLike(LikeCount likeCount);

    Long findLike(FreeBoard freeBoard, Members members);

}
