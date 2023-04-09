package mz.sixsense.road.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhotoZone is a Querydsl query type for PhotoZone
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhotoZone extends EntityPathBase<PhotoZone> {

    private static final long serialVersionUID = 1515413920L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhotoZone photoZone = new QPhotoZone("photoZone");

    public final StringPath address = createString("address");

    public final QRoad guganNm;

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final StringPath name = createString("name");

    public final StringPath pos = createString("pos");

    public final StringPath road_course = createString("road_course");

    public final StringPath road_course_gugan = createString("road_course_gugan");

    public final StringPath roadConentcatecory = createString("roadConentcatecory");

    public final StringPath seq = createString("seq");

    public final StringPath state = createString("state");

    public QPhotoZone(String variable) {
        this(PhotoZone.class, forVariable(variable), INITS);
    }

    public QPhotoZone(Path<? extends PhotoZone> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhotoZone(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhotoZone(PathMetadata metadata, PathInits inits) {
        this(PhotoZone.class, metadata, inits);
    }

    public QPhotoZone(Class<? extends PhotoZone> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guganNm = inits.isInitialized("guganNm") ? new QRoad(forProperty("guganNm"), inits.get("guganNm")) : null;
    }

}

