package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT nome, tempo_envio, imagem FROM aluno";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String tempoEnvio = rs.getString("tempo_envio");
                String imagem = rs.getString("imagem");

                // se tempo_envio for null, usa o construtor alternativo
                if (tempoEnvio == null || tempoEnvio.isEmpty())
                    alunos.add(new Aluno(nome, imagem));
                else
                    alunos.add(new Aluno(nome, tempoEnvio, imagem));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }
}
