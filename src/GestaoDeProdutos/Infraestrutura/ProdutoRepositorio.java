package GestaoDeProdutos.Infraestrutura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GestaoDeProdutos.Entidades.Bermuda;
import GestaoDeProdutos.Entidades.Camisa;
import GestaoDeProdutos.Entidades.IProdutoFabrica;
import GestaoDeProdutos.Entidades.Produto;

public class ProdutoRepositorio implements IProdutoRepositorio {
	private final DbConnection dbConnection;
	private IProdutoFabrica produtoFabrica;

	public ProdutoRepositorio(IProdutoFabrica produtoFabrica) {
		dbConnection = new DbConnection();
		this.produtoFabrica = produtoFabrica;
	}

	// Adiciona um produto ao estoque
	@Override
	// Método para adicionar um produto ao banco de dados
	public void adicionarCamisa(Camisa camisa) {
		String sql = "INSERT INTO camisas (tipo, codigo, nome, quantidade, preco, manga, tamanho) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {

			statement.setString(1, camisa.getTipo());
			statement.setInt(2, camisa.getCodigo());
			statement.setString(3, camisa.getNome());
			statement.setInt(4, camisa.getQuantidade());
			statement.setDouble(5, camisa.getPreco());
			statement.setString(6, camisa.getManga());
			statement.setString(7, camisa.getTamanho());
			statement.executeUpdate();

			System.out.println("Camisa adicionado com sucesso.");
		} catch (SQLException e) {
			System.err.println("Erro ao adicionar camisa: " + e.getMessage());
		}
	}

	@Override
	// Método para adicionar um produto ao banco de dados
	public void adicionarBermuda(Bermuda bermuda) {
		String sql = "INSERT INTO bermudas (tipo, codigo, nome, quantidade, preco, cor, comprimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {

			statement.setString(1, bermuda.getTipo());
			statement.setInt(2, bermuda.getCodigo());
			statement.setString(3, bermuda.getNome());
			statement.setInt(4, bermuda.getQuantidade());
			statement.setDouble(5, bermuda.getPreco());
			statement.setString(6, bermuda.getCor());
			statement.setInt(7, bermuda.getComprimento());
			statement.executeUpdate();

			System.out.println("Produto adicionado com sucesso.");
		} catch (SQLException e) {
			System.err.println("Erro ao adicionar bermuda: " + e.getMessage());
		}
	}

	// Atualiza a quantidade de um produto em estoque
	@Override
	public void atualizarQuantidade(Produto produto) {
		Produto produtoExistente;
		
		if(produto.getTipo() == "Camisa") {
			 produtoExistente = buscarCamisa(produto.getCodigo());
		} else {
			 produtoExistente = buscarBermuda(produto.getCodigo());
		}

		if (produtoExistente != null) {
			// Defina a tabela com base no tipo de produto
			String tabela = produtoExistente.getTipo().equals("Camisa") ? "camisas" : "bermudas";
			String sql = "UPDATE " + tabela + " SET quantidade = ? WHERE codigo = ?";

			try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {

				statement.setInt(1, produtoExistente.getQuantidade());
				statement.setInt(2, produtoExistente.getCodigo());
				statement.executeUpdate();

				System.out.println("Quantidade atualizada com sucesso.");
			} catch (SQLException e) {
				System.err.println("Erro ao atualizar quantidade: " + e.getMessage());
			}
		} else {
			System.err.println("Produto para atualizar quantidade não encontrado no repositorio.");
		}
	}

	// Remove um produto do estoque
	@Override
	public void removerProduto(String tipo, int codigo) {
		Produto produtoExistente;
		
		if(tipo == "Camisa") {
			 produtoExistente = buscarCamisa(codigo);
		} else {
			 produtoExistente = buscarBermuda(codigo);
		}
		
		if (produtoExistente != null) {
			String sql = "DELETE FROM " + tipo + " WHERE codigo = ?";

			try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {

				statement.setInt(1, produtoExistente.getCodigo());
				int rowsAffected = statement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Produto removido com sucesso.");
				} else {
					System.out.println("Nenhum produto encontrado para remoção.");
				}
			} catch (SQLException e) {
				System.err.println("Erro ao remover produto: " + e.getMessage());
			}
		} else {
			System.err.println("Produto não encontrado.");
		}
	}

	// Lista todos os produtos no estoque
	@Override
	public List<Camisa> listarCamisas() {
		List<Camisa> camisas = new ArrayList<>();
		String sql = "SELECT * FROM public.camisas ORDER BY id ASC;";

        try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String tipo = resultSet.getString("tipo");
				int codigo = resultSet.getInt("codigo");
				String nome = resultSet.getString("nome");
				int quantidade = resultSet.getInt("quantidade");
				double preco = resultSet.getDouble("preco");
				String manga = resultSet.getString("manga");
				String tamanho = resultSet.getString("tamanho");

				Camisa camisa = produtoFabrica.criarCamisa(tipo, codigo, nome, quantidade, preco, manga, tamanho);

				camisas.add(camisa);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao listar camisas: " + e.getMessage());
		}

		if (camisas.isEmpty()) {
			System.out.println("Nenhum camisa no estoque.");
		}
		return camisas;
	}

	@Override
	public List<Bermuda> listarBermudas() {
		List<Bermuda> bermudas = new ArrayList<>();
		String sql = "SELECT * FROM public.bermudas ORDER BY id ASC;";

        try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String tipo = resultSet.getString("tipo");
				int codigo = resultSet.getInt("codigo");
				String nome = resultSet.getString("nome");
				int quantidade = resultSet.getInt("quantidade");
				double preco = resultSet.getDouble("preco");
				String cor = resultSet.getString("cor");
				int comprimento = resultSet.getInt("comprimento");

				Bermuda bermuda = produtoFabrica.criarBermuda(tipo, codigo, nome, quantidade, preco, cor, comprimento);

				bermudas.add(bermuda);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao listar bermudas: " + e.getMessage());
		}

		if (bermudas.isEmpty()) {
			System.out.println("Nenhum bermuda no estoque.");
		}
		return bermudas;
	}

	// Busca uma camisa pelo código no estoque
	@Override
	public Camisa buscarCamisa(int codigo) {
		String sql = "SELECT id, tipo, codigo, nome, quantidade, preco::numeric, manga, tamanho "
				+ "FROM public.camisas WHERE codigo = ?;";
		try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {

			statement.setInt(1, codigo);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String tipo = resultSet.getString("tipo");
				String nome = resultSet.getString("nome");
				int quantidade = resultSet.getInt("quantidade");
				double preco = resultSet.getDouble("preco");
				String manga = resultSet.getString("manga");
				String tamanho = resultSet.getString("tamanho");

				return new Camisa(tipo, codigo, nome, quantidade, preco, manga, tamanho);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar camisa: " + e.getMessage());
		}
		return null; // Retorna null se a camisa não for encontrada
	}

	// Busca uma bermuda pelo código no estoque
	@Override
	public Bermuda buscarBermuda(int codigo) {
		String sql = "SELECT id, tipo, codigo, nome, quantidade, preco::numeric, cor, comprimento "
				+ "FROM public.bermudas WHERE codigo = ?;";
		try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {

			statement.setInt(1, codigo);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String tipo = resultSet.getString("tipo");
				String nome = resultSet.getString("nome");
				int quantidade = resultSet.getInt("quantidade");
				double preco = resultSet.getDouble("preco");
				String cor = resultSet.getString("cor");
				int comprimento = resultSet.getInt("comprimento");

				return new Bermuda(tipo, codigo, nome, quantidade, preco, cor, comprimento);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar bermuda: " + e.getMessage());
		}
		return null; // Retorna null se a bermuda não for encontrada
	}
	
	 public void fecharConexao() {
	        if (dbConnection != null) {
	            dbConnection.close(); // Fecha a conexão
	        }
	    }

}
