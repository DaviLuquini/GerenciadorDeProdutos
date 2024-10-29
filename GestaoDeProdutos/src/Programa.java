
import GestaoDeProdutos.Entidades.*;
import GestaoDeProdutos.Infraestrutura.*;
import GestaoDeProdutos.Servico.EstoqueManager;

public class Programa {
    public static void main(String[] args) {
        // Inicializando o repositório, fábrica e serviço
        IProdutoFabrica produtoFabrica = new ProdutoFabrica();
        IProdutoRepositorio produtoRepositorio = new ProdutoRepositorio(produtoFabrica);
        EstoqueManager estoqueManagerServico = new EstoqueManager(produtoRepositorio, produtoFabrica);

        // Adicionando uma Camisa
        estoqueManagerServico.adicionarCamisa(1, "Camisa Polo", 10, 49.90, "Curta", "M");

        // Adicionando uma Bermuda
        estoqueManagerServico.adicionarBermuda(2, "Bermuda Jeans", 5, 79.90, "Azul", 38);

        // Listando todos os produtos
        System.out.println("\nLista de produtos no estoque:");
        estoqueManagerServico.listarProdutos();

        // Atualizando a quantidade de uma Camisa
        System.out.println("\nAtualizando a quantidade de Camisa Polo para 15...");
        estoqueManagerServico.atualizarQuantidade("Camisa", 1, 15);

        // Listando todos os produtos novamente para ver a atualização
        System.out.println("\nLista de produtos após atualização:");
        estoqueManagerServico.listarProdutos();

        // Removendo a Bermuda do estoque
        System.out.println("\nRemovendo Bermuda Jeans do estoque...");
        estoqueManagerServico.removerProduto("Bermuda" ,2);

        // Listando todos os produtos para verificar a remoção
        System.out.println("\nLista de produtos após remoção:");
        estoqueManagerServico.listarProdutos();

        // Tentando buscar um produto que não existe
        System.out.println("\nBuscando um produto inexistente:");
        estoqueManagerServico.buscarProduto("Camisa" ,3);
    }
}
