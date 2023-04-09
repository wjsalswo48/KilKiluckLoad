package mz.sixsense.shop.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import mz.sixsense.shop.entity.Products;
import mz.sixsense.shop.file.entity.ProductFile;

public interface ProductFileService {

	List<ProductFile> view();

	List<ProductFile> fileAllView(Long productID);

	ProductFile downloadImage(@PathVariable("fileID") Long productID);

	Long saveFile(MultipartFile files, Products product) throws IOException;

	void deleteFile(Long fileID);
	
	void deleteAllByProductID(Products pd);
}
