package GestaoDeProdutos.Entidades;

public class ProdutoFabrica implements IProdutoFabrica {

    @Override
    public Camisa criarCamisa(String tipo, int codigo, String nome, int quantidade, double preco, String manga, String tamanho) { 
        return new Camisa(tipo, codigo, nome, quantidade, preco, manga, tamanho);
    }

    @Override
    public Bermuda criarBermuda(String tipo, int codigo, String nome, int quantidade, double preco, String cor, int comprimento) { 
        return new Bermuda(tipo ,codigo, nome, quantidade, preco, cor, comprimento);
    }
}


