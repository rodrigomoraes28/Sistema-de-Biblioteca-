package biblioteca.models.exemplares;

public class Livro extends Exemplar {

	protected String autor;

	protected String editora;

	public Livro(String codExemplar, String nome, short anoPublicacao, String autor, String editora) {
		super(codExemplar, nome, anoPublicacao);
		this.autor = autor;
		this.editora = editora;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return this.editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String toString() {
		return "LIVRO     - " + super.toString() + " | Autor: " + this.autor + " | Editora: " + this.editora;
	}

}
