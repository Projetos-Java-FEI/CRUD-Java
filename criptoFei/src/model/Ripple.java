package model;


public class Ripple extends Moedas implements Tarifas {
    
    @Override
    public double calcularTaxaCompra(double valor) {
        return valor * 0.01;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return valor * 0.01;
    }
}
