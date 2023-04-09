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
public class Toilet {
	@Id
	@Column(unique = true)
	@JsonProperty("name")
	private String name;

	@JsonProperty("addr_j")
	private String address_j;

	@JsonProperty("addr_r")
	private String address_r;

	@JsonProperty("open_time")
	private String openTime;

	@JsonProperty("lat")
	private String latitude;

	@JsonProperty("lng")
	private String longitude;

	@JsonProperty("gbn")
	private String roadConentcatecory;

	@JsonProperty("manager")
	private String manager;

	@JsonProperty("tel_no")
	private String manager_tel_no;

	@JsonProperty("course")
	private String road_course;

	@JsonProperty("gugan")
	private String road_course_gugan;



	@ManyToOne
    @JoinColumn(name = "guganNm", nullable = false, updatable = false)
    private Road guganNm;

}
