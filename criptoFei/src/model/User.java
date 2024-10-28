package model;

public class User {
    private int id;  // Atributo para o ID do usuário
    private String nome, cpf, senha, tipoUser;

    // Construtor que inclui todos os atributos (incluindo o ID)
    public User(int id, String nome, String cpf, String senha, String tipoUser) {
        this.id = id;  // ID do usuário
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.tipoUser = tipoUser;
    }

    // Construtor que não inclui ID (para cadastro)
    public User(String nome, String cpf, String senha, String tipoUser) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.tipoUser = tipoUser;
    }

    // Construtor padrão (opcional, se necessário)
    public User() {
    }

    // Métodos getter e setter para o ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters e Setters para outros atributos
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

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }
}

