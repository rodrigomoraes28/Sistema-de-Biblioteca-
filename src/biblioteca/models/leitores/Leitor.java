package biblioteca.models.leitores;

import java.io.Serializable;

public class Leitor implements Comparable<Leitor>, Serializable {

	protected String nome;

	protected String cpf;

	protected short idade;

	public Leitor(String nome, String cpf, short idade) {
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public short getIdade() {
		return this.idade;
	}

	public void setIdade(short idade) {
		this.idade = idade;
	}
	
	@Override 
	public int compareTo(Leitor l) {
        return this.nome.compareTo(l.getNome());
    }

	public String toString() {
		return this.nome + " | CPF: " + this.cpf + " | Idade: " + this.idade;
	}
}
