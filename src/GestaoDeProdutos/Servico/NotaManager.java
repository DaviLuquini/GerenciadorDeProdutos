package GestaoDeProdutos.Servico;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import GestaoDeProdutos.Entidades.NotaProduto;
import GestaoDeProdutos.Entidades.Produto;
import GestaoDeProdutos.Infraestrutura.INotaProdutoRepositorio;
import GestaoDeProdutos.Infraestrutura.IProdutoRepositorio;

public class NotaManager {
    private IProdutoRepositorio produtoRepositorio;
    private INotaProdutoRepositorio notaProdutoRepositorio;

    public NotaManager(IProdutoRepositorio produtoRepositorio, INotaProdutoRepositorio notaProdutoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
        this.notaProdutoRepositorio = notaProdutoRepositorio;
    }

    // Método para adicionar uma nota de compra
    public void adicionarNotaCompra(int produtoId, int quantidade, double preco) throws SQLException {
        Produto produto = produtoRepositorio.buscarProdutoPorId(produtoId);
        if (produto != null) {
            // Atualizar o estoque do produto
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
            produtoRepositorio.atualizarProduto(produto);

            // Registrar a nota de compra
            NotaProduto nota = new NotaProduto(produtoId, "compra", quantidade, preco, LocalDate.now());
            notaProdutoRepositorio.inserirNotaProduto(nota);
        } else {
            throw new IllegalArgumentException("Produto não encontrado!");
        }
    }

    // Método para adicionar uma nota de venda
    public void adicionarNotaVenda(int produtoId, int quantidade, double preco) throws SQLException {
        Produto produto = produtoRepositorio.buscarProdutoPorId(produtoId);
        if (produto != null) {
            if (produto.getQuantidadeEstoque() >= quantidade) {
                // Atualizar o estoque do produto
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
                produtoRepositorio.atualizarProduto(produto);

                // Registrar a nota de venda
                NotaProduto nota = new NotaProduto(produtoId, "venda", quantidade, preco, LocalDate.now());
                notaProdutoRepositorio.inserirNotaProduto(nota);
            } else {
                throw new IllegalArgumentException("Estoque insuficiente!");
            }
        } else {
            throw new IllegalArgumentException("Produto não encontrado!");
        }
    }
    
    public List<NotaProduto> listarNotas() throws SQLException {
        return notaProdutoRepositorio.listarNotas();
    }
}

