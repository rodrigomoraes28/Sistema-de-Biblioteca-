package biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import biblioteca.models.emprestimos.Emprestimo;
import biblioteca.models.exemplares.Artigo;
import biblioteca.models.exemplares.Exemplar;
import biblioteca.models.exemplares.Livro;
import biblioteca.models.exemplares.Periodico;
import biblioteca.models.leitores.Estudante;
import biblioteca.models.leitores.Leitor;
import biblioteca.models.leitores.LeitorComum;

abstract class Menu {

    public static void menuItems() {
        String menu = "\n----------- GESTAO DE BIBLIOTECA -----------\n";
        menu += " 0 - Sair\n";
        menu += " 1 - Adicionar Exemplar\n";
        menu += " 2 - Listar Exemplares\n";
        menu += " 3 - Buscar Exemplar\n";
        menu += " 4 - Excluir Exemplar\n";
        menu += " 5 - Adicionar Leitor\n";
        menu += " 6 - Listar Leitores\n";
        menu += " 7 - Buscar Leitor\n";
        menu += " 8 - Excluir Leitor\n";
        menu += " 9 - Realizar Emprestimo\n";
        menu += "10 - Listar Emprestimos\n";
        menu += "11 - Realizar Devolucao\n";
        menu += "12 - Buscar Emprestimo\n";
        menu += "13 - Excluir Empréstimo\n";
        menu += "--------------------------------------------\n";
        menu += "Escolha uma opcao: ";
        System.out.print(menu);
    }

    public static void addExemplar(Biblioteca biblioteca, Scanner sc) {
        try {
            String menu = "Tipo de exemplar\n";
            menu += "1 - Livro\n";
            menu += "2 - Artigo\n";
            menu += "3 - Periodico\n";
            menu += "Opcao: ";
            System.out.print(menu);

            int tipoExemplar = Integer.parseInt(sc.nextLine().split(" ")[0]);

            if (tipoExemplar < 1 || tipoExemplar > 3) {
                System.out.println("Escolha uma opção válida");
                return;
            }

            System.out.print("Código do Exemplar: ");
            String codExemplar = sc.nextLine().trim();

            System.out.print("Nome: ");
            String nome = sc.nextLine().trim();

            System.out.print("Ano de publicação: ");
            short anoPublicacao = Short.parseShort(sc.nextLine().trim());

            Exemplar exemplar;
            
            if (tipoExemplar == 1) {
                System.out.print("Autor: ");
                String autor = sc.nextLine().trim();
                System.out.print("Editora: ");
                String editora = sc.nextLine().trim();

                exemplar = new Livro(codExemplar, nome, anoPublicacao, autor, editora);
            } else if (tipoExemplar == 2) {
                System.out.print("Autor: ");
                String autor = sc.nextLine().trim();

                exemplar = new Artigo(codExemplar, nome, anoPublicacao, autor);
            } else {
                System.out.print("Editora: ");
                String editora = sc.nextLine().trim();

                exemplar = new Periodico(codExemplar, nome, anoPublicacao, editora);
            }

            biblioteca.exemplarController.addExemplar(exemplar);

        } catch (NumberFormatException e) {
            System.out.println("Por favor, informe um ano de publicação válido.");
        }
    }

    public static void listarExemplares(Biblioteca biblioteca, Scanner sc) {
        String menu = "1 - Listar Todos\n";
        menu += "2 - Listar Livros\n";
        menu += "3 - Listar Artigos\n";
        menu += "4 - Listar Periodicos\n";
        menu += "Opção: ";
        System.out.print(menu);

        int opcao = Integer.parseInt(sc.nextLine().split(" ")[0]);

        switch (opcao) {
            case 1:
                System.out.println(biblioteca.exemplarController.listarExemplares());
                break;
            case 2:
                System.out.println(biblioteca.exemplarController.listarExemplares(Livro.class));
                break;
            case 3:
                System.out.println(biblioteca.exemplarController.listarExemplares(Artigo.class));
                break;
            case 4:
                System.out.println(biblioteca.exemplarController.listarExemplares(Periodico.class));
                break;
            default:
                System.out.println("Por favor, selecione uma das opções");
                return;
        }
    }

