package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TGDAO {

    public List<TG> listarTGsPorAluno(int idAluno) {
        List<TG> tgs = new ArrayList<>();

        String sql = "SELECT id, id_aluno, nome_arquivo, data_envio, imagem_status FROM tg WHERE id_aluno = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                int id = rs.getInt("id");
                int idAlunoDB = rs.getInt("id_aluno");
                String nomeArquivo = rs.getString("nome_arquivo");
                String dataEnvio = rs.getString("data_envio");
                String imagemStatus = rs.getString("imagem_status");

                TG tg = new TG(id, idAlunoDB, nomeArquivo, dataEnvio, imagemStatus);
                tgs.add(tg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tgs;
    }
}

