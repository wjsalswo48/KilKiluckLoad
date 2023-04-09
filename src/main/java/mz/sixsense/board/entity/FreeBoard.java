package mz.sixsense.board.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mz.sixsense.board.file.entity.FreeBoardFile;
import mz.sixsense.board.like.entity.LikeCount;
import mz.sixsense.board.reply.entity.Reply;
import mz.sixsense.member.entity.Members;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"members", "freeBoardFileList", "replyList", "likeCountList"})
public class FreeBoard {

    @Id
    @GeneratedValue
    private Long freeBID;

    private String title;

    private String content;

    private int viewCnt;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate = new Date();

    @Column(columnDefinition = "number default 0")
    private int recommendCnt;
    @Column(columnDefinition = "number default 0")
    private int replyCnt;

    private String boardCategory;

    private Long fileID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberID", nullable = false, updatable = false)
    private Members memberID;

    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL)
    private List<FreeBoardFile> freeBoardFileList = new ArrayList<>();

    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL)
    private List<LikeCount> likeCountList = new ArrayList<>();

    public void setMember(Members members){
        this.memberID = members;
        members.getFreeBoardList().add(this);
    }


}
