package GestaoDeProdutos.Entidades;

public class ProdutoFabrica implements IProdutoFabrica {

    @Override
    public Produto criarProduto(int codigo, String nome, int quantidade, double preco, String manga, String tamanho) {
        return new Camisa(codigo, nome, quantidade, preco, manga, tamanho);
    }

    @Override
    public Produto criarProduto(int codigo, String nome, int quantidade, double preco, String cor, int comprimento) {
        return new Bermuda(codigo, nome, quantidade, preco, cor, comprimento);
    }
}


