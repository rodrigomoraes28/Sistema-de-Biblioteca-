package biblioteca.models.exemplares;

public class Artigo extends Exemplar {

	protected String autor;

	public Artigo(String codExemplar, String nome, short anoPublicacao, String autor) {
		super(codExemplar, nome, anoPublicacao);
		this.autor = autor;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String toString() {
		return "ARTIGO    - " + super.toString() + " | Autor: " + this.autor;
	}

}
