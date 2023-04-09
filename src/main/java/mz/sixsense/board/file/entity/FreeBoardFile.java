package mz.sixsense.board.file.entity;

import lombok.*;
import mz.sixsense.board.entity.FreeBoard;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"freeBoard"})
public class FreeBoardFile {

    @Id
    @GeneratedValue
    private Long fileID;

    private String originName;
    private String saveName;
    private String savePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freeBID")
    private FreeBoard freeBoard;

    public void setFreeBoard(FreeBoard freeBoard){
        this.freeBoard = freeBoard;
        freeBoard.getFreeBoardFileList().add(this);
    }

    @Builder
    public FreeBoardFile(Long fileID, String originName, String saveName, String savePath) {
        this.fileID = fileID;
        this.originName = originName;
        this.saveName = saveName;
        this.savePath = savePath;
    }
}
