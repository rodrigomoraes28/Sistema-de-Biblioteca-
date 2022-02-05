package biblioteca.controllers;

import java.io.File;

public abstract class Controller {
    protected boolean temBancoDeDados(String db) {
		File tempFile = new File(db);
		return tempFile.exists();
	}


	protected abstract void salvarDados();
}
