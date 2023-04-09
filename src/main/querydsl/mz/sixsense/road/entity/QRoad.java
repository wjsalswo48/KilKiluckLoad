package mz.sixsense.road.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoad is a Querydsl query type for Road
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoad extends EntityPathBase<Road> {

    private static final long serialVersionUID = -424170178L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoad road = new QRoad("road");

    public final ListPath<Restaurant, QRestaurant> arroundRestaurantList = this.<Restaurant, QRestaurant>createList("arroundRestaurantList", Restaurant.class, QRestaurant.class, PathInits.DIRECT2);

    public final StringPath avgTime = createString("avgTime");

    public final QCourse courseId;

    public final StringPath end_lat = createString("end_lat");

    public final StringPath end_long = createString("end_long");

    public final StringPath endAddr = createString("endAddr");

    public final StringPath endPls = createString("endPls");

    public final StringPath gmCourse = createString("gmCourse");

    public final StringPath gmDegree = createString("gmDegree");

    public final StringPath gmText = createString("gmText");

    public final StringPath guganNm = createString("guganNm");

    public final ListPath<Hotel, QHotel> hotelList = this.<Hotel, QHotel>createList("hotelList", Hotel.class, QHotel.class, PathInits.DIRECT2);

    public final StringPath img_path = createString("img_path");

    public final StringPath middleAdr = createString("middleAdr");

    public final StringPath middlePls = createString("middlePls");

    public final ListPath<PhotoZone, QPhotoZone> photoZoneList = this.<PhotoZone, QPhotoZone>createList("photoZoneList", PhotoZone.class, QPhotoZone.class, PathInits.DIRECT2);

    public final StringPath Range = createString("Range");

    public final ListPath<mz.sixsense.road.file.entity.RoadFile, mz.sixsense.road.file.entity.QRoadFile> roadFileList = this.<mz.sixsense.road.file.entity.RoadFile, mz.sixsense.road.file.entity.QRoadFile>createList("roadFileList", mz.sixsense.road.file.entity.RoadFile.class, mz.sixsense.road.file.entity.QRoadFile.class, PathInits.DIRECT2);

    public final StringPath start_lat = createString("start_lat");

    public final StringPath start_long = createString("start_long");

    public final StringPath startAddr = createString("startAddr");

    public final StringPath startPls = createString("startPls");

    public final ListPath<Toilet, QToilet> toiletList = this.<Toilet, QToilet>createList("toiletList", Toilet.class, QToilet.class, PathInits.DIRECT2);

    public final ListPath<TouristAttaction, QTouristAttaction> touristAttactionList = this.<TouristAttaction, QTouristAttaction>createList("touristAttactionList", TouristAttaction.class, QTouristAttaction.class, PathInits.DIRECT2);

    public final NumberPath<Long> visits = createNumber("visits", Long.class);

    public QRoad(String variable) {
        this(Road.class, forVariable(variable), INITS);
    }

    public QRoad(Path<? extends Road> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoad(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoad(PathMetadata metadata, PathInits inits) {
        this(Road.class, metadata, inits);
    }

    public QRoad(Class<? extends Road> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseId = inits.isInitialized("courseId") ? new QCourse(forProperty("courseId")) : null;
    }

}

