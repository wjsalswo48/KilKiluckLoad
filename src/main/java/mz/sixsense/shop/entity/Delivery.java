package mz.sixsense.shop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mz.sixsense.member.entity.Members;
import mz.sixsense.shop.domain.DeliveryStatus;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Delivery")
@Data
@RequiredArgsConstructor
@ToString(exclude = {"memberID", "productsList"})
@SequenceGenerator(
		name = "del_seq",
		sequenceName = "delivery_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class Delivery{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "del_seq")
	@Column(insertable = false, updatable = false)
	private long deliveryID;

	private String address;
	private String addressDetail;
	private int orderAmount= 0;
	
    @Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "memberID", nullable = false, updatable = false)
    private Members memberID;

    public void setMemberID(Members member) {
    	this.memberID = member;
    	member.getDeliveryList().add(this);
    }
    
    //주문 하나에 물건 여러개
    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Products> productsList = new ArrayList<Products>();
    

}//class
