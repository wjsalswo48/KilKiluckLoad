package mz.sixsense.shop.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import mz.sixsense.shop.entity.Products;
import mz.sixsense.shop.file.entity.ProductFile;
import mz.sixsense.shop.file.repository.ProductFileRepository;
import mz.sixsense.shop.file.service.ProductFileService;

@Service
@RequiredArgsConstructor
public class ProductFileServiceImpl implements ProductFileService{

	@Value("${productFile.dir}")
	private String productFileDir;
	
	@Autowired
	private ProductFileRepository productFileRepo;

    @Override
    public Long saveFile(MultipartFile files, Products product) throws IOException {
        if (files.isEmpty()) {
            return null;
        }
        String originName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        //확장자
        String extension = originName.substring(originName.lastIndexOf("."));
        String saveName = uuid + extension;
        String savePath = productFileDir + saveName;

        ProductFile productFile = ProductFile.builder()
        								.originName(originName)
                                        .saveName(saveName)
                                        .savePath(savePath)
                                        .build();

        productFile.setProduct(product);
        files.transferTo(new File(savePath));

        ProductFile saveFile = productFileRepo.save(productFile);
        
        return saveFile.getFileID();
    }

    @Override
    public List<ProductFile> view() {
        return productFileRepo.findAll();
    }

    @Override
    public List<ProductFile> fileAllView(Long productID) {
        return productFileRepo.selectImageAllViewQuery(productID);
    }

    @Override
    public ProductFile downloadImage(@PathVariable("fileID") Long productID) {
        return productFileRepo.findById(productID).orElse(null);
    }
    
    @Override
    public void deleteFile(Long fileID) {
    	productFileRepo.deleteById(fileID);
    }
    
    @Override
    public void deleteAllByProductID(Products pd) {
    	productFileRepo.deleteAllByproduct(pd);
    }

}//class
