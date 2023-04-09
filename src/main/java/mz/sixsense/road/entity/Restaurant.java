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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"resid"})})
public class Restaurant {

	@Id
	@Column(unique = true)
	@JsonProperty("uc_seq")
	private String resid;

	@JsonProperty("name")
	private String name;

	@JsonProperty("main_title")
	private String restaurantTitle;

	@JsonProperty("addr1")
	private String address;


	@JsonProperty("usage_day_")
	private String opentime;

	@JsonProperty("cntct_tel")
	private String tel;

	@JsonProperty("main_img_n")
	private String mainimage;

	@JsonProperty("main_img_t")
	private String thumbnail;

	@JsonProperty("itemcntnts")
	private String introduce;

	@JsonProperty("course")
	@Column(length = 5000)
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

