package mz.sixsense.shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = 1616968424L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final NumberPath<Long> deliveryID = createNumber("deliveryID", Long.class);

    public final EnumPath<mz.sixsense.shop.domain.DeliveryStatus> deliveryStatus = createEnum("deliveryStatus", mz.sixsense.shop.domain.DeliveryStatus.class);

    public final mz.sixsense.member.entity.QMembers memberID;

    public final NumberPath<Integer> orderAmount = createNumber("orderAmount", Integer.class);

    public final ListPath<Products, QProducts> productsList = this.<Products, QProducts>createList("productsList", Products.class, QProducts.class, PathInits.DIRECT2);

    public QDelivery(String variable) {
        this(Delivery.class, forVariable(variable), INITS);
    }

    public QDelivery(Path<? extends Delivery> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDelivery(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDelivery(PathMetadata metadata, PathInits inits) {
        this(Delivery.class, metadata, inits);
    }

    public QDelivery(Class<? extends Delivery> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberID = inits.isInitialized("memberID") ? new mz.sixsense.member.entity.QMembers(forProperty("memberID")) : null;
    }

}

