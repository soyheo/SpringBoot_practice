package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.EntityNotFoundException;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SimpleProductServiceTest {

    @Autowired
    SimpleProductService simpleProductService;   // you can inject directly in test code

//    @Transactional    //When used with @test, the result is roll backed. It can not be used with non-transactional process
    @Test
    @DisplayName("When querying a product with the id after adding the product, it must show the same product.")
    void productAddAndFindByIdTest() {
        ProductDto productDto = new ProductDto("pencil", 300, 20);

        ProductDto savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDto foundProductDto = simpleProductService.findById(savedProductId);

        assertTrue(Objects.equals(savedProductDto.getId(), foundProductDto.getId()));
        assertTrue(Objects.equals(savedProductDto.getName(), foundProductDto.getName()));
        assertTrue(Objects.equals(savedProductDto.getPrice(), foundProductDto.getPrice()));
        assertTrue(savedProductDto.getAmount().equals(foundProductDto.getAmount()));
    }

    @Test
    @DisplayName("When querying a product with non-existing id, it must throw EntityNotFoundException.")
    void findProductNotExistIdTest() {
        Long notExistId = -1L;

        assertThrows(EntityNotFoundException.class, () -> {
            simpleProductService.findById(notExistId);
        });
    }
}