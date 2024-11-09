package model;


public class Bitcoin extends Moeda implements Tarifas {

    public Bitcoin(String simbolo, String nome, double cotacao) {
        super(simbolo, nome, cotacao);
    }

    
    
    @Override
    public double calcularTaxaCompra(double valor) {
        return valor * 0.02;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return valor * 0.03;
    }
    
}
