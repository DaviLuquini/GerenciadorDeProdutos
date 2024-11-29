package GestaoDeProdutos.Infraestrutura;

import java.sql.SQLException;
import java.util.List;

import GestaoDeProdutos.Entidades.Produto;

public interface IProdutoRepositorio {
    // Método para inserir um produto no banco de dados
    void inserirProduto(Produto produto) throws SQLException;

    // Método para buscar um produto por ID
    Produto buscarProdutoPorId(int produtoId) throws SQLException;

    // Método para listar todos os produtos
    List<Produto> listarProdutos() throws SQLException;

    // Método para atualizar um produto existente
    void atualizarProduto(Produto produto) throws SQLException;

    // Método para excluir um produto pelo ID
    void excluirProduto(int produtoId) throws SQLException;
}
