package biblioteca.models.leitores;

public class Estudante extends Leitor {

	protected String instituicao;

	protected String matricula;

	public Estudante(String nome, String cpf, short idade, String instituicao, String matricula) {
		super(nome, cpf, idade);
		this.instituicao = instituicao;
		this.matricula = matricula;
	}

	public String getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String toString() {
		return "ESTUDANTE - " + super.toString() + " | Instituicao: " + this.instituicao + " | Matricula: " + this.matricula; 
	}

}
