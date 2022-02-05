package biblioteca.utils.serializations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serializador {
    public static List carregarLista(String url) throws ClassNotFoundException, IOException {
		try (FileInputStream fileInputStream = new FileInputStream(url);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

			return (List) objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException exception) {
			throw exception;
		}
	}

	public static void salvarLista(String url, List lista) throws IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(url);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

			objectOutputStream.writeObject(lista);
		} catch (IOException io) {
			System.out.println("Erro: " + io.getMessage());
		}
	}
}
