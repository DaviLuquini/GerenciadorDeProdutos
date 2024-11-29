package GestaoDeProdutos.Entidades;

import java.time.LocalDate;

public interface IProdutoFabrica {
	Produto criarProduto(int produtoId, String nome, String categoria, int quantidadeEstoque, LocalDate dataCadastro);
	
}

