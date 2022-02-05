package biblioteca;

import biblioteca.controllers.EmprestimoController;
import biblioteca.controllers.ExemplarController;
import biblioteca.controllers.LeitorController;

public class Biblioteca {
	public LeitorController leitorController;
	public ExemplarController exemplarController;
	public EmprestimoController emprestimoController;

	public Biblioteca() {
		this.leitorController = new LeitorController();
		this.exemplarController = new ExemplarController();
		this.emprestimoController = new EmprestimoController();
	}


	public void salvarDados() {
		try {
			this.leitorController.salvarDados();
			this.exemplarController.salvarDados();
			this.emprestimoController.salvarDados();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
