package biblioteca.models.leitores;



public class LeitorComum extends Leitor{

	public LeitorComum(String nome, String cpf, short idade) {
		super(nome, cpf, idade);
	}
	
	@Override
	public String toString() {
		return "COMUM     - " + super.toString(); 
	}

}
