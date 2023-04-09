package mz.sixsense.road.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QToilet is a Querydsl query type for Toilet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToilet extends EntityPathBase<Toilet> {

    private static final long serialVersionUID = 451859627L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QToilet toilet = new QToilet("toilet");

    public final StringPath address_j = createString("address_j");

    public final StringPath address_r = createString("address_r");

    public final QRoad guganNm;

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final StringPath manager = createString("manager");

    public final StringPath manager_tel_no = createString("manager_tel_no");

    public final StringPath name = createString("name");

    public final StringPath openTime = createString("openTime");

    public final StringPath road_course = createString("road_course");

    public final StringPath road_course_gugan = createString("road_course_gugan");

    public final StringPath roadConentcatecory = createString("roadConentcatecory");

    public QToilet(String variable) {
        this(Toilet.class, forVariable(variable), INITS);
    }

    public QToilet(Path<? extends Toilet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QToilet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QToilet(PathMetadata metadata, PathInits inits) {
        this(Toilet.class, metadata, inits);
    }

    public QToilet(Class<? extends Toilet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guganNm = inits.isInitialized("guganNm") ? new QRoad(forProperty("guganNm"), inits.get("guganNm")) : null;
    }

}

