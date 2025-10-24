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

        // 1. SQL CORRIGIDO: Seleciona colunas que REALMENTE existem na sua tabela 'aluno'
        // (Baseado na imagem que você enviou)
        String sql = "SELECT id, usuario_id, ra, idade, link_github, link_linkedin FROM aluno";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // 2. Pega os dados das colunas corretas
                int id = rs.getInt("id");
                int usuarioId = rs.getInt("usuario_id");
                String ra = rs.getString("ra");
                int idade = rs.getInt("idade");
                String linkGithub = rs.getString("link_github");
                String linkLinkedin = rs.getString("link_linkedin");

                // 3. CRÍTICO: Seu construtor Aluno() PRECISA bater com esses dados
                // Você terá que ATUALIZAR sua classe Aluno.java (veja abaixo)
                alunos.add(new Aluno(id, usuarioId, ra, idade, linkGithub, linkLinkedin));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Erro ao buscar alunos
        }

        return alunos;
    }
}