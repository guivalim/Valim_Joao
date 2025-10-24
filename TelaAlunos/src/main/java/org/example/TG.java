package org.example;

/**
 * Esta classe agora representa uma VERSÃO (tg_versao),
 * mas "enriquecida" com dados do portfólio (como aluno_id e titulo).
 */
public class TG {

    private int id; // ID da tg_versao
    private int alunoId; // ID do aluno (vindo da tg_portfolio)
    private String nomeArquivo; // Usaremos o "titulo" da tg_portfolio aqui
    private String dataEnvio; // Usaremos a "updated_at" da tg_versao
    private String imagemStatus; // Mapeado a partir de "avaliado"
    private String conteudo; // O "conteudo_md" da tg_versao

    // Construtor vazio (boa prática)
    public TG() {}

    // Construtor completo que o TGDAO usará
    public TG(int id, int alunoId, String nomeArquivo, String dataEnvio, String imagemStatus, String conteudo) {
        this.id = id;
        this.alunoId = alunoId;
        this.nomeArquivo = nomeArquivo;
        this.dataEnvio = dataEnvio;
        this.imagemStatus = imagemStatus;
        this.conteudo = conteudo;
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getImagemStatus() {
        return imagemStatus;
    }

    public void setImagemStatus(String imagemStatus) {
        this.imagemStatus = imagemStatus;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}