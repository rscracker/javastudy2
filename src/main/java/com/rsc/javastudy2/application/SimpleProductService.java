package com.rsc.javastudy2.application;

import com.rsc.javastudy2.domain.Product;
import com.rsc.javastudy2.infrastructure.DatabaseProductRepository;
import com.rsc.javastudy2.infrastructure.ListProductRepository;
import com.rsc.javastudy2.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SimpleProductService {

    private ListProductRepository listProductRepository;
    private ModelMapper modelMapper;
    private ValidationService validationService;

    private DatabaseProductRepository databaseProductRepository;
    @Autowired
    SimpleProductService(
            DatabaseProductRepository databaseProductRepository,
            ListProductRepository listProductRepository, ModelMapper modelMapper, ValidationService validationService){
        this.databaseProductRepository = databaseProductRepository;
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    public ProductDto add(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        validationService.checkValid(product);
        Product savedProduct = databaseProductRepository.add(product);
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);
        return savedProductDto;
    }

    public ProductDto findById(Long id){
        Product product = databaseProductRepository.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> findAll(){
        List<Product> products = databaseProductRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(
                product -> modelMapper.map(product, ProductDto.class)
        ).toList();
        return productDtos;
    }

    public List<ProductDto> findByName(String name){
        List<Product> products = databaseProductRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).toList();
        return productDtos;
    }

    public ProductDto update(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        Product updatedProduct = databaseProductRepository.update(product);
        ProductDto updatedProductDto = modelMapper.map(updatedProduct, ProductDto.class);
        return updatedProductDto;
    }

    public void delete(Long id){
        databaseProductRepository.delete(id);
    }
}
