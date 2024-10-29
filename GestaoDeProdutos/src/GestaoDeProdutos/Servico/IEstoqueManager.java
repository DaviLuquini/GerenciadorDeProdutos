package GestaoDeProdutos.Servico;

import java.util.List;

import GestaoDeProdutos.Entidades.Produto;

public interface IEstoqueManager {
    // Adiciona uma camisa ao estoque
    void adicionarCamisa(int codigo, String nome, int quantidade, double preco, String manga, String tamanho);
	
    // Adiciona uma bermuda ao estoque
    void adicionarBermuda(int codigo, String nome, int quantidade, double preco, String cor, int comprimento);

    // Atualiza a quantidade de um produto em estoque
    void atualizarQuantidade(String nome, int codigo, int novaQuantidade);

    // Remove um produto do estoque
    void removerProduto(String nome, int codigo);

    // Lista todos os produtos no estoque
    void listarProdutos();
    
    //Busca produto no estoque
    Produto buscarProduto(String nome, int codigo);
}
