package GestaoDeProdutos.Entidades;

import java.time.LocalDate;

public class NotaProduto {
	private int produtoId;
    private String tipo; 
    private int quantidade;
    private double preco;
    private LocalDate data;

    // Construtor
    public NotaProduto(int produtoId, String tipo, int quantidade, double preco, LocalDate data) {
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco = preco;
        this.data = data;
    }

    // Getter e Setter para produtoId
    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    // Getter e Setter para tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter e Setter para quantidade
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Getter e Setter para preco
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Getter e Setter para data
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    @Override
	public String toString() {
		return "NotaProduto [produtoId=" + produtoId + ", tipo=" + tipo + ", quantidade=" + quantidade + ", preco="
				+ preco + ", data=" + data + "]";
	}

}
