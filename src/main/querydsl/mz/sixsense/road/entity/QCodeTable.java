package mz.sixsense.road.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCodeTable is a Querydsl query type for CodeTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCodeTable extends EntityPathBase<CodeTable> {

    private static final long serialVersionUID = 1225473667L;

    public static final QCodeTable codeTable = new QCodeTable("codeTable");

    public final StringPath CodeName = createString("CodeName");

    public final StringPath CodeValueString = createString("CodeValueString");

    public QCodeTable(String variable) {
        super(CodeTable.class, forVariable(variable));
    }

    public QCodeTable(Path<? extends CodeTable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodeTable(PathMetadata metadata) {
        super(CodeTable.class, metadata);
    }

}

