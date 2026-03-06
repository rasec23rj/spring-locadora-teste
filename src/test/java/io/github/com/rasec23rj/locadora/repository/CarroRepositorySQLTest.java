package io.github.com.rasec23rj.locadora.repository;


import io.github.com.rasec23rj.locadora.entity.CarroEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CarroRepositorySQLTest {

    @Autowired
    CarroRepository repository;

    @Test
    @Sql("/sql/carrosql.sql")
    void deveBuscarPorModelo(){
        List<CarroEntity> lista = repository.findByModelo("SUV");
        var carro =    lista.getFirst();
        Assertions.assertEquals(1, lista.size());

        org.assertj.core.api.Assertions.assertThat(carro.getValorDiaria()).isEqualTo(150.0);
        org.assertj.core.api.Assertions.assertThat(carro.getModelo()).isEqualTo("SUV");

    }
}
