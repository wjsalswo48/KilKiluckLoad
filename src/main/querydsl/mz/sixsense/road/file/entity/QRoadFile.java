package mz.sixsense.road.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoadFile is a Querydsl query type for RoadFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoadFile extends EntityPathBase<RoadFile> {

    private static final long serialVersionUID = -561224542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoadFile roadFile = new QRoadFile("roadFile");

    public final mz.sixsense.road.entity.QRoad guganNm;

    public final StringPath orginName = createString("orginName");

    public final NumberPath<Long> roadVideoID = createNumber("roadVideoID", Long.class);

    public final StringPath saveName = createString("saveName");

    public final StringPath savePath = createString("savePath");

    public QRoadFile(String variable) {
        this(RoadFile.class, forVariable(variable), INITS);
    }

    public QRoadFile(Path<? extends RoadFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoadFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoadFile(PathMetadata metadata, PathInits inits) {
        this(RoadFile.class, metadata, inits);
    }

    public QRoadFile(Class<? extends RoadFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guganNm = inits.isInitialized("guganNm") ? new mz.sixsense.road.entity.QRoad(forProperty("guganNm"), inits.get("guganNm")) : null;
    }

}

