package GestaoDeProdutos.Entidades;

public interface IProdutoFabrica {
    Camisa criarCamisa(String tipo, int codigo, String nome, int quantidade, double preco, String manga, String tamanho);
    
    Bermuda criarBermuda(String tipo, int codigo, String nome, int quantidade, double preco, String cor, int comprimento);
}

