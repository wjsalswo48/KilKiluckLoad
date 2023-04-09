package mz.sixsense.board.reply.service.impl;

import com.querydsl.core.BooleanBuilder;
import mz.sixsense.board.Search;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.reply.entity.QReply;
import mz.sixsense.board.reply.entity.Reply;
import mz.sixsense.board.reply.repository.ReplyRepository;
import mz.sixsense.board.reply.service.ReplyService;
import mz.sixsense.board.repository.FreeBoardRepository;
import mz.sixsense.member.entity.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepo;

    @Autowired
    private FreeBoardRepository freeBoardRepo;

    @Override
    @Transactional
    public Reply replyWrite(Reply reply, Members members, FreeBoard freeBoard) {
        reply.setBoard(freeBoard);
        reply.setMemberID(members);

        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt());

        return replyRepo.save(reply);
    }

    @Override
    public Page<Reply> getReplyList(Long freeBID, int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "replyID");

        return replyRepo.selectReplyAllFind(freeBID, pageable);
    }

    @Override
    public void replyDelete(Reply reply) {
        replyRepo.deleteById(reply.getReplyID());
    }

    @Override
    public void replyUpdate(Reply reply) {
        Reply findReply = replyRepo.findById(reply.getReplyID()).get();
        findReply.setContent(reply.getContent());

        replyRepo.save(findReply);

    }

    @Override
    public Reply getReplyID(Long replyID) {

        return replyRepo.findReplyID(replyID);
    }

    @Override
    public String getReplyContent(String Content) {
        return null;
    }

    @Override
    public Page<Reply> myreplyList(Search search, Members member, int page) {

        BooleanBuilder builder = new BooleanBuilder();

        QReply qreply = QReply.reply;
        builder.and(qreply.memberID.eq(member));
        if(search.getSearchCondition().equals("TITLE")){
            builder.and(qreply.content.like("%" + search.getSearchKeyword() + "%"));
        }
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "replyID");

        return replyRepo.findAll(builder, pageable);
    }

}
