package mz.sixsense.member.history.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mz.sixsense.member.entity.Members;
import mz.sixsense.shop.entity.Products;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@ToString(exclude = {"memberID"})
@SequenceGenerator(
		name = "orderhis_seq",
		sequenceName = "orderhistory_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class OrderHistory{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "orderhis_seq")
	private Long orderHistoryID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	private int amount = 0;
	private String productName;
	
    @ManyToOne
    @JoinColumn(name = "memberID", nullable = false, updatable = false)
    private Members memberID;
    
    public void setMemberID(Members member) {
    	this.memberID = member;
    	member.getOrderHistoryList().add(this);
    }
    
}//class
