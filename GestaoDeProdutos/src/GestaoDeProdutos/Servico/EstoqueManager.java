package GestaoDeProdutos.Servico;

import java.util.List;

import GestaoDeProdutos.Entidades.IProdutoFabrica;
import GestaoDeProdutos.Entidades.Produto;
import GestaoDeProdutos.Infraestrutura.IProdutoRepositorio;

public class EstoqueManager implements IEstoqueManager {
	private IProdutoRepositorio produtoRepositorio;
	private IProdutoFabrica produtoFabrica;

	// Injeção de dependência
	public EstoqueManager(IProdutoRepositorio produtoRepositorio, IProdutoFabrica produtoFabrica) {
		this.produtoRepositorio = produtoRepositorio;
		this.produtoFabrica = produtoFabrica;
	}

	@Override
	// Adiciona um produto ao estoque
	public void adicionarProduto(int codigo, String nome, int quantidade, double preco, String cor, int comprimento) {
		Produto novaBermuda = produtoFabrica.criarProduto(codigo, nome, quantidade, preco, cor, comprimento);
		
		produtoRepositorio.adicionarProduto(novaBermuda);
	}
	
	@Override
	// Adiciona um produto ao estoque
	public void adicionarProduto(int codigo, String nome, int quantidade, double preco, String manga, String tamanho) {
		Produto novaCamisa = produtoFabrica.criarProduto(codigo, nome, quantidade, preco, manga, tamanho);
		
		produtoRepositorio.adicionarProduto(novaCamisa);
	}

	// Atualiza a quantidade de um produto em estoque
	public void atualizarQuantidade(int codigo, int novaQuantidade) {
		Produto produto = buscarProduto(codigo);
		if (produto != null) {
			produto.setQuantidade(novaQuantidade);
			produtoRepositorio.atualizarQuantidade(produto); // Atualiza no repositório
		}
	}

	// Remove um produto do estoque
	public void removerProduto(int codigo) {
		Produto produto = buscarProduto(codigo);
		if (produto != null) {
			produtoRepositorio.removerProduto(codigo); // Remove do repositório
		}
	}

	// Lista todos os produtos no estoque
	public List<Produto> listarProdutos() {
		return produtoRepositorio.listarProdutos(); // Lista todos do repositório
	}

	// Método auxiliar para buscar um produto pelo código
	public Produto buscarProduto(int codigo) {
		for (Produto produto : produtoRepositorio.listarProdutos()) { // Usa o repositório para buscar
			if (produto.getCodigo() == codigo) {
				return produto;
			}
		}
		return null;
	}

}