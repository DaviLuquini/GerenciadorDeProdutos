package GestaoDeProdutos.Infraestrutura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection implements AutoCloseable {
    private Connection connection;

    public DbConnection() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/gestaoDeProdutos", 
                "postgres", 
                "postgres"
            );
            System.out.println("Conexão com o banco de dados PostgreSQL estabelecida com sucesso.");
        } catch (SQLException e) {
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}