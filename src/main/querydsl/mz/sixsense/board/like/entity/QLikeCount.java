package mz.sixsense.board.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeCount is a Querydsl query type for LikeCount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeCount extends EntityPathBase<LikeCount> {

    private static final long serialVersionUID = 698098811L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeCount likeCount = new QLikeCount("likeCount");

    public final mz.sixsense.board.entity.QFreeBoard freeBoard;

    public final NumberPath<Long> likeCheck = createNumber("likeCheck", Long.class);

    public final NumberPath<Long> likeID = createNumber("likeID", Long.class);

    public final mz.sixsense.member.entity.QMembers members;

    public QLikeCount(String variable) {
        this(LikeCount.class, forVariable(variable), INITS);
    }

    public QLikeCount(Path<? extends LikeCount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeCount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeCount(PathMetadata metadata, PathInits inits) {
        this(LikeCount.class, metadata, inits);
    }

    public QLikeCount(Class<? extends LikeCount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.freeBoard = inits.isInitialized("freeBoard") ? new mz.sixsense.board.entity.QFreeBoard(forProperty("freeBoard"), inits.get("freeBoard")) : null;
        this.members = inits.isInitialized("members") ? new mz.sixsense.member.entity.QMembers(forProperty("members")) : null;
    }

}

