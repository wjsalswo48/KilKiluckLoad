package mz.sixsense.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoard is a Querydsl query type for FreeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoard extends EntityPathBase<FreeBoard> {

    private static final long serialVersionUID = -1234555560L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeBoard freeBoard = new QFreeBoard("freeBoard");

    public final StringPath boardCategory = createString("boardCategory");

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Long> fileID = createNumber("fileID", Long.class);

    public final NumberPath<Long> freeBID = createNumber("freeBID", Long.class);

    public final ListPath<mz.sixsense.board.file.entity.FreeBoardFile, mz.sixsense.board.file.entity.QFreeBoardFile> freeBoardFileList = this.<mz.sixsense.board.file.entity.FreeBoardFile, mz.sixsense.board.file.entity.QFreeBoardFile>createList("freeBoardFileList", mz.sixsense.board.file.entity.FreeBoardFile.class, mz.sixsense.board.file.entity.QFreeBoardFile.class, PathInits.DIRECT2);

    public final ListPath<mz.sixsense.board.like.entity.LikeCount, mz.sixsense.board.like.entity.QLikeCount> likeCountList = this.<mz.sixsense.board.like.entity.LikeCount, mz.sixsense.board.like.entity.QLikeCount>createList("likeCountList", mz.sixsense.board.like.entity.LikeCount.class, mz.sixsense.board.like.entity.QLikeCount.class, PathInits.DIRECT2);

    public final mz.sixsense.member.entity.QMembers memberID;

    public final NumberPath<Integer> recommendCnt = createNumber("recommendCnt", Integer.class);

    public final NumberPath<Integer> replyCnt = createNumber("replyCnt", Integer.class);

    public final ListPath<mz.sixsense.board.reply.entity.Reply, mz.sixsense.board.reply.entity.QReply> replyList = this.<mz.sixsense.board.reply.entity.Reply, mz.sixsense.board.reply.entity.QReply>createList("replyList", mz.sixsense.board.reply.entity.Reply.class, mz.sixsense.board.reply.entity.QReply.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> viewCnt = createNumber("viewCnt", Integer.class);

    public QFreeBoard(String variable) {
        this(FreeBoard.class, forVariable(variable), INITS);
    }

    public QFreeBoard(Path<? extends FreeBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFreeBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFreeBoard(PathMetadata metadata, PathInits inits) {
        this(FreeBoard.class, metadata, inits);
    }

    public QFreeBoard(Class<? extends FreeBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberID = inits.isInitialized("memberID") ? new mz.sixsense.member.entity.QMembers(forProperty("memberID")) : null;
    }

}

