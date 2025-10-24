package org.example;

public class Aluno {

    // 1. Campos que batem com o DAO e a tabela do banco
    private int id;
    private int usuarioId;
    private String ra;
    private int idade;
    private String linkGithub;
    private String linkLinkedin;
    // (Você pode adicionar os outros campos da tabela aqui se precisar deles,
    // como historico_academico, etc.)

    /**
     * Construtor vazio.
     * É uma boa prática manter para frameworks como o JavaFX/FXML.
     */
    public Aluno(){}

    /**
     * Este é o construtor que o AlunoDAO (corrigido) está usando.
     * Ele aceita os dados que realmente existem na sua tabela.
     */
    public Aluno(int id, int usuarioId, String ra, int idade, String linkGithub, String linkLinkedin) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.ra = ra;
        this.idade = idade;
        this.linkGithub = linkGithub;
        this.linkLinkedin = linkLinkedin;
    }

    // 3. Getters e Setters para todos os campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLinkGithub() {
        return linkGithub;
    }

    public void setLinkGithub(String linkGithub) {
        this.linkGithub = linkGithub;
    }

    public String getLinkLinkedin() {
        return linkLinkedin;
    }

    public void setLinkLinkedin(String linkLinkedin) {
        this.linkLinkedin = linkLinkedin;
    }
}