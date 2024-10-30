package GestaoDeProdutos;
import java.util.Random;

import GestaoDeProdutos.Entidades.*;
import GestaoDeProdutos.Infraestrutura.*;
import GestaoDeProdutos.Servico.EstoqueManager;

public class Programa {
    public static void main(String[] args) {
        // Inicializando o repositório, fábrica e serviço
        IProdutoFabrica produtoFabrica = new ProdutoFabrica();
        IProdutoRepositorio produtoRepositorio = new ProdutoRepositorio(produtoFabrica);
        EstoqueManager estoqueManagerServico = new EstoqueManager(produtoRepositorio, produtoFabrica);
        Random random = new Random();
        int randomCodigoCamisa = random.nextInt(100) + 1; // Gera um número de 1 a 100
        int randomCodigoBermuda = random.nextInt(100) + 1; // Gera um número de 1 a 100

        // Adicionando uma Camisa
        //estoqueManagerServico.adicionarCamisa("Camisa", randomCodigoCamisa, "Camisa Polo", 10, 49.90, "Curta", "M");

        // Adicionando uma Bermuda
        //estoqueManagerServico.adicionarBermuda("Bermuda", randomCodigoBermuda, "Bermuda Jeans", 5, 79.90, "Azul", 38);

        // Listando todos os produtos
        System.out.println("\nLista de produtos no estoque:");
        estoqueManagerServico.listarProdutos();

        // Atualizando a quantidade de uma Camisa
        System.out.println("\nAtualizando a quantidade de Camisa Polo para 15...");
        estoqueManagerServico.atualizarQuantidade("Camisa", 9, 15);

        // Listando todos os produtos novamente para ver a atualização
        System.out.println("\nLista de produtos após atualização:");
        estoqueManagerServico.listarProdutos();

        // Removendo a Bermuda do estoque
        System.out.println("\nRemovendo Bermuda Jeans do estoque...");
        estoqueManagerServico.removerProduto("Bermuda" , 23);

        // Listando todos os produtos para verificar a remoção
        System.out.println("\nLista de produtos após remoção:");
        estoqueManagerServico.listarProdutos();

     // Tentando buscar um produto existente
        System.out.println("\nBuscando um produto existente:");
        Produto produtoBuscado = estoqueManagerServico.buscarProduto("Camisa" ,9);
        System.out.println(produtoBuscado.getNome());
        
        // Tentando buscar um produto que não existe
        System.out.println("\nBuscando um produto inexistente:");
        estoqueManagerServico.buscarProduto("Camisa" ,3);
    }
}
