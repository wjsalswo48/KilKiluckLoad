package mz.sixsense.board.file.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mz.sixsense.board.entity.ReportBoard;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@ToString(exclude = {"ReportBoard"})
public class ReportBoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileID;

    private String originName;
    private String saveName;
    private String savPath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reportID")
    private ReportBoard reportBoard;

    @Builder
    public ReportBoardFile(Long fileID, String originName, String saveName, String savPath) {
        this.fileID = fileID;
        this.originName = originName;
        this.saveName = saveName;
        this.savPath = savPath;
    }
}
