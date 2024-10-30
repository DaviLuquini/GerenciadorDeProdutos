package GestaoDeProdutos.Servico;

import java.util.List;

import GestaoDeProdutos.Entidades.Bermuda;
import GestaoDeProdutos.Entidades.Camisa;
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
	public void adicionarCamisa(String tipo, int codigo, String nome, int quantidade, double preco, String manga, String tamanho) {
		Camisa novaCamisa = produtoFabrica.criarCamisa(tipo, codigo, nome, quantidade, preco, manga, tamanho);
		
		produtoRepositorio.adicionarCamisa(novaCamisa);
	}

	@Override
	// Adiciona um produto ao estoque
	public void adicionarBermuda(String tipo, int codigo, String nome, int quantidade, double preco, String cor, int comprimento) {
		Bermuda novaBermuda = produtoFabrica.criarBermuda(tipo ,codigo, nome, quantidade, preco, cor, comprimento);
		
		produtoRepositorio.adicionarBermuda(novaBermuda);
	}

	// Atualiza a quantidade de um produto em estoque
	@Override
	public void atualizarQuantidade(String tipo, int codigo, int novaQuantidade) {
		Produto produto = buscarProduto(tipo, codigo);
		if (produto != null) {
			produto.setQuantidade(novaQuantidade);
			produtoRepositorio.atualizarQuantidade(produto); // Atualiza no repositório
            System.out.println("Quantidade do produto atualizada: " + produto.getNome() + " - " + produto.getQuantidade());
		} else {
			System.out.println("Produto para atualizar quantidade não encotrado");
		}
	}

	// Remove um produto do estoque
	@Override
	public void removerProduto(String tipo, int codigo) {
		Produto produto = buscarProduto(tipo ,codigo);
		if (produto != null) {
			produtoRepositorio.removerProduto(produto.getNome() ,codigo); // Remove do repositório
		}
	}

	// Lista todos os produtos no estoque
	@Override
	public void listarProdutos() {
		List<Camisa> listaDeCamisas = produtoRepositorio.listarCamisas(); // Lista todas camisas do repositório
		for (Produto produto : listaDeCamisas) {
		    System.out.println(produto.getNome() + " - " + produto.getQuantidade());
		}
		
		List<Bermuda> listaDeBermudas = produtoRepositorio.listarBermudas(); // Lista todas bermudas do repositório
		for (Produto produto : listaDeBermudas) {
		    System.out.println(produto.getNome() + " - " + produto.getQuantidade());
		}
	}

	// Método auxiliar para buscar um produto pelo código
	@Override
	public Produto buscarProduto(String tipo, int codigo) {
		if(tipo == "Camisa") {
			for (Produto produto : produtoRepositorio.listarCamisas()) { // Usa o repositório para buscar
				if (produto.getCodigo() == codigo) {
					return produto;
				}
			}
		} else {
			for (Produto produto : produtoRepositorio.listarBermudas()) { // Usa o repositório para buscar
				if (produto.getCodigo() == codigo) {
					return produto;
				}
			}
		}
		
		System.out.println("Produto buscado não encontrado.");
		return null;
	}

}
