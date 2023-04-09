package mz.sixsense.member.history.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderHistory is a Querydsl query type for OrderHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderHistory extends EntityPathBase<OrderHistory> {

    private static final long serialVersionUID = 1638208100L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderHistory orderHistory = new QOrderHistory("orderHistory");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final mz.sixsense.member.entity.QMembers memberID;

    public final DateTimePath<java.util.Date> orderDate = createDateTime("orderDate", java.util.Date.class);

    public final NumberPath<Long> orderHistoryID = createNumber("orderHistoryID", Long.class);

    public final StringPath productName = createString("productName");

    public QOrderHistory(String variable) {
        this(OrderHistory.class, forVariable(variable), INITS);
    }

    public QOrderHistory(Path<? extends OrderHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderHistory(PathMetadata metadata, PathInits inits) {
        this(OrderHistory.class, metadata, inits);
    }

    public QOrderHistory(Class<? extends OrderHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberID = inits.isInitialized("memberID") ? new mz.sixsense.member.entity.QMembers(forProperty("memberID")) : null;
    }

}

