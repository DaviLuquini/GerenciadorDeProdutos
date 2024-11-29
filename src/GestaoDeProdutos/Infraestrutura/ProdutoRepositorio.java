package GestaoDeProdutos.Infraestrutura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import GestaoDeProdutos.Entidades.Produto;

public class ProdutoRepositorio implements IProdutoRepositorio {
    private final DbConnection dbConnection;

    public ProdutoRepositorio(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Método para inserir um produto no banco de dados
    public void inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (produtoId, nome, categoria, quantidadeestoque, datacadastro) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, produto.getProdutoId());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCategoria());
            stmt.setInt(4, produto.getQuantidadeEstoque());
            stmt.setObject(5, produto.getDataCadastro());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar produto ao banco de dados: " + e.getMessage());
            System.err.println("StackTrace:");
            e.printStackTrace(); // Mostra o trace completo no console para análise
            throw e; // Relança a exceção para ser tratada na camada superior
        }
    }
    
    // Método para buscar um produto por ID
    public Produto buscarProdutoPorId(int produtoId) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE produtoId = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produtoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                        rs.getInt("produtoId"),
                        rs.getString("nome"),
                        rs.getString("categoria"),
                        rs.getInt("quantidadeEstoque"),
                        rs.getObject("dataCadastro", LocalDate.class)
                    );
                }
            }
        }
        return null;
    }

    // Método para listar todos os produtos
    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produtos.add(new Produto(
                    rs.getInt("produtoId"),
                    rs.getString("nome"),
                    rs.getString("categoria"),
                    rs.getInt("quantidadeEstoque"),
                    rs.getObject("dataCadastro", LocalDate.class)
                ));
            }
        }
        return produtos;
    }

    // Método para atualizar um produto
    public void atualizarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET quantidadeEstoque = ? WHERE produtoId = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produto.getQuantidadeEstoque());
            stmt.setInt(2, produto.getProdutoId());
            stmt.executeUpdate();
        }
    }

 // Método para excluir um produto por ID
    public void excluirProduto(int produtoId) throws SQLException {
        // SQL para excluir da tabela 'notas_produtos' e depois da tabela 'produtos'
        String sqlNotasProdutos = "DELETE FROM notas_produtos WHERE produtoId = ?";
        String sqlProdutos = "DELETE FROM produtos WHERE produtoId = ?";

        try (Connection conn = dbConnection.getConnection()) {
            // Primeiro, exclui os registros na tabela 'notas_produtos'
            try (PreparedStatement stmtNotasProdutos = conn.prepareStatement(sqlNotasProdutos)) {
                stmtNotasProdutos.setInt(1, produtoId);
                stmtNotasProdutos.executeUpdate();
            }

            // Depois, exclui o produto da tabela 'produtos'
            try (PreparedStatement stmtProdutos = conn.prepareStatement(sqlProdutos)) {
                stmtProdutos.setInt(1, produtoId);
                stmtProdutos.executeUpdate();
            }
        }
    }
}
