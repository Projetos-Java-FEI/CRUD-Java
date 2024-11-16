package model;


public class Real extends Moeda implements Tarifas {

    public Real(String simbolo, String nome, double cotacao) {
        super(simbolo, nome, cotacao);
    }

    
    @Override
    public double calcularTaxaCompra(double valor) {
        return 0;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return 0;
    }
    
}
