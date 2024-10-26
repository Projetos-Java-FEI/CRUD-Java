package model;


public class Real extends Moedas implements Tarifas {

    @Override
    public double calcularTaxaCompra(double valor) {
        return 0;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return 0;
    }
    
}
