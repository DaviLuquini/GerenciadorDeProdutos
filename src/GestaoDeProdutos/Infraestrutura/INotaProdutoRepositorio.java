package GestaoDeProdutos.Infraestrutura;

import java.sql.SQLException;
import java.util.List;

import GestaoDeProdutos.Entidades.NotaProduto;

public interface INotaProdutoRepositorio {
    // Método para inserir uma nota de produto no banco de dados
    void inserirNotaProduto(NotaProduto notaProduto) throws SQLException;

    // Método para buscar notas de um produto específico pelo ID do produto
    List<NotaProduto> buscarNotasPorProdutoId(int produtoId) throws SQLException;

    // Método para listar todas as notas
    List<NotaProduto> listarNotas() throws SQLException;

}
