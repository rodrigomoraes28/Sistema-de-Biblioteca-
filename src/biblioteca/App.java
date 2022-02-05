package biblioteca;

import java.util.Locale;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Locale.setDefault(new Locale("pt", "BR"));

		Biblioteca biblioteca = new Biblioteca();

		Scanner sc = new Scanner(System.in);

		boolean sair = false;

		while (!sair) {
			try {
				Menu.menuItems();
				String line = sc.nextLine();
				String[] ui = line.split(" ");
				int command = Integer.parseInt(ui[0]);

				switch (command) {
					case 0:
						Menu.salvarDados(biblioteca);
						sair = true;
						break;
					case 1:
						Menu.addExemplar(biblioteca, sc);
						break;
					case 2:
						Menu.listarExemplares(biblioteca, sc);
						break;
					case 3:
						Menu.buscarExemplar(biblioteca, sc);
						break;
					case 4:
						Menu.excluirExemplar(biblioteca, sc);
						break;
					case 5:
						Menu.addLeitor(biblioteca, sc);
						break;
					case 6:
						Menu.listarLeitores(biblioteca, sc);
						break;
					case 7:
						Menu.buscarLeitor(biblioteca, sc);
						break;
					case 8:
						Menu.excluirLeitor(biblioteca, sc);
						break;
					case 9:
						Menu.realizarEmprestimo(biblioteca, sc);
						break;
					case 10:
						Menu.listarEmprestimos(biblioteca, sc);
						break;
					case 11:
						Menu.realizarDevolucao(biblioteca, sc);
						break;
					case 12:
						Menu.buscarEmprestimo(biblioteca, sc);
						break;
					case 13:
						Menu.excluirEmprestimo(biblioteca, sc);
						break;
					default:
						System.out.println("Opcão Invalida");
				}

				
				
			} catch (NumberFormatException e) {
				System.out.println("Por favor, informe um número");
			}

			Thread.sleep(1500);
		}

		sc.close();
	}

}
