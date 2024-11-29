package GestaoDeProdutos.Infraestrutura;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection implements AutoCloseable {
    private Connection connection;

    public DbConnection() {
        Properties properties = new Properties();

        // Carrega o arquivo de propriedades com o ClassLoader
        try (FileInputStream input = new FileInputStream("C:\\Users\\davil\\eclipse-workspace\\GerenciamentoDeProdutos\\src\\db.properties")) {
            properties.load(input);
           

            // Obtém as propriedades do arquivo
            String url = properties.getProperty("dburl");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            // Estabelece a conexão com o banco de dados
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão com o banco de dados PostgreSQL estabelecida com sucesso.");

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de propriedades: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            reestabelecerConexao(); // Método para reabrir a conexão
        }
        return connection;
    }

    private void reestabelecerConexao() throws SQLException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("C:\\Users\\davil\\eclipse-workspace\\GerenciamentoDeProdutos\\src\\db.properties")) {
            properties.load(input);
            String url = properties.getProperty("dburl");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de propriedades: " + e.getMessage());
            throw new SQLException("Não foi possível reestabelecer a conexão", e);
        }
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
