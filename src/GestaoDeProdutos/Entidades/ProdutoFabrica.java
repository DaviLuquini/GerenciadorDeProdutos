package GestaoDeProdutos.Entidades;

import java.time.LocalDate;

public class ProdutoFabrica implements IProdutoFabrica {

    @Override
	public Produto criarProduto(int produtoId, String nome, String categoria, int quantidadeEstoque, LocalDate dataCadastro) {
        return new Produto(produtoId , nome, categoria, quantidadeEstoque, dataCadastro);
    }
}


