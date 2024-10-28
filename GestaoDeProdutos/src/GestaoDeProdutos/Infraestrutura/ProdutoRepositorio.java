package GestaoDeProdutos.Infraestrutura;

import java.util.ArrayList;
import java.util.List;
import GestaoDeProdutos.Entidades.Produto;

public class ProdutoRepositorio implements IProdutoRepositorio {
    // Lista que age como o "estoque" em memória
    private List<Produto> produtos;

    public ProdutoRepositorio() {
        this.produtos = new ArrayList<>();
    }

    // Adiciona um produto ao estoque
    @Override
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto adicionado: " + produto.getNome());
    }

    // Atualiza a quantidade de um produto em estoque
    @Override
    public void atualizarQuantidade(Produto produto) {
        Produto produtoExistente = buscarProduto(produto.getCodigo());
        if (produtoExistente != null) {
            produtoExistente.setQuantidade(produto.getQuantidade());
            System.out.println("Quantidade do produto atualizada: " + produto.getNome());
        } else {
            System.out.println("Produto não encontrado para atualizar.");
        }
    }

    // Remove um produto do estoque
    @Override
    public void removerProduto(int codigo) {
        Produto produto = buscarProduto(codigo);
        if (produto != null) {
            produtos.remove(produto);
            System.out.println("Produto removido: " + produto.getNome());
        } else {
            System.out.println("Produto não encontrado para remoção.");
        }
    }

    // Lista todos os produtos no estoque
    @Override
    public List<Produto> listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto no estoque.");
        }
        return produtos;
    }

    // Busca um produto pelo código no estoque
    @Override
    public Produto buscarProduto(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        System.out.println("Produto com código " + codigo + " não encontrado.");
        return null;
    }
}