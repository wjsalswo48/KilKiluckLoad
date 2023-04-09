package mz.sixsense.shop.entity;

//github.com/KkilukLoad/KkilukLoad_Project.git
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mz.sixsense.shop.file.entity.ProductFile;

@Entity(name = "Products")
@Data
@RequiredArgsConstructor
@ToString(exclude = { "delivery", "productFileList", "productGradeList"})
@SequenceGenerator(name = "proshop_seq", sequenceName = "productShop_seq", initialValue = 1, allocationSize = 1)
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proshop_seq")
	@Column(insertable = false, updatable = false)
	private long productID;

	private String name;
	private String content;
	private String productCategory;

	private Long cashPrice = 0L;
	private Long pointPrice = 0L;
	private Long stock =0L;

	private Double grade = 0D;
	private Long fileID;//대표사진

	//주문 하나에 물건 여러개...
	@ManyToOne
	@JoinColumn(name = "delivery", nullable = true)
	private Delivery delivery;
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.getProductsList().add(this);
	}
	@OneToMany(mappedBy = "product")
	private List<ProductFile> productFileList = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductGrade> productGradeList = new ArrayList<>();
	
}// class
