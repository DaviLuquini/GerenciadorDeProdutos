package GestaoDeProdutos.Servico;

import java.util.List;

import GestaoDeProdutos.Entidades.Produto;

public interface IEstoqueManager {
    // Adiciona uma bermuda ao estoque
    void adicionarProduto(int codigo, String nome, int quantidade, double preco, String cor, int comprimento);
    
    // Adiciona uma camisa ao estoque
    void adicionarProduto(int codigo, String nome, int quantidade, double preco, String manga, String tamanho);

    // Atualiza a quantidade de um produto em estoque
    void atualizarQuantidade(int codigo, int novaQuantidade);

    // Remove um produto do estoque
    void removerProduto(int codigo);

    // Lista todos os produtos no estoque
    List<Produto> listarProdutos();
    
    //Busca produto no estoque
    Produto buscarProduto(int codigo);
}