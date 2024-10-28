package GestaoDeProdutos.Infraestrutura;

import java.util.List;

import GestaoDeProdutos.Entidades.Produto;

public interface IProdutoRepositorio {
	// Adiciona um produto ao estoque
    void adicionarProduto(Produto produto);

    // Atualiza a quantidade de um produto em estoque
    void atualizarQuantidade(Produto produto);

    // Remove um produto do estoque
    void removerProduto(int codigo);

    // Lista todos os produtos no estoque
    List<Produto> listarProdutos();
    
    //Busca produto no estoque
    //void buscarProduto();
    
    Produto buscarProduto(int codigo);
 }
