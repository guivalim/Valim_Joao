package org.example;

public class TG {
    private int id;
    private int idAluno;
    private String nomeArquivo;
    private String dataEnvio;
    private String imagemStatus;

    public TG(){}

    public TG(int id, int idAluno, String nomeArquivo, String dataEnvio, String imagemStatus) {
        this.id = id;
        this.idAluno = idAluno;
        this.nomeArquivo = nomeArquivo;
        this.dataEnvio = dataEnvio;
        this.imagemStatus = imagemStatus;
    }

    public TG(String nomeArquivo, String dataEnvio, String imagemStatus) {
        this.nomeArquivo = nomeArquivo;
        this.dataEnvio = dataEnvio;
        this.imagemStatus = imagemStatus;
    }

    public TG(String nomeArquivo, String imagemStatus) {

        this(nomeArquivo,null,imagemStatus);
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }

    public String getNomeArquivo() { return nomeArquivo; }
    public void setNomeArquivo(String nomeArquivo) { this.nomeArquivo = nomeArquivo; }

    public String getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(String dataEnvio) { this.dataEnvio = dataEnvio; }

    public String getImagemStatus() { return imagemStatus; }
    public void setImagemStatus(String imagemStatus) { this.imagemStatus = imagemStatus; }
}
