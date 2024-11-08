package GestaoDeProdutos.Infraestrutura;

import java.util.List;

import GestaoDeProdutos.Entidades.Bermuda;
import GestaoDeProdutos.Entidades.Camisa;
import GestaoDeProdutos.Entidades.Produto;

public interface IProdutoRepositorio {
	// Adiciona uma camisa ao estoque
    void adicionarCamisa(Camisa camisa);
    
	// Adiciona uma bermuda ao estoque
    void adicionarBermuda(Bermuda bermuda);

    // Atualiza a quantidade de um produto em estoque
	void atualizarQuantidade(Produto produto);

    // Remove um produto do estoque
	void removerProduto(Produto produto);

    // Lista todos as camisas no estoque
    List<Camisa> listarCamisas();
    
    // Lista todos as bermudas no estoque
    List<Bermuda> listarBermudas();
    
    //Busca camisa no estoque
    Camisa buscarCamisa(int codigo);
    
    //Busca bermuda no estoque
    Bermuda buscarBermuda(int codigo);
 }
