package mz.sixsense.shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductGrade is a Querydsl query type for ProductGrade
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductGrade extends EntityPathBase<ProductGrade> {

    private static final long serialVersionUID = 314639644L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductGrade productGrade = new QProductGrade("productGrade");

    public final NumberPath<Double> grades = createNumber("grades", Double.class);

    public final NumberPath<Long> gradeSeq = createNumber("gradeSeq", Long.class);

    public final mz.sixsense.member.entity.QMembers member;

    public final QProducts product;

    public QProductGrade(String variable) {
        this(ProductGrade.class, forVariable(variable), INITS);
    }

    public QProductGrade(Path<? extends ProductGrade> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductGrade(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductGrade(PathMetadata metadata, PathInits inits) {
        this(ProductGrade.class, metadata, inits);
    }

    public QProductGrade(Class<? extends ProductGrade> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new mz.sixsense.member.entity.QMembers(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new QProducts(forProperty("product"), inits.get("product")) : null;
    }

}

