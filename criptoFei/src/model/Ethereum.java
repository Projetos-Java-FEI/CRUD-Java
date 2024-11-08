package model;


public class Ethereum extends Moeda implements Tarifas {

    public Ethereum(String simbolo, String nome, double cotacao) {
        super(simbolo, nome, cotacao);
    }
  
    
    @Override
    public double calcularTaxaCompra(double valor) {
        return valor * 0.01;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return valor * 0.02;
    }
}
