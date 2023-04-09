package mz.sixsense.road.file.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mz.sixsense.road.entity.Road;

@Entity
@Data
@RequiredArgsConstructor
@ToString(exclude = {"Road"})
public class RoadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roadVideoID;

    private String saveName;

    private String orginName;

    private String savePath;


    @ManyToOne(fetch =FetchType.LAZY, cascade =CascadeType.ALL)
    @JoinColumn(name ="guganNm")
    private Road guganNm;
}
