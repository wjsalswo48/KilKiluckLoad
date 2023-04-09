package mz.sixsense.board.like.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.member.entity.Members;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"freeBoard", "members"})
public class LikeCount {
    @Id
    @GeneratedValue
    private Long likeID;

    @ManyToOne
    @JoinColumn(name = "freeBoard")
    private FreeBoard freeBoard;

    public void setFreeBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
        freeBoard.getLikeCountList().add(this);
    }

    @ManyToOne
    @JoinColumn(name = "members")
    private Members members;

    public void setMembers(Members members) {
        this.members = members;
        members.getLikeCountList().add(this);
    }
    private Long likeCheck = 0L;
}
