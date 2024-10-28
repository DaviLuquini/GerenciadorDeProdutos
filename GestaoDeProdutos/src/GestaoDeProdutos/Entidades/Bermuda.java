package GestaoDeProdutos.Entidades;

public class Bermuda extends Produto {
	public String Cor;
	public int Tamanho;
	
	public Bermuda(int codigo, String nome, int quantidade, double preco, String cor, int tamanho) {
		super(codigo, nome, quantidade, preco);
		this.Cor = cor;
		this.Tamanho = tamanho;
	}
	
	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public int getTamanho() {
		return Tamanho;
	}

	public void setTamanho(int tamanho) {
		Tamanho = tamanho;
	}


}
