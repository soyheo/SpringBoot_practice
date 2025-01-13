package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import kr.co.hanbit.product.management.infrastructure.DatabaseProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

//    private ListProductRepository listProductRepository;
//    private DatabaseProductRepository databaseProductRepository;
    private ProductRepository productRepository;
//    private ModelMapper modelMapper;
    private ValidationService validationService;

    @Autowired
    SimpleProductService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
//        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }
    // Add a product
    public ProductDto add(ProductDto productDto) {
//        Product product = modelMapper.map(productDto, Product.class);
        Product product = ProductDto.toEntity(productDto);
        validationService.checkValid(product);

        Product savedProduct = productRepository.add(product);
//        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class); // Product <-> ProductDto : constructor or static factory method or mapping library(ModelMapping)
        ProductDto savedProductDto = ProductDto.toDto(savedProduct);
        return savedProductDto;
    }
    // Query a product with id
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
//        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        ProductDto productDto = ProductDto.toDto(product);
        return productDto;
    }
    // Query all products
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
//                .map(product -> modelMapper.map(product, ProductDto.class))
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }
    // Query a product named with specified letters
    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
//                .map(product -> modelMapper.map(product, ProductDto.class))
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }
    // Update information of a product
    public ProductDto update(ProductDto productDto) {
//        Product product = modelMapper.map(productDto, Product.class);
        Product product = ProductDto.toEntity(productDto);
        Product updatedProduct = productRepository.update(product);
//        ProductDto updatedProductDto = modelMapper.map(updatedProduct, ProductDto.class);
        ProductDto updatedProductDto = ProductDto.toDto(updatedProduct);
        return updatedProductDto;
    }
    // Delete a product
    public void delete(Long id) {
        productRepository.delete(id);
    }

}
