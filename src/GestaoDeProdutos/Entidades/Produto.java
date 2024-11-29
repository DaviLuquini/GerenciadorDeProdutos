package GestaoDeProdutos.Entidades;

import java.time.LocalDate;

public class Produto {
    private int produtoId;         // Identificador único do produto
    private String nome;           // Nome do produto
    private String categoria;      // Categoria do produto
    private int quantidadeEstoque; // Quantidade em estoque
    private LocalDate dataCadastro; // Data de cadastro do produto

    // Construtor
    public Produto(int produtoId, String nome, String categoria, int quantidadeEstoque, LocalDate dataCadastro) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
        this.dataCadastro = dataCadastro;
    }

    // Getters e setters
    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
