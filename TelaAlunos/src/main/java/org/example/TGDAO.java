package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TGDAO {

    // Define um formatador de data para ficar "dd/MM/yyyy HH:mm"
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Lista todas as VERSÕES de TG de um determinado aluno.
     * Busca dados em tg_versao, tg_secao e tg_portifolio.
     */
    public List<TG> listarTGsPorAluno(int idAluno) {
        List<TG> tgs = new ArrayList<>();

        // SQL com o JOIN nas 3 tabelas
        String sql = "SELECT " +
                "  v.id, " +                 // ID da versão (para o feedback)
                "  p.aluno_id, " +           // ID do aluno (para filtro)
                "  p.titulo, " +             // Título do portfólio (será nosso "nome_arquivo")
                "  v.updated_at, " +         // Data da versão (será nossa "data_envio")
                "  v.avaliado, " +           // Status (será nossa "imagem_status")
                "  v.conteudo_md " +          // Conteúdo (será nosso "conteudo")
                "FROM " +
                "  tg_versao AS v " +
                "JOIN " +
                "  tg_secao AS s ON v.secao_id = s.id " +
                "JOIN " +
                // --- CORREÇÃO ESTÁ AQUI ---
                "  tg_portifolio AS p ON s.portifolio_id = p.id " + // (era portfolio)
                "WHERE " +
                "  p.aluno_id = ? " +
                "ORDER BY " +
                "  v.updated_at DESC"; // Mostra as versões mais recentes primeiro

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno); // Define o ID do aluno no "WHERE"

            // Usando try-with-resources para o ResultSet também
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idAlunoDB = rs.getInt("aluno_id");
                    String nomeArquivo = rs.getString("titulo");

                    // Pega a data e formata
                    String dataEnvio = rs.getTimestamp("updated_at")
                            .toLocalDateTime()
                            .format(FORMATADOR_DATA);

                    int avaliado = rs.getInt("avaliado"); // Pega o status (0 ou 1)
                    String conteudo = rs.getString("conteudo_md");

                    // Mapeia o status 0/1 para os nomes das imagens
                    String imagemStatus = (avaliado == 1) ? "check.png" : "excla.png";

                    TG tg = new TG(id, idAlunoDB, nomeArquivo, dataEnvio, imagemStatus, conteudo);
                    tgs.add(tg);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Lança o erro para a camada superior (Controller) saber que falhou
            throw new RuntimeException("Erro ao buscar TGs: " + e.getMessage(), e);
        }

        return tgs;
    }
}