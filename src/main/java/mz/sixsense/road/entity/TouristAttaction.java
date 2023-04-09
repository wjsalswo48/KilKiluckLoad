package mz.sixsense.road.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(exclude = {"guganNm"})
public class TouristAttaction {
	@Id
	@JsonProperty("uc_seq")
	@Column(unique = true)
	private String seq;

	@JsonProperty("name")
	private String name;

	@JsonProperty("cate1_nm")
	private String category;

	@JsonProperty("place")
	private String place;

	@JsonProperty("title")
	private String title;

	@JsonProperty("main_img_n")
	private String mainImage;

	@JsonProperty("main_img_t")
	private String thumbnail;

	@JsonProperty("itemcntnts")
	private String text;

	@JsonProperty("lat")
	private String latitude;

	@JsonProperty("lng")
	private String longtitude;

	@JsonProperty("course")
	private String road_course;

	@JsonProperty("gugan")
	private String road_course_gugan;


	@ManyToOne
    @JoinColumn(name = "guganNm", nullable = false, updatable = false)
    private Road guganNm;

}
