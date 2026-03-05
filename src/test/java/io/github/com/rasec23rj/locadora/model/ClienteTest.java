package io.github.com.rasec23rj.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClienteTest {


    @Test
    void deveCriarClienteComNome(){
        //1 cenario
        var cliente = new Cliente("maria");
        String nome = cliente.getNome();
        //3 verificação
        Assertions.assertNotNull(nome);
        Assertions.assertTrue(nome.startsWith("m"));

    }

}
