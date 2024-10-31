package model;


public class Real extends Moeda implements Tarifas {

    @Override
    public double calcularTaxaCompra(double valor) {
        return 0;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return 0;
    }
    
}
