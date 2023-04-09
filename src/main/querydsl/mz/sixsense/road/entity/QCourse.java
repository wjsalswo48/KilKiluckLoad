package mz.sixsense.road.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = -34472263L;

    public static final QCourse course = new QCourse("course");

    public final StringPath courseID = createString("courseID");

    public final StringPath img_path = createString("img_path");

    public final ListPath<Road, QRoad> roadList = this.<Road, QRoad>createList("roadList", Road.class, QRoad.class, PathInits.DIRECT2);

    public final StringPath total_length = createString("total_length");

    public final StringPath total_time = createString("total_time");

    public QCourse(String variable) {
        super(Course.class, forVariable(variable));
    }

    public QCourse(Path<? extends Course> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourse(PathMetadata metadata) {
        super(Course.class, metadata);
    }

}

