package com.smumo.productservice.service;

import com.smumo.productservice.dto.CreateProductDto;
import com.smumo.productservice.dto.ProductDto;
import com.smumo.productservice.dto.UpdateProductDto;
import com.smumo.productservice.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<ProductDto> getProducts() ;
    public Product CreateProduct(CreateProductDto productDto);
    public ProductDto getProduct(Long Id) ;
    public void UpdateProduct(long id , UpdateProductDto productDto);

    void deleteByID(long id);
}
