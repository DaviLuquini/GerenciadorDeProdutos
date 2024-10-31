package GestaoDeProdutos;
import java.util.Arrays;
import java.util.List;
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
        List<String> nomesAleatoriosCamisa = Arrays.asList("Polo Ralph Lauren", "Lacoste", "Calvin Klein", "Levis");
        List<String> nomesAleatoriosBermuda = Arrays.asList("Lacoste" ,"Adidas" ,"Nike" ,"Levis");
        
        int randomCodigoProduto = random.nextInt(100) + 1; // Gera um número de 1 a 100
        int randomNomeProduto = random.nextInt(nomesAleatoriosCamisa.size());
        

        // Adicionando uma Camisa aleatoria & Bermuda Lacoste
        estoqueManagerServico.adicionarCamisa("Camisa", randomCodigoProduto, nomesAleatoriosCamisa.get(randomNomeProduto), 30, 49.90, "Curta", "M");
        estoqueManagerServico.adicionarCamisa("Camisa", 100, "Lacoste", 10, 69.90, "Longa", "GG");
        
        // Adicionando uma Bermuda aleatoria & Bermuda Adidas
        estoqueManagerServico.adicionarBermuda("Bermuda", randomCodigoProduto, nomesAleatoriosBermuda.get(randomNomeProduto), 5, 79.90, "Azul", 38);
        estoqueManagerServico.adicionarBermuda("Bermuda", 200, "Adidas", 35, 40.20, "Preta", 40);

        // Listando todos os produtos
        System.out.println("\nLista de produtos no estoque:");
        estoqueManagerServico.listarProdutos();

        // Atualizando a quantidade de uma Camisa
        System.out.println("\nAtualizando a quantidade da Camisa Lacoste para 15...");
        estoqueManagerServico.atualizarQuantidade("Camisa", 100, 15);

        // Listando todos os produtos novamente para ver a atualização
        System.out.println("\nLista de produtos após atualização:");
        estoqueManagerServico.listarProdutos();

        // Removendo a Camisa Lacoste & Bermuda Adidas do estoque
        System.out.println("\nRemovendo Camisa Lacoste & Bermuda Adidas do estoque...");
        estoqueManagerServico.removerProduto("Camisa" , 100);
        estoqueManagerServico.removerProduto("Bermuda" , 200);

        // Listando todos os produtos para verificar a remoção
        System.out.println("\nLista de produtos após remoção:");
        estoqueManagerServico.listarProdutos();

     // Tentando buscar um produto existente
        System.out.println("\nBuscando um produto existente:");
        Produto produtoBuscado = estoqueManagerServico.buscarProduto("Camisa" ,100);
        if(produtoBuscado != null) {
            System.out.println("Produto encontrado: " + "Camisa " + produtoBuscado.getNome() + " - " + produtoBuscado.getQuantidade() + "und.");
        }
        
        // Tentando buscar um produto que não existe
        System.out.println("\nBuscando um produto inexistente:");
        estoqueManagerServico.buscarProduto("Camisa" ,3);
    }
}
