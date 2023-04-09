package mz.sixsense.member.history.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mz.sixsense.member.entity.Members;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@ToString(exclude = {"memberID"})
@SequenceGenerator(
		name = "pointhis_seq",
		sequenceName = "pointhistory_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pointhis_seq")
    @Column(name = "pointID", nullable = false)
    private Long pointID;

    private Long variancePoint;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date varianceDate = new Date();
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "memberID")
    private Members memberID;
    
//    public void setMember(Members member) {
//    	this.memberID = member;
//    	member.getPointHistoryList().add(this);
//    }
    
    
}//class
