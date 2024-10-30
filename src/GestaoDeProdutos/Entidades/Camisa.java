package GestaoDeProdutos.Entidades;

public class Camisa extends Produto {
	public String Manga;
	public String Tamanho;
	
	public Camisa(String tipo, int codigo, String nome, int quantidade, double preco, String manga, String tamanho) {
		super(tipo, codigo, nome, quantidade, preco);
		this.Manga = manga;
		this.Tamanho = tamanho;
	}
	
	public String getManga() {
		return Manga;
	}

	public void setManga(String manga) {
		Manga = manga;
	}

	public String getTamanho() {
		return Tamanho;
	}

	public void setTamanho(String tamanho) {
		Tamanho = tamanho;
	}

}
