package mz.sixsense.road.qr.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQRData is a Querydsl query type for QRData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQRData extends EntityPathBase<QRData> {

    private static final long serialVersionUID = 569637334L;

    public static final QQRData qRData = new QQRData("qRData");

    public final NumberPath<Long> avgTime = createNumber("avgTime", Long.class);

    public final StringPath endPoint = createString("endPoint");

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    public final NumberPath<Long> roadData = createNumber("roadData", Long.class);

    public final StringPath startingPoint = createString("startingPoint");

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public QQRData(String variable) {
        super(QRData.class, forVariable(variable));
    }

    public QQRData(Path<? extends QRData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQRData(PathMetadata metadata) {
        super(QRData.class, metadata);
    }

}

