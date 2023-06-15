package com.smumo.productservice.service;

import com.smumo.productservice.dto.CreateProductDto;
import com.smumo.productservice.dto.ProductDto;
import com.smumo.productservice.dto.UpdateProductDto;
import com.smumo.productservice.entity.Product;
import com.smumo.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getProducts() {
        var productDtoslist = productRepository.findAll().stream()
                .map( product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                )).collect(Collectors.toList());
//        List<ProductDto> productDtoslist = new ArrayList<>();
//        if(products.size() > 0){
//            for(var item : products){
//                ProductDto newProductDto  = new ProductDto();
//                newProductDto.setName(item.getName());
//                newProductDto.setPrice(item.getPrice());
//                productDtoslist.add(newProductDto);
//            }
//        }
        return productDtoslist;
    }

    @Override
    public Product CreateProduct(CreateProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(product.getPrice());
        productRepository.save(product);
        log.info("Product {} saved ",product.getId());
        return product;
    }

    @Override
    public ProductDto getProduct(Long Id) {
        var product = productRepository.findById(Id);
        ProductDto productDto = new ProductDto();
        if(product.isPresent()){
            productDto.setPrice(product.get().getPrice());
            productDto.setPrice(product.get().getPrice());
        }else{
           // throw new StudentNotFoundException("id-" + id);
        }

        return productDto;
    }

    @Override
    public void UpdateProduct(long id, UpdateProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(id);
        Product updateProd = new Product();
        if(productOptional.isEmpty()){
            // throw new StudentNotFoundException("id-" + id);
        }
        updateProd.setId(id);
        updateProd.setPrice(productDto.getPrice());
        updateProd.setName(productDto.getName());

         productRepository.save(updateProd);

    }

    @Override
    public void deleteByID(long id) {
        productRepository.deleteById(id);
    }
}
