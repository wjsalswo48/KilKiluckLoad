package mz.sixsense.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMembers is a Querydsl query type for Members
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMembers extends EntityPathBase<Members> {

    private static final long serialVersionUID = 2079316929L;

    public static final QMembers members = new QMembers("members");

    public final StringPath adminCode = createString("adminCode");

    public final StringPath birthDate = createString("birthDate");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final ListPath<mz.sixsense.shop.entity.Delivery, mz.sixsense.shop.entity.QDelivery> deliveryList = this.<mz.sixsense.shop.entity.Delivery, mz.sixsense.shop.entity.QDelivery>createList("deliveryList", mz.sixsense.shop.entity.Delivery.class, mz.sixsense.shop.entity.QDelivery.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final ListPath<mz.sixsense.board.entity.FreeBoard, mz.sixsense.board.entity.QFreeBoard> freeBoardList = this.<mz.sixsense.board.entity.FreeBoard, mz.sixsense.board.entity.QFreeBoard>createList("freeBoardList", mz.sixsense.board.entity.FreeBoard.class, mz.sixsense.board.entity.QFreeBoard.class, PathInits.DIRECT2);

    public final StringPath gender = createString("gender");

    public final ListPath<mz.sixsense.board.like.entity.LikeCount, mz.sixsense.board.like.entity.QLikeCount> likeCountList = this.<mz.sixsense.board.like.entity.LikeCount, mz.sixsense.board.like.entity.QLikeCount>createList("likeCountList", mz.sixsense.board.like.entity.LikeCount.class, mz.sixsense.board.like.entity.QLikeCount.class, PathInits.DIRECT2);

    public final StringPath memberID = createString("memberID");

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final ListPath<mz.sixsense.member.history.entity.OrderHistory, mz.sixsense.member.history.entity.QOrderHistory> orderHistoryList = this.<mz.sixsense.member.history.entity.OrderHistory, mz.sixsense.member.history.entity.QOrderHistory>createList("orderHistoryList", mz.sixsense.member.history.entity.OrderHistory.class, mz.sixsense.member.history.entity.QOrderHistory.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final ListPath<mz.sixsense.member.history.entity.PointHistory, mz.sixsense.member.history.entity.QPointHistory> pointHistoryList = this.<mz.sixsense.member.history.entity.PointHistory, mz.sixsense.member.history.entity.QPointHistory>createList("pointHistoryList", mz.sixsense.member.history.entity.PointHistory.class, mz.sixsense.member.history.entity.QPointHistory.class, PathInits.DIRECT2);

    public final ListPath<mz.sixsense.shop.entity.ProductGrade, mz.sixsense.shop.entity.QProductGrade> productGradeList = this.<mz.sixsense.shop.entity.ProductGrade, mz.sixsense.shop.entity.QProductGrade>createList("productGradeList", mz.sixsense.shop.entity.ProductGrade.class, mz.sixsense.shop.entity.QProductGrade.class, PathInits.DIRECT2);

    public final NumberPath<Integer> qrFlag = createNumber("qrFlag", Integer.class);

    public final DateTimePath<java.util.Date> recentLogin = createDateTime("recentLogin", java.util.Date.class);

    public final ListPath<mz.sixsense.board.reply.entity.Reply, mz.sixsense.board.reply.entity.QReply> replyList = this.<mz.sixsense.board.reply.entity.Reply, mz.sixsense.board.reply.entity.QReply>createList("replyList", mz.sixsense.board.reply.entity.Reply.class, mz.sixsense.board.reply.entity.QReply.class, PathInits.DIRECT2);

    public final ListPath<mz.sixsense.board.entity.ReportBoard, mz.sixsense.board.entity.QReportBoard> reportBoardList = this.<mz.sixsense.board.entity.ReportBoard, mz.sixsense.board.entity.QReportBoard>createList("reportBoardList", mz.sixsense.board.entity.ReportBoard.class, mz.sixsense.board.entity.QReportBoard.class, PathInits.DIRECT2);

    public final EnumPath<mz.sixsense.enums.Role> role = createEnum("role", mz.sixsense.enums.Role.class);

    public final ListPath<mz.sixsense.calendar.entity.TodayCheck, mz.sixsense.calendar.entity.QTodayCheck> todayCheckList = this.<mz.sixsense.calendar.entity.TodayCheck, mz.sixsense.calendar.entity.QTodayCheck>createList("todayCheckList", mz.sixsense.calendar.entity.TodayCheck.class, mz.sixsense.calendar.entity.QTodayCheck.class, PathInits.DIRECT2);

    public final NumberPath<Long> totalPoint = createNumber("totalPoint", Long.class);

    public QMembers(String variable) {
        super(Members.class, forVariable(variable));
    }

    public QMembers(Path<? extends Members> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMembers(PathMetadata metadata) {
        super(Members.class, metadata);
    }

}

