package io.github.com.rasec23rj.locadora.service;

import io.github.com.rasec23rj.locadora.entity.CarroEntity;
import io.github.com.rasec23rj.locadora.repository.CarroRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    CarroService service;

    @Mock
    CarroRepository repository;

    @Test
    void deveSalvarUmCarro(){
        CarroEntity carro = new CarroEntity("suv", 10.0, 2017);
        Mockito.when(repository.save(carro)).thenReturn(carro);

        CarroEntity save = service.save(carro);
        assertNotNull(save);
        assertEquals("suv", carro.getModelo());
    }

    @Test
    void deveDaErrorAoTentarSavarComDiariaNegativa(){
        CarroEntity carro = new CarroEntity("sedan", 0, 2026);
        var erro =  catchThrowable(() -> service.save(carro));

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);

    }
}