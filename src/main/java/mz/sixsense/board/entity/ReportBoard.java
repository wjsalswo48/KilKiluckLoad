package mz.sixsense.board.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mz.sixsense.board.file.entity.ReportBoardFile;
import mz.sixsense.member.entity.Members;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@ToString(exclude = {"members", "reportBoard"})
public class ReportBoard {

    @Id
    @GeneratedValue
    private Long reportID;

    private String title;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate = new Date();

    private String reportStatus;

    private String boardCategory;
    
    private Long fileID;//추가함

    @ManyToOne
    @JoinColumn(name = "memberID", nullable = false, updatable = false)
    private Members memberID;

    @OneToMany(mappedBy = "reportBoard", cascade = CascadeType.ALL)
    private List<ReportBoardFile> reportBoardFileList = new ArrayList<>();

//    public void setMember(Members members){
//        this.memberID = members;
//        members.getReportBoardList().add(this);
//    }
}
