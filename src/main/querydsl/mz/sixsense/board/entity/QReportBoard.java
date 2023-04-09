package mz.sixsense.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportBoard is a Querydsl query type for ReportBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportBoard extends EntityPathBase<ReportBoard> {

    private static final long serialVersionUID = -1219787760L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportBoard reportBoard = new QReportBoard("reportBoard");

    public final StringPath boardCategory = createString("boardCategory");

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Long> fileID = createNumber("fileID", Long.class);

    public final mz.sixsense.member.entity.QMembers memberID;

    public final ListPath<mz.sixsense.board.file.entity.ReportBoardFile, mz.sixsense.board.file.entity.QReportBoardFile> reportBoardFileList = this.<mz.sixsense.board.file.entity.ReportBoardFile, mz.sixsense.board.file.entity.QReportBoardFile>createList("reportBoardFileList", mz.sixsense.board.file.entity.ReportBoardFile.class, mz.sixsense.board.file.entity.QReportBoardFile.class, PathInits.DIRECT2);

    public final NumberPath<Long> reportID = createNumber("reportID", Long.class);

    public final StringPath reportStatus = createString("reportStatus");

    public final StringPath title = createString("title");

    public QReportBoard(String variable) {
        this(ReportBoard.class, forVariable(variable), INITS);
    }

    public QReportBoard(Path<? extends ReportBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportBoard(PathMetadata metadata, PathInits inits) {
        this(ReportBoard.class, metadata, inits);
    }

    public QReportBoard(Class<? extends ReportBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberID = inits.isInitialized("memberID") ? new mz.sixsense.member.entity.QMembers(forProperty("memberID")) : null;
    }

}

