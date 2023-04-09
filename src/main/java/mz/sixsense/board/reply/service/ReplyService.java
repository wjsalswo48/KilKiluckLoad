package mz.sixsense.board.reply.service;

import mz.sixsense.board.Search;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.reply.entity.Reply;
import mz.sixsense.member.entity.Members;
import org.springframework.data.domain.Page;

public interface ReplyService {

    Reply replyWrite(Reply reply, Members members, FreeBoard freeBoard);

    Page<Reply> getReplyList(Long freeBID, int page);

    void replyDelete(Reply reply);

    void replyUpdate(Reply reply);

    Reply getReplyID(Long replyID);

    String getReplyContent(String Content);

    Page<Reply> myreplyList(Search search, Members member, int page);

}
