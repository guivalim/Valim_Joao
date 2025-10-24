package org.example; // <- Pacote adaptado para seu projeto

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    // Configurações do banco de dados da BlueTech
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "blue_tech"; // Nome do banco de dados
    private static final String DB_URL = SERVER_URL + DB_NAME;
    private static final String USER = "usuarioBlueTech";
    private static final String PASSWORD = "BlueTechADM123";

    private static HikariDataSource dataSource; // O Pool de Conexões

    // Inicializa o pool sob demanda (apenas 1 vez)
    private static synchronized HikariDataSource getDataSource() {
        if (dataSource == null) {

            // 1. Garante que o banco de dados exista ANTES de configurar o pool
            createDatabaseIfNotExists();

            // 2. Configura o pool (Hikari)
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DB_URL);
            config.setUsername(USER);
            config.setPassword(PASSWORD);
            config.setMaximumPoolSize(5); // Tamanho do pool (5 conexões)
            config.setConnectionTimeout(30000); // 30s de timeout
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");

            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }

    /**
     * Pega uma conexão emprestada do pool.
     * Use sempre este método no seu código (ex: nos DAOs).
     */
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection(); // Empresta do pool
    }

    /**
     * Conexão genérica SEM pool.
     * Usada APENAS para criar o banco na primeira vez.
     */
    private static Connection getRootConnection() throws SQLException {
        // Conecta ao servidor (sem DB específico) para poder criar o DB
        return java.sql.DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
    }

    /**
     * Cria o banco de dados (usa conexão sem pool)
     */
    private static void createDatabaseIfNotExists() {
        String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;

        // try-with-resources fecha a conexão e o statement automaticamente
        try (Connection conn = getRootConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();
            System.out.println("Database '" + DB_NAME + "' verificado. (Pronto para uso)");

        } catch (SQLException e) {
            // Se falhar aqui (ex: usuário/senha errados), a aplicação não deve continuar
            throw new RuntimeException("Erro crítico ao criar/verificar database: " + e.getMessage(), e);
        }
    }

    /**
     * Fecha o pool de conexões.
     * Chame isso quando sua aplicação for fechar (ex: no método stop() do JavaFX)
     */
    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();
            System.out.println("Pool de conexões fechado.");
        }
    }
}