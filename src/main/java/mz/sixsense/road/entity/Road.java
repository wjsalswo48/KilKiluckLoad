package mz.sixsense.road.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mz.sixsense.road.file.entity.RoadFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(exclude = {"toiletList", "roadFileList", "courseId",
        "photoZoneList", "touristAttactionList", "arroundRestaurantList", "hotelList"})
public class Road {

    @Id
    @Column(unique = true)
    @JsonProperty("gugan_nm")
    private String guganNm;

    @JsonProperty("gm_text")
    @Column(length = 3000)
    private String gmText;

    @JsonProperty("start_addr")
    private String startAddr;

    @JsonProperty("start_pls")
    private String startPls;

    @JsonProperty("middle_pls")
    private String middlePls;

    @JsonProperty("middle_adr")
    private String middleAdr;

    @JsonProperty("end_pls")
    private String endPls;

    @JsonProperty("end_addr")
    private String endAddr;

    @JsonProperty("gm_range")
    private String Range;

    @JsonProperty("gm_degree")
    private String gmDegree;

    @JsonProperty("gm_course")
    private String gmCourse;

    private Long visits;

    private String avgTime;

    private String img_path;

    private String start_lat;

    private String start_long;

    private String end_lat;

    private String end_long;


    @OneToMany(mappedBy = "guganNm", cascade = CascadeType.ALL)
    private List<Toilet> toiletList = new ArrayList<>();

    @OneToMany(mappedBy = "guganNm", cascade = CascadeType.ALL)
    private List<RoadFile> roadFileList = new ArrayList<>();

    @OneToMany(mappedBy = "guganNm", cascade = CascadeType.ALL)
    private List<PhotoZone> photoZoneList = new ArrayList<>();

    @OneToMany(mappedBy = "guganNm", cascade = CascadeType.ALL)
    private List<TouristAttaction> touristAttactionList = new ArrayList<>();

    @OneToMany(mappedBy = "guganNm", cascade = CascadeType.ALL)
    private List<Restaurant> arroundRestaurantList = new ArrayList<>();

    @OneToMany(mappedBy = "guganNm", cascade = CascadeType.ALL)
    private List<Hotel> hotelList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "courseId", nullable = false, updatable = false)
    Course courseId;

//    @Override
//    public List<Road> compareTo(List<String> o) {
//        return null
//    }
}

