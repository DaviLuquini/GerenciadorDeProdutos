

/**

import GestaoDeProdutos.Entidades.*;
import GestaoDeProdutos.Infraestrutura.*;
import GestaoDeProdutos.Servico.EstoqueManager;

public class Programa {
    public static void main(String[] args) {
        // Inicializando o repositório, fábrica e serviço
        IProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
        IProdutoFabrica produtoFabrica = new ProdutoFabrica();
        EstoqueManager estoqueManagerServico = new EstoqueManager(produtoRepositorio, produtoFabrica);

        // Adicionando uma Camisa
        estoqueManagerServico.adicionarProduto(1, "Camisa Polo", 10, 49.90, "Curta", "M");

        // Adicionando uma Bermuda
        estoqueManagerServico.adicionarProduto(2, "Bermuda Jeans", 5, 79.90, "Azul", 38);

        // Listando todos os produtos
        System.out.println("\nLista de produtos no estoque:");
        estoqueManagerServico.listarProdutos();

        // Atualizando a quantidade de uma Camisa
        System.out.println("\nAtualizando a quantidade de Camisa Polo para 15...");
        camisa.setQuantidade(15);
        estoqueManagerServico.atualizarQuantidade(camisa.getCodigo(), camisa.getQuantidade());

        // Listando todos os produtos novamente para ver a atualização
        System.out.println("\nLista de produtos após atualização:");
        estoqueManagerServico.listarProdutos();

        // Removendo a Bermuda do estoque
        System.out.println("\nRemovendo Bermuda Jeans do estoque...");
        estoqueManagerServico.removerProduto(bermuda.getCodigo());

        // Listando todos os produtos para verificar a remoção
        System.out.println("\nLista de produtos após remoção:");
        estoqueManagerServico.listarProdutos();

        // Tentando buscar um produto que não existe
        System.out.println("\nBuscando um produto inexistente:");
        estoqueManagerServico.buscarProduto(3);
    }
}

 */