package mz.sixsense.road.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotel is a Querydsl query type for Hotel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotel extends EntityPathBase<Hotel> {

    private static final long serialVersionUID = -273590442L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotel hotel = new QHotel("hotel");

    public final StringPath address = createString("address");

    public final QRoad guganNm;

    public final StringPath homepageUrl = createString("homepageUrl");

    public final StringPath hotelConentcatecory = createString("hotelConentcatecory");

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final StringPath name = createString("name");

    public final StringPath openTime = createString("openTime");

    public final StringPath place = createString("place");

    public final StringPath road_course = createString("road_course");

    public final StringPath road_course_gugan = createString("road_course_gugan");

    public final StringPath seq = createString("seq");

    public final StringPath tel = createString("tel");

    public QHotel(String variable) {
        this(Hotel.class, forVariable(variable), INITS);
    }

    public QHotel(Path<? extends Hotel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotel(PathMetadata metadata, PathInits inits) {
        this(Hotel.class, metadata, inits);
    }

    public QHotel(Class<? extends Hotel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guganNm = inits.isInitialized("guganNm") ? new QRoad(forProperty("guganNm"), inits.get("guganNm")) : null;
    }

}

