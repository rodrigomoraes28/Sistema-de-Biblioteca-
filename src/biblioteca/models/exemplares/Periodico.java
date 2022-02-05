package biblioteca.models.exemplares;

public class Periodico extends Exemplar {

	protected String editora;

	public Periodico(String codExemplar, String nome, short anoPublicacao, String editora) {
		super(codExemplar, nome, anoPublicacao);
		this.editora = editora;
	}

	public String getEditora() {
		return this.editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String toString() {
		return "PERIODICO - " + super.toString() + " | Editora: " + this.editora;
	}

}
