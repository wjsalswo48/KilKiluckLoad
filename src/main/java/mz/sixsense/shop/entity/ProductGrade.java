package mz.sixsense.shop.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;
import mz.sixsense.member.entity.Members;

/**
 * Entity implementation class for Entity: ProductGrade
 *
 */
@Entity
@Data
@ToString(exclude = {"product","member"})
@SequenceGenerator(
		name = "grade_seq",
		sequenceName = "product_grade_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class ProductGrade implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ProductGrade() {
		super();
	}
   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
	private long gradeSeq;
	
	private double grades;
	@ManyToOne
	@JoinColumn(name = "product", nullable = false, updatable = false)
	private Products product;
	
	public void  setProduct(Products product) {
		this.product = product;
		product.getProductGradeList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name = "member")
	private Members member;
	
	public void setMember(Members member) {
		this.member = member;
		member.getProductGradeList().add(this);
	}
}//class
