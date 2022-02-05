package biblioteca.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import biblioteca.models.exemplares.Exemplar;
import biblioteca.utils.comparators.NomeComparatorExemplar;
import biblioteca.utils.serializations.Serializador;

public class ExemplarController extends Controller{
    private List<Exemplar> listaExemplares;
    static final String EXEMPLARES_DB = "src/database/exemplares.db";

    public ExemplarController(){
        try{
        this.listaExemplares = !temBancoDeDados(EXEMPLARES_DB) ?
            new ArrayList<Exemplar>() :
            Serializador.carregarLista(EXEMPLARES_DB);
        } catch(IOException | ClassNotFoundException exception){
			System.out.println("Erro: " + exception.getMessage());
        }
    }

	public void salvarDados(){
		try {
			Serializador.salvarLista(EXEMPLARES_DB, this.listaExemplares);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
	 * Função responsável por adicionar um exemplar
	 * @param exemplar
	 */
	public void addExemplar(Exemplar exemplar) {
		if (possuiCodExemplarDuplicado(exemplar.getCodExemplar()))
			return;
		if (possuiNomeDuplicado(exemplar.getNome()))
			return;

		this.listaExemplares.add(exemplar);
		System.out.println("Exemplar adicionado com sucesso.");
	}

	/**
	 * Função responsável por listar exemplares
	 * @return
	 */
	public String listarExemplares() {

		if (this.listaExemplares.size() == 0)
			return "Nao ha exemplares cadastrados.";

		Collections.sort(this.listaExemplares, new NomeComparatorExemplar());

		String lista = "";
		for (Exemplar ex : this.listaExemplares) {
			lista += ex + "\n";
		}
		return lista;
	}

	/**
	 * Função responsável por listar exemplares pelo tipo especificado
	 * @param tipo
	 * @return
	 */
	public String listarExemplares(Class<?> tipo) {
		Collections.sort(this.listaExemplares, new NomeComparatorExemplar());

		int qtdPorTipo = 0;
		String lista = "";
		for (Exemplar ex : this.listaExemplares) {
			if (tipo.equals(ex.getClass())) {

				lista += ex + "\n";
				qtdPorTipo++;
			}

		}

		if (qtdPorTipo == 0)
			return "Nao ha exemplares desse tipo cadastrados";

		return lista;
	}

	/**
	 * Função responsável por buscar um exemplar
	 * @param codExemplar
	 * @return
	 */
	public Exemplar buscarExemplar(String codExemplar) {
		for (Exemplar ex : this.listaExemplares) {
			if (ex.getCodExemplar().equals(codExemplar)) {
				return ex;
			}
		}

		return null;
	}

	/**
	 * Função responsável por excluir um exemplar
	 * @param codExemplar
	 * @return
	 */
	public String excluirExemplar(String codExemplar) {
		for (Exemplar ex : this.listaExemplares) {
			if (ex.getCodExemplar().equals(codExemplar)) {
				this.listaExemplares.remove(this.listaExemplares.indexOf(ex));
				return "Exemplar excluido com sucesso";
			}
		}

		return "Exemplar nao encontrado";
	}

    private boolean possuiCodExemplarDuplicado(String codExemplar) {
		for (Exemplar e : listaExemplares) {
			if (e.getCodExemplar().equals(codExemplar)) {
				System.out.println("Já existe um exemplar com este código");
				return true;
			}
		}
		return false;
	}

	/**
	 * Função responsável por verificar se ja existe um exemplar com o mesmo nome
	 * @param nome
	 * @return
	 */
	private boolean possuiNomeDuplicado(String nome) {
		for (Exemplar e : this.listaExemplares) {
			if (e.getNome().equals(nome)) {
				System.out.println("Exemplar ja cadastrado no sistema.");
				return true;
			}
		}

		return false;
	}
}
