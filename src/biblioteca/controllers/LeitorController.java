package biblioteca.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import biblioteca.models.leitores.Leitor;
import biblioteca.utils.comparators.NomeComparatorLeitor;
import biblioteca.utils.serializations.Serializador;

public class LeitorController extends Controller {
    private List<Leitor> listaLeitores;
    static final String LEITORES_DB = "src/database/leitores.db";

    public LeitorController(){
        try{
        this.listaLeitores = !temBancoDeDados(LEITORES_DB) ?
            new ArrayList<Leitor>() :
            Serializador.carregarLista(LEITORES_DB);
        } catch(IOException | ClassNotFoundException exception){
			System.out.println("Erro: " + exception.getMessage());
        }
    }

	public void salvarDados(){
		try {
			Serializador.salvarLista(LEITORES_DB, this.listaLeitores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
	 * Função responsável por adicionar um leitor
	 * @param leitor
	 */
	public void addLeitor(Leitor leitor) {
		if (this.possuiCpfDuplicado(leitor.getCpf()))
			return;

		this.listaLeitores.add(leitor);
		System.out.println("Exemplar adicionado com sucesso.");
	}

	public void editarLeitor(int pos, Leitor leitor){
		this.listaLeitores.set(pos, leitor);
		if (this.possuiCpfDuplicado(leitor.getCpf()))
			return;

		System.out.println("Exemplar editado com sucesso.");
	}

	/**
	 * Função responsável por listar os leitores
	 * @return String
	 */
	public String listarLeitores() {
		if (this.listaLeitores.size() == 0)
			return "Nao ha leitores cadastrados.";

		Collections.sort(this.listaLeitores, new NomeComparatorLeitor());

		String lista = "";
		for (Leitor leitor : this.listaLeitores) {
			lista += leitor + "\n";
		}
		return lista;
	}

	/**
	 * Função responsável por listar os leitores pelo tipo de leitor
	 * @param tipo
	 * @return
	 */
	public String listarLeitores(Class<?> tipo) {
		int qtdPorTipo = 0;

		Collections.sort(this.listaLeitores, new NomeComparatorLeitor());

		String lista = "";
		for (Leitor leitor : this.listaLeitores) {
			if (leitor.getClass().equals(tipo)) {
				lista += leitor + "\n";
				qtdPorTipo++;
			}

		}

		if (qtdPorTipo == 0)
			return "Nao ha leitores desse tipo cadastrados";
		return lista;
	}

	/**
	 * Função responsável por buscar um leitor
	 * @param cpf
	 * @return
	 */
	public Leitor buscarLeitor(String cpf) {
		for (Leitor leitor : this.listaLeitores) {
			if (leitor.getCpf().equals(cpf)) {
				return leitor;
			}
		}

		return null;
	}

	/**
	 * Função responsável por excluir um leitor
	 * @param cpf
	 * @return
	 */
	public String excluirLeitor(String cpf) {
		for (Leitor leitor : this.listaLeitores) {
			if (leitor.getCpf().equals(cpf)) {
				this.listaLeitores.remove(this.listaLeitores.indexOf(leitor));
				return "Leitor excluido com sucesso";
			}
		}

		return "Leitor nao encontrado";
	}

    /**
	 * Função responsável por verificar se já existe um leitor com o mesmo cpf
	 * @param cpf
	 * @return
	 */
	private boolean possuiCpfDuplicado(String cpf) {
		for (Leitor l : this.listaLeitores) {
			if (l.getCpf().equals(cpf)) {
				System.out.println("Já possui um leitor com este CPF.");
				return true;
			}
		}

		return false;
	}

}