    public static void buscarExemplar(Biblioteca biblioteca, Scanner sc) {
        try {
            System.out.print("Código do exemplar: ");
            String codExemplar = sc.nextLine().trim();

            Exemplar exemplar = biblioteca.exemplarController.buscarExemplar(codExemplar); 
            if(exemplar != null) System.out.println(exemplar);
            else System.out.println("Exemplar não encontrado");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excluirExemplar(Biblioteca biblioteca, Scanner sc) {
        try {
            System.out.print("Código do exemplar: ");
            String codExemplar = sc.nextLine().trim();

            System.out.println(biblioteca.exemplarController.excluirExemplar(codExemplar));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exemplar não encontrado");
        }        
    }

    public static void addLeitor(Biblioteca biblioteca, Scanner sc) {
        System.out.print("É estudante? (s/n): ");
        String opcao = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = sc.nextLine().trim();
        System.out.print("Idade: ");
        short idade = Short.parseShort(sc.nextLine().trim());

        Leitor leitor;

        if (opcao.equals("s")) {
            System.out.print("Instituição: ");
            String instituicao = sc.nextLine().trim();
            System.out.print("Matrícula: ");
            String matricula = sc.nextLine().trim();

            leitor = new Estudante(nome, cpf, idade, instituicao, matricula);
        } else {
            leitor = new LeitorComum(nome, cpf, idade);
        }

        biblioteca.leitorController.addLeitor(leitor);
    }

    public static void editarLeitor(Biblioteca biblioteca, Scanner sc){
        System.out.print("CPF do leitor: ");
    }

    public static void listarLeitores(Biblioteca biblioteca, Scanner sc) {
        String menu = "1 - Listar Todos\n";
        menu += "2 - Listar Estudantes\n";
        menu += "3 - Listar Leitores Comuns\n";
        menu += "Opção: ";
        System.out.print(menu);

        int opcao = Integer.parseInt(sc.nextLine().split(" ")[0]);

        switch (opcao) {
            case 1:
                System.out.println(biblioteca.leitorController.listarLeitores());
                break;
            case 2:
                System.out.println(biblioteca.leitorController.listarLeitores(Estudante.class));
                break;
            case 3:
                System.out.println(biblioteca.leitorController.listarLeitores(LeitorComum.class));
                break;
            default:
                System.out.println("Por favor, selecione uma das opções");
                return;
        }
    }

    public static void buscarLeitor(Biblioteca biblioteca, Scanner sc) {
        try {
            System.out.print("CPF do leitor: ");
            String cpf = sc.nextLine().trim();

            Leitor leitor = biblioteca.leitorController.buscarLeitor(cpf);
            if(leitor != null) System.out.println(leitor);
            else System.out.println("Leitor não encontrado");
            
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Leitor não encontrado");
        }
    }

    public static void excluirLeitor(Biblioteca biblioteca, Scanner sc) {
        try {
            System.out.print("CPF do leitor: ");
            String cpf = sc.nextLine().trim();

            System.out.println(biblioteca.leitorController.excluirLeitor(cpf));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exemplar não encontrado");
        }     
    }

    public static void realizarEmprestimo(Biblioteca biblioteca, Scanner sc) {
        try {
            System.out.println("Insira os seguintes dados: cpf do leitor, codigo do exemplar, data do emprestimo");
            System.out.print("CPF do leitor: ");
            String cpf = sc.nextLine().trim();

            Leitor leitor = biblioteca.leitorController.buscarLeitor(cpf);

            if (leitor == null) {
                System.out.println("Leitor nao encontrado");
                return;
            }

            System.out.print("Código do exemplar: ");
            String codExemplar = sc.nextLine().trim();

            Exemplar exemplar = biblioteca.exemplarController.buscarExemplar(codExemplar);
            if (exemplar == null) {
                System.out.println("Exemplar nao encontrado");
                return;
            }

            System.out.print("Data de empréstimo: ");
            String data = sc.nextLine().trim();

            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Date dataEmprestimo = formatoData.parse(data);

            Emprestimo emprestimo = new Emprestimo(leitor, exemplar, dataEmprestimo);

            biblioteca.emprestimoController.realizarEmprestimo(emprestimo);
        } catch (ParseException e) {
            System.out.println("Tipo de data inválido.");
        }
    }

    public static void listarEmprestimos(Biblioteca biblioteca, Scanner sc) {
        System.out.println(biblioteca.emprestimoController.listarEmprestimos());
    }

    public static void realizarDevolucao(Biblioteca biblioteca, Scanner sc) {
        try {
            System.out.print("Código do empréstimo: ");
            int codEmprestimo = Integer.parseInt(sc.nextLine().trim());
            
            if(biblioteca.emprestimoController.buscarEmprestimo(codEmprestimo) == null){
                System.out.println("Empréstimo não encontrado");
                return;
            };

            System.out.print("Data da devolução: ");

            String dataDevolucaoString = sc.nextLine().trim();

            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Date dataDevolucao = formatoData.parse(dataDevolucaoString);

            biblioteca.emprestimoController.realizarDevolucao(codEmprestimo, dataDevolucao);
        } catch (ParseException e) {
            System.out.println("Tipo de data inválido.");
        }
    }

    public static void buscarEmprestimo(Biblioteca biblioteca, Scanner sc) {
        try {
            System.out.print("Código do Empréstimo: ");
            int codEmprestimo = Integer.parseInt(sc.nextLine().trim());
                
            Emprestimo emprestimo = biblioteca.emprestimoController.buscarEmprestimo(codEmprestimo);
            if(emprestimo != null) System.out.println(emprestimo);
            else System.out.println("Empréstimo não encontrado");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nao encontrado");
        }
    }

    public static void excluirEmprestimo(Biblioteca biblioteca, Scanner sc){
        try {
            System.out.print("Código do empréstimo: ");
            int codEmprestimo = Integer.parseInt(sc.nextLine().trim());

            System.out.println(biblioteca.emprestimoController.excluirEmprestimo(codEmprestimo));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exemplar não encontrado");
        }   
    }

    public static void salvarDados(Biblioteca biblioteca){
        System.out.println("Salvando dados...");
        biblioteca.salvarDados();
    }
}
