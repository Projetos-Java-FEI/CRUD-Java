package model;


public class Ripple extends Moeda implements Tarifas {
    
    @Override
    public double calcularTaxaCompra(double valor) {
        return valor * 0.01;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return valor * 0.01;
    }
}
