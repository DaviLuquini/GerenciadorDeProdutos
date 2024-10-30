package GestaoDeProdutos.Entidades;

public class Bermuda extends Produto {
	public String Cor;
	public int Comprimento;
	
	public Bermuda(String tipo, int codigo, String nome, int quantidade, double preco, String cor, int comprimento) {
		super(tipo, codigo, nome, quantidade, preco);
		this.Cor = cor;
		this.Comprimento = comprimento;
	}
	
	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public int getComprimento() {
		return Comprimento;
	}

	public void setComprimento(int comprimento) {
		Comprimento = comprimento;
	}


}
