package io.github.com.rasec23rj.locadora.repository;

import io.github.com.rasec23rj.locadora.entity.CarroEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository carroRepository;

    @Test
    void deveSalvarUmCarro(){
        var entity = new CarroEntity("sedan", 100.0);
        carroRepository.save(entity);
        assertNotNull(entity.getId());
    }
}