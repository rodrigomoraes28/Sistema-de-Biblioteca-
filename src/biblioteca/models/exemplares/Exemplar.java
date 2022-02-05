package biblioteca.models.exemplares;

import java.io.Serializable;

public abstract class Exemplar implements Comparable<Exemplar>, Serializable{

	protected String codExemplar;

	protected String nome;

	protected short anoPublicacao;

	public Exemplar(String codExemplar, String nome, short anoPublicacao) {
		this.codExemplar = codExemplar;
		this.nome = nome;
		this.anoPublicacao = anoPublicacao;
	}

	public String getCodExemplar() {
		return this.codExemplar;
	}

	public void setCodExemplar(String codExemplar) {
		this.codExemplar = codExemplar;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public short getAnoPublicacao() {
		return this.anoPublicacao;
	}

	public void setAnoPublicacao(short anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String toString() {
		return this.nome + " | COD: " + this.codExemplar +" | Ano: " + this.anoPublicacao;
	}
	
	@Override 
	public int compareTo(Exemplar e) {
        return this.nome.compareTo(e.getNome());
    }

}
