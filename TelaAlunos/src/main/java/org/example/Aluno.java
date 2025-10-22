package org.example;

public class Aluno {
    private int id;
    private String nome;
    private String tempoEnvio; // opcional
    private String imagem;     // caminho relativo em resources

    public Aluno(){}

    public Aluno(int id, String nome, String tempoEnvio, String imagem){
        this.id = id;
        this.nome = nome;
        this.tempoEnvio = tempoEnvio;
    }

    public Aluno(String nome, String tempoEnvio, String imagem) {
        this.nome = nome;
        this.tempoEnvio = tempoEnvio;
        this.imagem = imagem;
    }

    // construtor sem tempo (corrigido)
    public Aluno(String nome, String imagem) {
        this(nome, null, imagem);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTempoEnvio() {
        return tempoEnvio;
    }
    public void setTempoEnvio(String tempoEnvio) {
        this.tempoEnvio = tempoEnvio;
    }

    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
