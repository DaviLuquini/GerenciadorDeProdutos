package GestaoDeProdutos;

import java.util.Scanner;
import GestaoDeProdutos.Entidades.Produto;
import GestaoDeProdutos.Entidades.ProdutoFabrica;
import GestaoDeProdutos.Entidades.IProdutoFabrica;
import GestaoDeProdutos.Entidades.NotaProduto;
import GestaoDeProdutos.Infraestrutura.*;
import GestaoDeProdutos.Servico.*;

public class Programa {
    public static void main(String[] args) {
        // Inicializando repositórios e gerenciadores
    	DbConnection dbConnection = new DbConnection();
    	
        IProdutoRepositorio produtoRepositorio = new ProdutoRepositorio(dbConnection);
        INotaProdutoRepositorio notaProdutoRepositorio = new NotaProdutoRepositorio(dbConnection);
        IProdutoFabrica produtoFabrica = new ProdutoFabrica();
        
        ProdutoManager produtoManager = new ProdutoManager(produtoRepositorio, produtoFabrica);
        NotaManager notaManager = new NotaManager(produtoRepositorio, notaProdutoRepositorio);

        Scanner sc = new Scanner(System.in);
        boolean encerrarPrograma = false;

        while (!encerrarPrograma) {
            exibirMenu();
            int opcao = lerInteiro(sc, "Digite uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrarProduto(produtoManager, sc);
                    case 2 -> adicionarNota(notaManager, sc);
                    case 3 -> atualizarEstoque(produtoManager, sc);
                    case 4 -> excluirProduto(produtoManager, sc);
                    case 5 -> listarProdutos(produtoManager);
                    case 6 -> listarNotas(notaManager);
                    case 7 -> {
                        encerrarPrograma = true;
                        System.out.println("Programa encerrado.");
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static void exibirMenu() {
        System.out.println("\nSelecione uma opção:");
        System.out.println("(1) Cadastrar Produto");
        System.out.println("(2) Adicionar Nota (Compra/Venda)");
        System.out.println("(3) Atualizar Estoque");
        System.out.println("(4) Excluir Produto");
        System.out.println("(5) Listar Produtos");
        System.out.println("(6) Listar Notas");
        System.out.println("(7) Sair");
    }

    private static void cadastrarProduto(ProdutoManager produtoManager, Scanner sc) throws Exception {
        System.out.println("=== Cadastrar Produto ===");
        int produtoId = lerInteiro(sc, "Digite o código do produto: ");
        System.out.println("Digite o nome do produto:");
        sc.nextLine(); // Consumir quebra de linha
        String nome = sc.nextLine();
        System.out.println("Digite a categoria do produto:");
        String categoria = sc.nextLine();
        int quantidade = lerInteiro(sc, "Digite a quantidade inicial em estoque: ");

        produtoManager.cadastrarProduto(produtoId, nome, categoria, quantidade);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void adicionarNota(NotaManager notaManager, Scanner sc) throws Exception {
        System.out.println("=== Adicionar Nota (Compra/Venda) ===");
        int produtoId = lerInteiro(sc, "Digite o código do produto: ");
        System.out.println("Digite o tipo de nota (Compra/Venda):");
        sc.nextLine(); // Consumir quebra de linha
        String tipo = sc.nextLine().toLowerCase();
        int quantidade = lerInteiro(sc, "Digite a quantidade: ");
        double preco = lerDouble(sc, "Digite o preço unitário: ");

        if (tipo.equals("compra")) {
            notaManager.adicionarNotaCompra(produtoId, quantidade, preco);
            System.out.println("Nota de compra adicionada com sucesso!");
        } else if (tipo.equals("venda")) {
            notaManager.adicionarNotaVenda(produtoId, quantidade, preco);
            System.out.println("Nota de venda adicionada com sucesso!");
        } else {
            System.out.println("Tipo de nota inválido. Use 'Compra' ou 'Venda'.");
        }
    }

    private static void atualizarEstoque(ProdutoManager produtoManager, Scanner sc) throws Exception {
        System.out.println("=== Atualizar Estoque ===");
        int produtoId = lerInteiro(sc, "Digite o código do produto: ");
        int novaQuantidade = lerInteiro(sc, "Digite a nova quantidade em estoque: ");

        produtoManager.atualizarProduto(produtoId , novaQuantidade);
        System.out.println("Estoque atualizado com sucesso!");
    }

    private static void excluirProduto(ProdutoManager produtoManager, Scanner sc) throws Exception {
        System.out.println("=== Excluir Produto ===");
        int produtoId = lerInteiro(sc, "Digite o código do produto a ser excluído: ");

        produtoManager.excluirProduto(produtoId);
        System.out.println("Produto excluído com sucesso!");
    }

    private static void listarProdutos(ProdutoManager produtoManager) throws Exception {
        System.out.println("=== Lista de Produtos ===");
        for (Produto produto : produtoManager.listarProdutos()) {
            System.out.println(
                    "ID: " + produto.getProdutoId() +
                    ", Nome: " + produto.getNome() +
                    ", Categoria: " + produto.getCategoria() +
                    ", Quantidade: " + produto.getQuantidadeEstoque());
        }
    }
    
    private static void listarNotas(NotaManager notaManager) throws Exception {
        System.out.println("=== Lista de Notas de Produto ===");
        
        // Obtém a lista de notas de produto através do serviço
        for (NotaProduto nota : notaManager.listarNotas()) {
            System.out.println(
                "Produto ID: " + nota.getProdutoId() +
                ", Tipo: " + nota.getTipo() +
                ", Quantidade: " + nota.getQuantidade() +
                ", Preço: " + nota.getPreco() +
                ", Data: " + nota.getData());
        }
    }

    private static int lerInteiro(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número inteiro.");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double lerDouble(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        while (!sc.hasNextDouble()) {
            System.out.println("Entrada inválida. Digite um número decimal.");
            sc.next();
        }
        return sc.nextDouble();
    }
}
