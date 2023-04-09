package mz.sixsense.shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProducts is a Querydsl query type for Products
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProducts extends EntityPathBase<Products> {

    private static final long serialVersionUID = -210259880L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProducts products = new QProducts("products");

    public final NumberPath<Long> cashPrice = createNumber("cashPrice", Long.class);

    public final StringPath content = createString("content");

    public final QDelivery delivery;

    public final NumberPath<Long> fileID = createNumber("fileID", Long.class);

    public final NumberPath<Double> grade = createNumber("grade", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> pointPrice = createNumber("pointPrice", Long.class);

    public final StringPath productCategory = createString("productCategory");

    public final ListPath<mz.sixsense.shop.file.entity.ProductFile, mz.sixsense.shop.file.entity.QProductFile> productFileList = this.<mz.sixsense.shop.file.entity.ProductFile, mz.sixsense.shop.file.entity.QProductFile>createList("productFileList", mz.sixsense.shop.file.entity.ProductFile.class, mz.sixsense.shop.file.entity.QProductFile.class, PathInits.DIRECT2);

    public final ListPath<ProductGrade, QProductGrade> productGradeList = this.<ProductGrade, QProductGrade>createList("productGradeList", ProductGrade.class, QProductGrade.class, PathInits.DIRECT2);

    public final NumberPath<Long> productID = createNumber("productID", Long.class);

    public final NumberPath<Long> stock = createNumber("stock", Long.class);

    public QProducts(String variable) {
        this(Products.class, forVariable(variable), INITS);
    }

    public QProducts(Path<? extends Products> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProducts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProducts(PathMetadata metadata, PathInits inits) {
        this(Products.class, metadata, inits);
    }

    public QProducts(Class<? extends Products> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
    }

}

