package io.github.com.rasec23rj.locadora.model;

import io.github.com.rasec23rj.locadora.model.exception.ReservaInvalidaException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {


    Cliente cliente;
    Carro carro;

    @BeforeEach
    void setUp(){
         cliente = new Cliente("jose");
         carro =  new Carro("onix", 50.0);

    }

    @Test
    void deveCriarUmaReserva(){

        var reserva = new Reserva(cliente, carro, 5);
        Assertions.assertThat(reserva).isNotNull();
    }

    @Test
    void deveDarErrorAoCriarUmaReservaComDiasNegativos(){

        assertThrows(ReservaInvalidaException.class, () -> new Reserva(cliente, carro, 0));
        assertDoesNotThrow(() -> new Reserva(cliente, carro, 1));
    }

    @Test
    void deveCalcularOTotaldoAluguel(){
        var reserva = new Reserva(cliente, carro, 3);
        var total = reserva.calcularTotal();
        Assertions.assertThat(total).isEqualTo(150);
    }
}