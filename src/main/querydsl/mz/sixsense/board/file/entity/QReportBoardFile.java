package mz.sixsense.board.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportBoardFile is a Querydsl query type for ReportBoardFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportBoardFile extends EntityPathBase<ReportBoardFile> {

    private static final long serialVersionUID = -641322580L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportBoardFile reportBoardFile = new QReportBoardFile("reportBoardFile");

    public final NumberPath<Long> fileID = createNumber("fileID", Long.class);

    public final StringPath originName = createString("originName");

    public final mz.sixsense.board.entity.QReportBoard reportBoard;

    public final StringPath saveName = createString("saveName");

    public final StringPath savPath = createString("savPath");

    public QReportBoardFile(String variable) {
        this(ReportBoardFile.class, forVariable(variable), INITS);
    }

    public QReportBoardFile(Path<? extends ReportBoardFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportBoardFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportBoardFile(PathMetadata metadata, PathInits inits) {
        this(ReportBoardFile.class, metadata, inits);
    }

    public QReportBoardFile(Class<? extends ReportBoardFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reportBoard = inits.isInitialized("reportBoard") ? new mz.sixsense.board.entity.QReportBoard(forProperty("reportBoard"), inits.get("reportBoard")) : null;
    }

}

