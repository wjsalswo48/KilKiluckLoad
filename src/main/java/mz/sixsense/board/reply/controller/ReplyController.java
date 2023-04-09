package mz.sixsense.board.reply.controller;

import lombok.RequiredArgsConstructor;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.reply.entity.Reply;
import mz.sixsense.board.reply.service.ReplyService;
import mz.sixsense.board.repository.FreeBoardRepository;
import mz.sixsense.board.service.FreeBoardService;
import mz.sixsense.member.entity.Members;
import mz.sixsense.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private FreeBoardService freeBoardService;

    @Autowired
    private FreeBoardRepository freeBoardRepo;

    @PostMapping("/replyWrite")
    @Transactional
    public String replyWrite(@ModelAttribute Reply reply, FreeBoard freeBoard,
                             @AuthenticationPrincipal SecurityUser principal) {
        Members members = principal.getMember();
        System.out.println("reply.getContent==========================>" + reply.getContent());
        replyService.replyWrite(reply, members, freeBoard);


        FreeBoard findFreeBoard = freeBoardRepo.findById(freeBoard.getFreeBID()).get();
        findFreeBoard.setReplyCnt(findFreeBoard.getReplyCnt() + 1);
        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt() + 1);
        freeBoardRepo.save(findFreeBoard);

        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt());

        return "redirect:/board/getFreeBoard?freeBID=" + freeBoard.getFreeBID();
    }


    @GetMapping("/replyDelete")
    public String replyDelete(Reply reply, FreeBoard freeBoard) {
        replyService.replyDelete(reply);

        FreeBoard findFreeBoard = freeBoardRepo.findById(freeBoard.getFreeBID()).get();
        findFreeBoard.setReplyCnt(findFreeBoard.getReplyCnt() - 1);
        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt() - 1);
        freeBoardRepo.save(findFreeBoard);

        freeBoardRepo.updateReplyCntBy(freeBoard.getReplyCnt());

        return "redirect:/board/getFreeBoard?freeBID=" + freeBoard.getFreeBID();
    }

    @PostMapping("/replyUpdate")
    public String replyUpdate(Reply reply, FreeBoard freeBoard) {
        replyService.replyUpdate(reply);

        return "redirect:/board/getFreeBoard?freeBID=" + freeBoard.getFreeBID();
    }

    @GetMapping("/replyRegister")
    public String replyRegister(Model model, Long freeBID, Long replyID, String Content){
        FreeBoard freeBoard = freeBoardService.getFreeBoard(freeBID);
        Reply getReply = replyService.getReplyID(replyID);
        String getContent = replyService.getReplyContent(Content);

        model.addAttribute("freeBID", freeBoard);
        model.addAttribute("replyID", getReply);
        model.addAttribute("Content", getContent);

        return "board/replyRegister";
    }


}
