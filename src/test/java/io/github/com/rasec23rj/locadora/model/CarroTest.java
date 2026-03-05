package io.github.com.rasec23rj.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor real do aluguel")
    void deveCalcularValorAluguel(){
        Carro carro =  new Carro("sedan", 100.0);
        double total = carro.calcularValorAluguel(3);
        Assertions.assertEquals(300.0, total);
    }

    @Test
    @DisplayName("Deve calcular o valor do aluguel com desconto")
    void deveCalcularValorAluguelComDesconto(){
        Carro carro =  new Carro("sedan", 100.0);
        double total = carro.calcularValorAluguel(5);
        Assertions.assertEquals(450.0, total);
    }


}
