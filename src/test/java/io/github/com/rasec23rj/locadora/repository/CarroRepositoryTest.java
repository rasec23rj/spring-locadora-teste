package io.github.com.rasec23rj.locadora.repository;

import io.github.com.rasec23rj.locadora.entity.CarroEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository carroRepository;

    CarroEntity carroEntity;

    @BeforeEach
    void setUp() {
        carroEntity = new CarroEntity("sedan", 100.0, 2026);
    }

    @Test
    void deveSalvarUmCarro() {
        carroRepository.save(carroEntity);
        assertNotNull(carroEntity.getId());
    }

    @Test
    void deveBuscarPorId() {
        var carroSalvo = carroRepository.save(carroEntity);
        Optional<CarroEntity> carroEncontrado = carroRepository.findById(carroSalvo.getId());
        Assertions.assertThat(carroEncontrado).isPresent();
        Assertions.assertThat(carroEncontrado.get().getModelo()).isEqualTo("sedan");
    }

    @Test
    void deveAtualizarCarro() {
        var carroSalvo = carroRepository.save(carroEntity);
        carroSalvo.setAno(2027);
        var carroAtualizado = carroRepository.save(carroSalvo);
        Assertions.assertThat(carroAtualizado.getAno()).isEqualTo(2027);
    }

    @Test
    void deveDeletarCarro() {
        var carroSalvo = carroRepository.save(carroEntity);
        carroRepository.deleteById(carroSalvo.getId());
        Optional<CarroEntity> byId = carroRepository.findById(carroSalvo.getId());
        Assertions.assertThat(byId).isEmpty();

    }
}