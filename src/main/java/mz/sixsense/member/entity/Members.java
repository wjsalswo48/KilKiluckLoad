package mz.sixsense.member.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.entity.ReportBoard;
import mz.sixsense.board.like.entity.LikeCount;
import mz.sixsense.board.reply.entity.Reply;
import mz.sixsense.calendar.entity.TodayCheck;
import mz.sixsense.enums.Role;
import mz.sixsense.member.history.entity.OrderHistory;
import mz.sixsense.member.history.entity.PointHistory;
import mz.sixsense.shop.entity.Delivery;
import mz.sixsense.shop.entity.ProductGrade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"freeBoardList", "replyList", "pointHistoryList", "deliveryList", "orderHistoryList",
        "productGradeList", "reportBoardList", "todayCheckList", "likeCountList"})
public class Members {


    @Id
    @Column(name = "MEMBERID")
    private String memberID;

    private String name;

//    private int age;
    private String password;
    private String nickName;
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate = new Date();

    private String birthDate;

    @Column(columnDefinition = "number default 0")
    private Long totalPoint = 0L;

    private int qrFlag;

    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String adminCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date recentLogin;

   // @OneToMany(mappedBy = "memberID", fetch = FetchType.LAZY)

    @OneToMany(mappedBy = "memberID", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReportBoard> reportBoardList = new ArrayList<ReportBoard>();
//    @OneToMany(mappedBy = "memberID")
//    private List<ReportBoard> reportBoardList = new ArrayList<>();
    
    @OneToMany(mappedBy = "memberID", cascade = CascadeType.ALL)
    private List<FreeBoard> freeBoardList = new ArrayList<FreeBoard>();

    @OneToMany(mappedBy = "memberID", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<Reply>();

    @OneToMany(mappedBy = "memberID", cascade = CascadeType.ALL)
    private List<TodayCheck> todayCheckList = new ArrayList<>();

    @OneToMany(mappedBy = "memberID", cascade = CascadeType.ALL)
    private List<PointHistory> pointHistoryList = new ArrayList<>();

//    @OneToMany(mappedBy = "memberID", fetch = FetchType.LAZY)
    
    @OneToMany(mappedBy = "memberID", cascade = CascadeType.ALL)
    private List<Delivery> deliveryList = new ArrayList<Delivery>();

    @OneToMany(mappedBy = "memberID", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderHistory> orderHistoryList = new ArrayList<>();


//    private List<FreeBoard> freeBoardList = new ArrayList<FreeBoard>();
//
//    @OneToMany(mappedBy = "memberID", fetch = FetchType.EAGER)
//    private List<Reply> replyList = new ArrayList<Reply>();
//
//    @OneToMany(mappedBy = "memberID")
//    private List<PointHistory> pointHistoryList = new ArrayList<PointHistory>();
//
//    @OneToMany(mappedBy = "memberID")
//    private List<Delivery> deliveryList = new ArrayList<Delivery>();
//
//    @OneToMany(mappedBy = "memberID")
//    private List<OrderHistory> orderHistoryList = new ArrayList<OrderHistory>();

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ProductGrade> productGradeList = new ArrayList<ProductGrade>();

    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
    private List<LikeCount> likeCountList = new ArrayList<>();





}
