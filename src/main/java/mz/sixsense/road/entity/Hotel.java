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
public class Hotel {

	@Id
	@Column(unique = true)
	@JsonProperty("uc_seq")
	private String seq;

	@JsonProperty("name")
	private String name;

	@JsonProperty("place")
	private String place;


	@JsonProperty("addr1")
	private String address;

	@JsonProperty("num_room")
	private String openTime;

	@JsonProperty("gbn")
	private String hotelConentcatecory;

	@JsonProperty("homepage_u")
	private String homepageUrl;

	@JsonProperty("cntct_tel")
	private String tel = "051-xxx-xxxx";

	@JsonProperty("course")
	private String road_course;

	@JsonProperty("gugan")
	private String road_course_gugan;

	@JsonProperty("lat")
	private String latitude;

	@JsonProperty("lng")
	private String longitude;


	@ManyToOne
    @JoinColumn(name = "guganNm", nullable = false, updatable = false)
    private Road guganNm;
}
