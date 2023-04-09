package mz.sixsense.shop.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductFile is a Querydsl query type for ProductFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductFile extends EntityPathBase<ProductFile> {

    private static final long serialVersionUID = 1521451835L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductFile productFile = new QProductFile("productFile");

    public final NumberPath<Long> fileID = createNumber("fileID", Long.class);

    public final StringPath originName = createString("originName");

    public final mz.sixsense.shop.entity.QProducts product;

    public final StringPath saveName = createString("saveName");

    public final StringPath savePath = createString("savePath");

    public QProductFile(String variable) {
        this(ProductFile.class, forVariable(variable), INITS);
    }

    public QProductFile(Path<? extends ProductFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductFile(PathMetadata metadata, PathInits inits) {
        this(ProductFile.class, metadata, inits);
    }

    public QProductFile(Class<? extends ProductFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new mz.sixsense.shop.entity.QProducts(forProperty("product"), inits.get("product")) : null;
    }

}

