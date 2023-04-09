package mz.sixsense.road.qr.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
@Data
@RequiredArgsConstructor
public class QRData{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long roadData;

	private String startingPoint;
	private Date startTime;
	private String endPoint;
	private Date endTime;
	private Long avgTime;
}
