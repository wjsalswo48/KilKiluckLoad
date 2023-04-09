package mz.sixsense.calendar.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodayCheck is a Querydsl query type for TodayCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayCheck extends EntityPathBase<TodayCheck> {

    private static final long serialVersionUID = -927830525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodayCheck todayCheck = new QTodayCheck("todayCheck");

    public final StringPath checkDate = createString("checkDate");

    public final mz.sixsense.member.entity.QMembers memberID;

    public final NumberPath<Long> todayCheckID = createNumber("todayCheckID", Long.class);

    public QTodayCheck(String variable) {
        this(TodayCheck.class, forVariable(variable), INITS);
    }

    public QTodayCheck(Path<? extends TodayCheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodayCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodayCheck(PathMetadata metadata, PathInits inits) {
        this(TodayCheck.class, metadata, inits);
    }

    public QTodayCheck(Class<? extends TodayCheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberID = inits.isInitialized("memberID") ? new mz.sixsense.member.entity.QMembers(forProperty("memberID")) : null;
    }

}

