package model;


public class Bitcoin extends Moeda implements Tarifas {


    @Override
    public double calcularTaxaCompra(double valor) {
        return valor * 0.02;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return valor * 0.03;
    }
    
}
