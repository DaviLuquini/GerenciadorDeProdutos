package GestaoDeProdutos.Entidades;

public abstract class Produto {
	private String tipo;      // Tipo do produto
    private int codigo;       // Código do produto
    private String nome;      // Nome do produto
    private int quantidade;   // Quantidade em estoque
    private double preco;     // Preço unitário do produto

    public Produto(String tipo ,int codigo, String nome, int quantidade, double preco) {
        this.tipo = tipo;
    	this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e setters
    public String getTipo() {
        return tipo;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
