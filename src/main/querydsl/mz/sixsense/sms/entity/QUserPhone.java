package mz.sixsense.sms.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPhone is a Querydsl query type for UserPhone
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPhone extends EntityPathBase<UserPhone> {

    private static final long serialVersionUID = -1291832306L;

    public static final QUserPhone userPhone = new QUserPhone("userPhone");

    public final StringPath memberPhone = createString("memberPhone");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QUserPhone(String variable) {
        super(UserPhone.class, forVariable(variable));
    }

    public QUserPhone(Path<? extends UserPhone> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPhone(PathMetadata metadata) {
        super(UserPhone.class, metadata);
    }

}

