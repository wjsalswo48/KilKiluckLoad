package mz.sixsense.road.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTouristAttaction is a Querydsl query type for TouristAttaction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTouristAttaction extends EntityPathBase<TouristAttaction> {

    private static final long serialVersionUID = 1707398691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTouristAttaction touristAttaction = new QTouristAttaction("touristAttaction");

    public final StringPath category = createString("category");

    public final QRoad guganNm;

    public final StringPath latitude = createString("latitude");

    public final StringPath longtitude = createString("longtitude");

    public final StringPath mainImage = createString("mainImage");

    public final StringPath name = createString("name");

    public final StringPath place = createString("place");

    public final StringPath road_course = createString("road_course");

    public final StringPath road_course_gugan = createString("road_course_gugan");

    public final StringPath seq = createString("seq");

    public final StringPath text = createString("text");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public QTouristAttaction(String variable) {
        this(TouristAttaction.class, forVariable(variable), INITS);
    }

    public QTouristAttaction(Path<? extends TouristAttaction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTouristAttaction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTouristAttaction(PathMetadata metadata, PathInits inits) {
        this(TouristAttaction.class, metadata, inits);
    }

    public QTouristAttaction(Class<? extends TouristAttaction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guganNm = inits.isInitialized("guganNm") ? new QRoad(forProperty("guganNm"), inits.get("guganNm")) : null;
    }

}

