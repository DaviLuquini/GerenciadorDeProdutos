package GestaoDeProdutos.Entidades;

public interface IProdutoFabrica {
    Produto criarProduto(int codigo, String nome, int quantidade, double preco, String manga, String tamanho);
    
    Produto criarProduto(int codigo, String nome, int quantidade, double preco, String cor, int comprimento);
}

