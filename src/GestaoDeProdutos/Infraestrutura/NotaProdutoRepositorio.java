package GestaoDeProdutos.Infraestrutura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import GestaoDeProdutos.Entidades.NotaProduto;

public class NotaProdutoRepositorio implements INotaProdutoRepositorio {
	  private final DbConnection dbConnection;

	    public NotaProdutoRepositorio(DbConnection dbConnection) {
	        this.dbConnection = dbConnection;
	    }


    @Override
    public void inserirNotaProduto(NotaProduto notaProduto) throws SQLException {
        String sql = "INSERT INTO notas_produtos (produtoId, tipo, quantidade, preco, data) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notaProduto.getProdutoId());
            stmt.setString(2, notaProduto.getTipo());
            stmt.setInt(3, notaProduto.getQuantidade());
            stmt.setDouble(4, notaProduto.getPreco());
            stmt.setObject(5, notaProduto.getData());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<NotaProduto> buscarNotasPorProdutoId(int produtoId) throws SQLException {
        List<NotaProduto> notas = new ArrayList<>();
        String sql = "SELECT * FROM notas_produtos WHERE produtoId = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produtoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notas.add(new NotaProduto(
                        rs.getInt("produtoId"),
                        rs.getString("tipo"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"),
                        rs.getObject("data", LocalDate.class)
                    ));
                }
            }
        }
        return notas;
    }

    @Override
    public List<NotaProduto> listarNotas() throws SQLException {
        List<NotaProduto> notas = new ArrayList<>();
        String sql = "SELECT * FROM notas_produtos";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                notas.add(new NotaProduto(
                    rs.getInt("produtoId"),
                    rs.getString("tipo"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco"),
                    rs.getObject("data", LocalDate.class)
                ));
            }
        }
        return notas;
    }
}
