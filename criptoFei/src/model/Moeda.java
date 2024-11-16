package model;


public class Moeda extends Carteira {
    private String simbolo, nome;
    private double cotacao, taxCompra, taxVenda;

    public Moeda(String simbolo, String nome, double cotacao) {
        this.simbolo = simbolo;
        this.nome = nome;
        this.cotacao = cotacao;
    }
    
    

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCotacao() {
        return cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }

    public double getTaxCompra() {
        return taxCompra;
    }

    public void setTaxCompra(double taxCompra) {
        this.taxCompra = taxCompra;
    }

    public double getTaxVenda() {
        return taxVenda;
    }

    public void setTaxVenda(double taxVenda) {
        this.taxVenda = taxVenda;
    }
    
}
