package GestaoDeProdutos;

import GestaoDeProdutos.Entidades.Produto;
import GestaoDeProdutos.Entidades.ProdutoFabrica;
import GestaoDeProdutos.Infraestrutura.DbConnection;
import GestaoDeProdutos.Infraestrutura.INotaProdutoRepositorio;
import GestaoDeProdutos.Infraestrutura.IProdutoRepositorio;
import GestaoDeProdutos.Infraestrutura.NotaProdutoRepositorio;
import GestaoDeProdutos.Infraestrutura.ProdutoRepositorio;
import GestaoDeProdutos.Servico.ProdutoManager;
import GestaoDeProdutos.Servico.NotaManager;
import GestaoDeProdutos.Entidades.IProdutoFabrica;
import GestaoDeProdutos.Entidades.NotaProduto;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Programa {

	public static void main(String[] args) {
		// Inicializando gerenciadores
		DbConnection dbConnection = new DbConnection();

		IProdutoRepositorio produtoRepositorio = new ProdutoRepositorio(dbConnection);
		INotaProdutoRepositorio notaProdutoRepositorio = new NotaProdutoRepositorio(dbConnection);
		IProdutoFabrica produtoFabrica = new ProdutoFabrica();

		ProdutoManager produtoManager = new ProdutoManager(produtoRepositorio, produtoFabrica);
		NotaManager notaManager = new NotaManager(produtoRepositorio, notaProdutoRepositorio);

		// Criando a interface gráfica
		SwingUtilities.invokeLater(() -> {
			try {
				criarInterface(produtoManager, notaManager);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
	
	//Interface Gráfica 
	
	private static void criarInterface(ProdutoManager produtoManager, NotaManager notaManager) throws SQLException {
	    JFrame frame = new JFrame("Gestão de Produtos");
	    frame.setSize(800, 600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());

	    // Painel de botões
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Botões empilhados

	    // Adiciona os botões para cada funcionalidade
	    JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
	    btnCadastrarProduto.addActionListener(e -> executarCadastrarProduto(produtoManager));

	    JButton btnAdicionarNota = new JButton("Adicionar Nota");
	    btnAdicionarNota.addActionListener(e -> executarAdicionarNota(notaManager));

	    JButton btnAtualizarEstoque = new JButton("Atualizar Estoque");
	    btnAtualizarEstoque.addActionListener(e -> executarAtualizarEstoque(produtoManager));

	    JButton btnExcluirProduto = new JButton("Excluir Produto");
	    btnExcluirProduto.addActionListener(e -> executarExcluirProduto(produtoManager));

	    JButton btnListarProdutos = new JButton("Listar Produtos");
	    btnListarProdutos.addActionListener(e -> executarListarProdutos(produtoManager));

	    JButton btnListarNotas = new JButton("Listar Notas");
	    btnListarNotas.addActionListener(e -> executarListarNotas(notaManager));

	    JButton btnFechar = new JButton("Fechar");
	    btnFechar.addActionListener(e -> frame.dispose());

	    // Adicionando os botões ao painel de botões
	    buttonPanel.add(btnCadastrarProduto);
	    buttonPanel.add(btnAdicionarNota);
	    buttonPanel.add(btnAtualizarEstoque);
	    buttonPanel.add(btnExcluirProduto);
	    buttonPanel.add(btnListarProdutos);
	    buttonPanel.add(btnListarNotas);
	    buttonPanel.add(btnFechar);

	    // Adicionando o painel de botões ao frame
	    frame.add(buttonPanel, BorderLayout.CENTER);

	    frame.setVisible(true);
	}

	// Métodos para cada funcionalidade

	private static void executarCadastrarProduto(ProdutoManager produtoManager) {
	    // Exemplo: abrir um diálogo para entrada de dados
	    JTextField produtoIdField = new JTextField();
	    JTextField nomeField = new JTextField();
	    JTextField categoriaField = new JTextField();
	    JTextField quantidadeField = new JTextField();

	    Object[] message = {
	        "Código do Produto:", produtoIdField,
	        "Nome:", nomeField,
	        "Categoria:", categoriaField,
	        "Quantidade Inicial:", quantidadeField,
	    };

	    int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar Produto", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	        try {
	            int produtoId = Integer.parseInt(produtoIdField.getText());
	            String nome = nomeField.getText();
	            String categoria = categoriaField.getText();
	            int quantidade = Integer.parseInt(quantidadeField.getText());

	            produtoManager.cadastrarProduto(produtoId, nome, categoria, quantidade);
	            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
	        }
	    }
	}

	private static void executarAdicionarNota(NotaManager notaManager) {
	    // Exemplo: abrir um diálogo para entrada de dados
	    JTextField produtoIdField = new JTextField();
	    String[] tipos = {"Compra", "Venda"};
	    JComboBox<String> tipoBox = new JComboBox<>(tipos);
	    JTextField quantidadeField = new JTextField();
	    JTextField precoField = new JTextField();

	    Object[] message = {
	        "Código do Produto:", produtoIdField,
	        "Tipo de Nota:", tipoBox,
	        "Quantidade:", quantidadeField,
	        "Preço Unitário:", precoField,
	    };

	    int option = JOptionPane.showConfirmDialog(null, message, "Adicionar Nota", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	        try {
	            int produtoId = Integer.parseInt(produtoIdField.getText());
	            String tipo = (String) tipoBox.getSelectedItem();
	            int quantidade = Integer.parseInt(quantidadeField.getText());
	            double preco = Double.parseDouble(precoField.getText());

	            if ("Compra".equals(tipo)) {
	                notaManager.adicionarNotaCompra(produtoId, quantidade, preco);
	            } else {
	                notaManager.adicionarNotaVenda(produtoId, quantidade, preco);
	            }
	            JOptionPane.showMessageDialog(null, "Nota adicionada com sucesso!");
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Erro ao adicionar nota: " + ex.getMessage());
	        }
	    }
	}

	private static void executarAtualizarEstoque(ProdutoManager produtoManager) {
	    JTextField produtoIdField = new JTextField();
	    JTextField quantidadeField = new JTextField();

	    Object[] message = {
	        "Código do Produto:", produtoIdField,
	        "Nova Quantidade:", quantidadeField,
	    };

	    int option = JOptionPane.showConfirmDialog(null, message, "Atualizar Estoque", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	        try {
	            int produtoId = Integer.parseInt(produtoIdField.getText());
	            int quantidade = Integer.parseInt(quantidadeField.getText());
	            produtoManager.atualizarProduto(produtoId, quantidade);
	            JOptionPane.showMessageDialog(null, "Estoque atualizado com sucesso!");
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque: " + ex.getMessage());
	        }
	    }
	}

	private static void executarExcluirProduto(ProdutoManager produtoManager) {
	    JTextField produtoIdField = new JTextField();

	    Object[] message = {
	        "Código do Produto:", produtoIdField,
	    };

	    int option = JOptionPane.showConfirmDialog(null, message, "Excluir Produto", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	        try {
	            int produtoId = Integer.parseInt(produtoIdField.getText());
	            produtoManager.excluirProduto(produtoId);
	            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex.getMessage());
	        }
	    }
	}

	private static void executarListarProdutos(ProdutoManager produtoManager) {
	    try {
	        List<Produto> produtos = produtoManager.listarProdutos();
	        StringBuilder mensagem = new StringBuilder("=== Lista de Produtos ===\n");
	        for (Produto produto : produtos) {
	            mensagem.append("ID: ").append(produto.getProdutoId())
	                .append(", Nome: ").append(produto.getNome())
	                .append(", Categoria: ").append(produto.getCategoria())
	                .append(", Quantidade: ").append(produto.getQuantidadeEstoque()).append("\n");
	        }
	        JOptionPane.showMessageDialog(null, mensagem.toString());
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + ex.getMessage());
	    }
	}

	private static void executarListarNotas(NotaManager notaManager) {
	    try {
	        List<NotaProduto> notas = notaManager.listarNotas();
	        StringBuilder mensagem = new StringBuilder("=== Lista de Notas ===\n");
	        for (NotaProduto nota : notas) {
	            mensagem.append("Produto ID: ").append(nota.getProdutoId())
	                .append(", Tipo: ").append(nota.getTipo())
	                .append(", Quantidade: ").append(nota.getQuantidade())
	                .append(", Preço: R$").append(nota.getPreco())
	                .append(", Data: ").append(nota.getData()).append("\n");
	        }
	        JOptionPane.showMessageDialog(null, mensagem.toString());
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Erro ao listar notas: " + ex.getMessage());
	    }
	}
}
