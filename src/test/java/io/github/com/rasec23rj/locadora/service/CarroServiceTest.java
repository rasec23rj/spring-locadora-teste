package io.github.com.rasec23rj.locadora.service;

import io.github.com.rasec23rj.locadora.entity.CarroEntity;
import io.github.com.rasec23rj.locadora.exception.EntityNotFoundException;
import io.github.com.rasec23rj.locadora.repository.CarroRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
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

    @Test
    void deveAtualizarUmCarro(){
        var carroExistente = new CarroEntity("suv", 10.0, 2017);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(carroExistente));

        var carroAtualizado = new CarroEntity("Gol", 10.0, 2017);
        carroAtualizado.setId(1L);
        Mockito.when(repository.save(Mockito.any())).thenReturn(carroAtualizado);

        Long id = 1L;
        var carro = new CarroEntity("sedan", 0, 2026);

        var resultado = service.update(id, carro);

        assertEquals(resultado.getModelo(), "Gol");
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void deveDarErroAoTentarAtualizarCarroInexistente(){
        Long id = 1L;
        var carro = new CarroEntity("Sedan", 10, 2027);

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var error = catchThrowable(() -> service.update(id, carro));

        assertThat(error).isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());

    }

    @Test
    void deveDeletarUmCarro(){
        Long id = 1L;
        var carro = new CarroEntity("Sedan", 10, 2027);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        service.deletar(id);
        Mockito.verify(repository, Mockito.times(1)).delete(carro);
    }

    @Test
    void deveBuscarPorId(){
        Long id = 1L;
        var carro = new CarroEntity("Sedan", 10, 2027);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        var carroEncontrado = service.searchById(id);
        assertThat(carroEncontrado.getModelo()).isEqualTo("Sedan");
    }

    @Test
    void deveListaTodosOsCarros(){
        var carro = new CarroEntity(1L, "Sedan", 10, 2027);
        var carro2 = new CarroEntity(2L, "Sedan", 10, 2027);

        var lista = List.of(carro, carro2);
        Mockito.when(repository.findAll()).thenReturn(lista);
        List<CarroEntity> resultado = service.findAll();
        assertThat(resultado).hasSize(2);

        Mockito.verify(repository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }
}