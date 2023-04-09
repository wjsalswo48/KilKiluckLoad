package mz.sixsense.board.reply.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.member.entity.Members;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"member", "freeBoard"})
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long replyID;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate = new Date();
    private Long recommendCnt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freeBID")
    private FreeBoard freeBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberID")
    private Members memberID;

//    public void setMember(Members members) {
//        this.memberID = members;
//        members.getReplyList().add(this);
//    }

    public void setBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
        freeBoard.getReplyList().add(this);
    }


}
