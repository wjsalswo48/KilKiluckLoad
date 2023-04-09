package mz.sixsense.shop.file.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mz.sixsense.shop.entity.Products;

@Entity
@Data
@RequiredArgsConstructor
@ToString(exclude = {"product"})
@SequenceGenerator(
		name = "itemfile_seq",
		sequenceName = "itemFile_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class ProductFile{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemfile_seq")
	private long fileID;

	private String saveName;
	private String savePath;
	private String originName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product")
	private Products product;

	public void setProduct(Products product) {
		this.product = product;
		product.getProductFileList().add(this);
	}
	
	@Builder
	public ProductFile(long fileID, String originName, String saveName, String savePath) {
			this.fileID = fileID;
	        this.originName = originName;
	        this.saveName = saveName;
	        this.savePath = savePath;
	}
}//class
