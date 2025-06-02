package com.thiago.quick.start.repositories;

import com.thiago.quick.start.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    @DisplayName("Deve salvar um produto no banco H2 gerando UUID automaticamente")
    void saveProductSuccessTest() {

        var expected = Product.builder()
                .name("Produto A")
                .price(50.0)
                .build();

        var saved = repository.save(expected);

        assertThat(expected.getId()).isNotNull();
        assertThat(saved.getId()).isInstanceOf(UUID.class);
        assertThat(saved)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);

        var productOpt = repository.findById(saved.getId());

        assertThat(productOpt).isPresent();
        assertThat(productOpt.get())
                .usingRecursiveComparison()
                .isEqualTo(saved);
    }

}
