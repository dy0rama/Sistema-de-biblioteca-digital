package util;

import exceptions.LivroIndisponivelException;
import exceptions.LivroNaoEncontradoException;
import exceptions.UsuarioNaoEncontradoException;
import services.ArquivoService;
import services.BibliotecaService;
import services.DadosBiblioteca;

import java.util.Scanner;

//Classe que será usada para entrada de dados
public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final BibliotecaService biblioteca;
    private final ArquivoService arquivo;

    public Menu(BibliotecaService biblioteca, ArquivoService arquivo) {
        this.biblioteca = biblioteca;
        this.arquivo = arquivo;
    }

    public void iniciar(){
        DadosBiblioteca dados = arquivo.carregar();
        biblioteca.carregarDados(dados);

        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro();

            try {
                switch (opcao) {

                    case 1 -> biblioteca.cadastrarLivro();
                    case 2 -> biblioteca.listarLivros();
                    case 3 -> biblioteca.buscarLivro();
                    case 4 -> biblioteca.buscarLivrosPorAutor();

                    case 5 -> biblioteca.ordenarLivrosPorTitulo();
                    case 6 -> biblioteca.ordenarLivrosPorAno();

                    case 7 -> biblioteca.listarLivrosDisponiveis();
                    case 8 -> biblioteca.listarLivrosEmprestados();

                    case 9 ->  biblioteca.cadastrarUsuario();
                    case 10 -> biblioteca.listarUsuarios();
                    case 11 -> biblioteca.buscarUsuario();
                    case 12 -> biblioteca.listarUsuariosComEmprestimos();
                    case 13 -> biblioteca.listarUsuariosSemEmprestimos();
                    case 14 -> biblioteca.listarEmails();

                    case 15 -> biblioteca.emprestarLivro();
                    case 16 -> biblioteca.devolverLivro();

                    case 17 -> biblioteca.listarHistorico();

                    case 18 -> biblioteca.agruparLivrosPorAutor();

                    case 19 -> biblioteca.mostrarResumo();

                    case 0 -> {
                        arquivo.salvarArquivo(
                                biblioteca.getLivros(),
                                biblioteca.getUsuarios(),
                                biblioteca.getHistorico());
                        System.out.println("Sistema encerrado");
                    }

                    default ->
                            System.out.println("Opção inválida");
                }
            } catch (LivroNaoEncontradoException |
                     UsuarioNaoEncontradoException |
                     LivroIndisponivelException e) {
                System.out.println(e.getMessage());
            }
        } while (opcao != 0);
    }

    //Tratamento de entradas de opções inválidas
    private int lerInteiro() {

        while (true) {
            try {
                System.out.print("Escolha uma opção: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("""
=============================
   BIBLIOTECA DIGITAL
=============================

1  - Cadastrar Livro
2  - Listar Livros
3  - Buscar Livro
4  - Buscar Livros Por Autor

5  - Ordenar Livros Por Título
6  - Ordenar Livros Por Ano

7  - Listar Livros Disponíveis
8  - Listar Livros Emprestados

9  - Cadastrar Usuário
10 - Listar Usuários
11 - Buscar Usuário
12 - Listar Usuários Com Empréstimos
13 - Listar Usuários Sem Empréstimos
14 - Listar Emails

15 - Emprestar Livro
16 - Devolver Livro

17 - Histórico de Empréstimos

18 - Agrupar Livros Por Autor

19 - Resumo Sobre Quantidade de Usuários e Livros Cadastrados

0  - Sair

=============================
""");
    }
}