//package mz.sixsense.board.like.service;
//
//import mz.sixsense.board.entity.FreeBoard;
//import mz.sixsense.board.like.entity.LikeCount;
//import mz.sixsense.board.like.repository.LikeCountRepository;
//import mz.sixsense.member.entity.Members;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LikeServiceImpl implements LikeService {
//
//    @Autowired
//    LikeCountRepository likeRepo;
//
//    @Override
//    public void insertLike(FreeBoard freeBoard, Members member) {
//
//        LikeCount like = new LikeCount();
//        like.setMembers(member);
//        like.setFreeBoard(freeBoard);
//
//        likeRepo.save(like);
//    }
//
//    @Override
//    public void updateLike(LikeCount like) {
//        LikeCount findLike = likeRepo.findById(like.getLikeID()).get();
//        findLike.setLikeCheck(0);
//
//        likeRepo.save(findLike);
//    }
//
//    @Override
//    public Long findLike(FreeBoard freeBoard, Members members) {
//        return null;
//    }
//}
