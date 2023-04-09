package mz.sixsense.board.service.impl;

import com.querydsl.core.BooleanBuilder;
import mz.sixsense.board.Search;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.entity.QFreeBoard;
import mz.sixsense.board.like.entity.LikeCount;
import mz.sixsense.board.like.repository.LikeCountRepository;
import mz.sixsense.board.repository.FreeBoardRepository;
import mz.sixsense.board.service.FreeBoardService;
import mz.sixsense.member.entity.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

    @Autowired
    private FreeBoardRepository freeBoardRepo;

    @Autowired
    private LikeCountRepository likeRepo;

    @Override
    public void insertFreeBoard(FreeBoard freeBoard) {
        freeBoardRepo.save(freeBoard);

    }   //insertFreeBoard

    @Override
    public void updateFreeBoard(FreeBoard freeBoard) {
        FreeBoard findFreeBoard = freeBoardRepo.findById(freeBoard.getFreeBID()).get();
        findFreeBoard.setTitle(freeBoard.getTitle());
        findFreeBoard.setContent(freeBoard.getContent());

        freeBoardRepo.save(findFreeBoard);

    }   //updateFreeBoard

    @Override
    public void deleteFreeBoard(FreeBoard freeBoard) {
        freeBoardRepo.deleteById(freeBoard.getFreeBID());

    }   //deleteFreeBoard

    @Override
    public FreeBoard getFreeBoard(FreeBoard freeBoard) {
        FreeBoard findFreeBoard = freeBoardRepo.findById(freeBoard.getFreeBID()).get();
        findFreeBoard.setViewCnt(findFreeBoard.getViewCnt() + 1);
        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt() + 1);
        freeBoardRepo.save(findFreeBoard);

        return findFreeBoard;
    }   //getFreeBoard

    @Override
    public FreeBoard getFreeBoard(Long freeBoard) {

        return freeBoardRepo.findFreeBoardLong(freeBoard);
    }

    @Override
    public FreeBoard registerForm(FreeBoard freeBoard) {
        FreeBoard findFreeBoard = freeBoardRepo.findById(freeBoard.getFreeBID()).get();
        findFreeBoard.setViewCnt(findFreeBoard.getViewCnt() + 1);
        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt() + 1);
        freeBoardRepo.save(findFreeBoard);

        return findFreeBoard;
    }

    @Override
    public Page<FreeBoard> getFreeBoardList(Search search, int page, FreeBoard freeBoard) {
        BooleanBuilder builder = new BooleanBuilder();

        QFreeBoard qFreeBoard = QFreeBoard.freeBoard;

        if (search.getSearchCondition().equals("TITLE")) {
            builder.and(qFreeBoard.title.like("%" + search.getSearchKeyword() + "%"));
            builder.and(qFreeBoard.boardCategory.like("%" + search.getSearchCategory() + "%"));
        } else if (search.getSearchCondition().equals("CONTENT")) {
            builder.and(qFreeBoard.content.like("%" + search.getSearchKeyword() + "%"));
            builder.and(qFreeBoard.boardCategory.like("%" + search.getSearchCategory() + "%"));
        }
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "freeBID");

        freeBoardRepo.getFreeBoardList(pageable);

        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt() + 1);

        return freeBoardRepo.findAll(builder, pageable);
    }

//    @Override
//    public Page<FreeBoard> getFreeBoardNoticeList(Search search, int page, FreeBoard freeBoard) {
//        BooleanBuilder builder = new BooleanBuilder();
//
//        QFreeBoard qFreeBoard = QFreeBoard.freeBoard;
//
//        if (search.getSearchCondition().equals("TITLE")) {
//            builder.and(qFreeBoard.title.like("%" + search.getSearchKeyword() + "%"));
//            builder.and(qFreeBoard.boardCategory.like("%" + search.getSearchCategory() + "%"));
//        } else if (search.getSearchCondition().equals("CONTENT")) {
//            builder.and(qFreeBoard.content.like("%" + search.getSearchKeyword() + "%"));
//            builder.and(qFreeBoard.boardCategory.like("%" + search.getSearchCategory() + "%"));
//        }
//        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "freeBID");
//
//        freeBoardRepo.getFreeBoardNoticeList(pageable);
//
//        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt()+1);
//
//        return freeBoardRepo.findAll(builder, pageable);
//    }

    @Override
    public FreeBoard likeUp(FreeBoard freeBoard) {
        FreeBoard findFreeBoard = freeBoardRepo.findById(freeBoard.getFreeBID()).get();
        findFreeBoard.setRecommendCnt(findFreeBoard.getRecommendCnt() + 1);
        freeBoardRepo.save(findFreeBoard);

        return findFreeBoard;
    }

    @Override
    public FreeBoard likeDown(FreeBoard freeBoard) {
        FreeBoard findFreeBoard = freeBoardRepo.findById(freeBoard.getFreeBID()).get();
        findFreeBoard.setRecommendCnt(findFreeBoard.getRecommendCnt() - 1);
        freeBoardRepo.save(findFreeBoard);

        return findFreeBoard;
    }

    @Override
    public List<LikeCount> getLikeListByFreeBoard(FreeBoard freeBoard) {
        return likeRepo.findAllByfreeBoard(freeBoard);
    }

    @Override
    public List<LikeCount> getLikeListByMember(Members member) {
        return likeRepo.findAllBymembers(member);
    }

    @Override
    public LikeCount getLikeByFbAndMem(Members member, FreeBoard freeBoard) {
        return likeRepo.findLikeCountByMembersAndFreeBoard(member, freeBoard);
    }

    @Override
    public void insertLike(LikeCount likeCount) {
        likeRepo.save(likeCount);
    }

    @Override
    public void deleteLike(long likeID) {
        likeRepo.deleteById(likeID);
    }

    @Override
    public Page<FreeBoard> myfreeBoardList(Search search, Members member, int page) {

        BooleanBuilder builder = new BooleanBuilder();

        QFreeBoard qfreeBoard = QFreeBoard.freeBoard;
        builder.and(qfreeBoard.memberID.eq(member));
        if (search.getSearchCondition().equals("TITLE")) {
            builder.and(qfreeBoard.title.like("%" + search.getSearchKeyword() + "%"));
        }
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "freeBID");

        return freeBoardRepo.findAll(builder, pageable);
    }


}   //class




























