package biblioteca.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import biblioteca.models.emprestimos.Emprestimo;
import biblioteca.models.exemplares.Exemplar;
import biblioteca.models.leitores.Leitor;
import biblioteca.utils.serializations.Serializador;

public class EmprestimoController extends Controller {
    private List<Emprestimo> listaEmprestimos;
    static final String EMPRESTIMO_DB = "src/database/emprestimo.db";

    public EmprestimoController(){
        try{
        this.listaEmprestimos = !temBancoDeDados(EMPRESTIMO_DB) ?
            new ArrayList<Emprestimo>() :
            Serializador.carregarLista(EMPRESTIMO_DB);
        } catch(IOException | ClassNotFoundException exception){
			System.out.println("Erro: " + exception.getMessage());
        }
    }

	public void salvarDados(){
		try {
			Serializador.salvarLista(EMPRESTIMO_DB, this.listaEmprestimos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
	 * Função responsável por realizar um empréstimo
	 * @param emprestimo
	 */
	public void realizarEmprestimo(Emprestimo emprestimo) {
		if (this.estaEmprestado(emprestimo.getExemplar()))
			return;
		if (this.pegouEmprestado(emprestimo.getLeitor()))
			return;

		this.listaEmprestimos.add(emprestimo);

		System.out.println("Empréstimo adicionado com sucesso.");
	}

	/**
	 * Função responsável por realizar uma devolução de exemplar
	 * @param codEmprestimo
	 * @param dataDevolucao
	 */
	public void realizarDevolucao(int codEmprestimo, Date dataDevolucao) {
		int index = codEmprestimo - 1;
		Emprestimo emprestimo = this.buscarEmprestimo(codEmprestimo);

		emprestimo.setDataDevolucao(dataDevolucao);

		this.listaEmprestimos.set(index, emprestimo);

		System.out.print("Devolução feita com sucesso!");

	}

	/**
	 * Função responsável por listar os empréstimos
	 * @return
	 */
	public String listarEmprestimos() {
		String lista = "";
		for (int i = 0; i < this.listaEmprestimos.size(); i++) {
			if(this.listaEmprestimos.get(i) != null){
				Emprestimo emprestimo = this.listaEmprestimos.get(i);
				lista += (i + 1) + ". " + emprestimo + "\n";
			}
		}
		if(lista.equals("")){
			return "Nenhum Empréstimo encontrado";
		}

		return lista;
	}

	/**
	 * Função responsável por buscar um empréstimo
	 * @param codEmprestimo
	 * @return
	 */
	public Emprestimo buscarEmprestimo(int codEmprestimo) {
		try {
			if (this.listaEmprestimos.get(codEmprestimo - 1) != null) {
				return this.listaEmprestimos.get(codEmprestimo - 1);
			}
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			return null;
		}
		return null;
	}

	/**
	 * Função responsável por excluir um empréstimo
	 * @param codEmprestimo
	 * @return
	 */
	public String excluirEmprestimo(int codEmprestimo) {
		if (buscarEmprestimo(codEmprestimo) != null) {
			this.listaEmprestimos.set(codEmprestimo - 1, null);
			return "Empréstimo excluido com sucesso";
		}

		return "Empréstimo nao encontrado";
	}

	

	/**
	 * Função responsável por verificar se o exemplar já está com um empréstimo pendente
	 * @param exemplar
	 * @return
	 */
	private boolean estaEmprestado(Exemplar exemplar) {
		for (Emprestimo emp : this.listaEmprestimos) {
			if(emp != null){
				if (emp.getExemplar().equals(exemplar)) {
					System.out.println("O exemplar não está disponível no momento.");
					return true;
				}
			}
			
		}

		return false;
	}

	/**
	 * Função responsável por verificar se o Leitor já está com um empréstimo pendente
	 * @param leitor
	 * @return
	 */
	private boolean pegouEmprestado(Leitor leitor) {
		for (Emprestimo emp : this.listaEmprestimos) {
			if(emp != null){
				if (emp.getLeitor().equals(leitor) && emp.getDataDevolucao() == null) {
					System.out.println("Leitor já está com um exemplar");
					return true;
				}
			}
		}

		return false;
	}

}
