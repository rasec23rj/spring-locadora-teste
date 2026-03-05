package io.github.com.rasec23rj.locadora.model;

public class Carro {

    private String modelo;
    private double valorDiraria;

    public Carro(String modelo, double valorDiraria) {
        this.modelo = modelo;
        this.valorDiraria = valorDiraria;
    }

    public double calcularValorAluguel(int dias) {
        double desconto = 0;
        if (dias >= 5){
                desconto = 50.0;
             }
        return (dias * valorDiraria) - desconto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiraria() {
        return valorDiraria;
    }

    public void setValorDiraria(double valorDiraria) {
        this.valorDiraria = valorDiraria;
    }
}
