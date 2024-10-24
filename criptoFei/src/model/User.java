package model;


public class User {
    private String nome, cpf, senha, tipoUser;

    public User(String nome, String cpf, String senha, String tipoUser) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.tipoUser = tipoUser;
        
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public User() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
        
    
}
