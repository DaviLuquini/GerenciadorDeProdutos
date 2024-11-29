package GestaoDeProdutos.Servico;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import GestaoDeProdutos.Entidades.IProdutoFabrica;
import GestaoDeProdutos.Entidades.Produto;
import GestaoDeProdutos.Infraestrutura.IProdutoRepositorio;

public class ProdutoManager {
    private final IProdutoRepositorio produtoRepositorio;
    private final IProdutoFabrica produtoFabrica;

    public ProdutoManager(IProdutoRepositorio produtoRepositorio, IProdutoFabrica produtoFabrica) {
        this.produtoRepositorio = produtoRepositorio;
		this.produtoFabrica = produtoFabrica;
    }

    // Método para cadastrar um novo produto
    public void cadastrarProduto(int produtoId, String nome, String categoria, int quantidadeEstoque) throws SQLException {
        Produto produtoExistente = produtoRepositorio.buscarProdutoPorId(produtoId);
        if (produtoExistente != null) {
            throw new IllegalArgumentException("Produto com o mesmo ID já existe!");
        }

        Produto novoProduto = produtoFabrica.criarProduto(produtoId, nome, categoria, quantidadeEstoque, LocalDate.now());
        
        produtoRepositorio.inserirProduto(novoProduto);
    }

    // Método para buscar um produto por ID
    public Produto buscarProduto(int produtoId) throws SQLException {
        Produto produto = produtoRepositorio.buscarProdutoPorId(produtoId);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado!");
        }
        return produto;
    }

    // Método para listar todos os produtos
    public List<Produto> listarProdutos() throws SQLException {
        return produtoRepositorio.listarProdutos();
    }

    // Método para atualizar os dados de um produto
    public void atualizarProduto(int produtoId, int novaQuantidadeEstoque) throws SQLException {
        Produto produtoExistente = produtoRepositorio.buscarProdutoPorId(produtoId);
        if (produtoExistente == null) {
            throw new IllegalArgumentException("Produto não encontrado!");
        }

        produtoExistente.setQuantidadeEstoque(novaQuantidadeEstoque);

        produtoRepositorio.atualizarProduto(produtoExistente);
    }

    // Método para excluir um produto pelo ID
    public void excluirProduto(int produtoId) throws SQLException {
        Produto produto = produtoRepositorio.buscarProdutoPorId(produtoId);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado!");
        }

        produtoRepositorio.excluirProduto(produtoId);
    }
}

