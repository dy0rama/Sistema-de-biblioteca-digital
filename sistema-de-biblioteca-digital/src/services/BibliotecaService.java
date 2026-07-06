package services;

import exceptions.LivroIndisponivelException;
import exceptions.LivroNaoEncontradoException;
import exceptions.UsuarioNaoEncontradoException;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//Usando interface de serialização para armazenamento de dados em bytes
public class BibliotecaService implements Serializable {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> historico;

    //Construtor
    public BibliotecaService() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        historico = new ArrayList<>();
    }

    //Getters
    public List<Livro> getLivros() {
        return livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Emprestimo> getHistorico() {
        return historico;
    }

    //Metodos de cadastramento, criação de listas variadas com diferentes tipos de informação, organização, ordenação e busca. Além de métodos de empréstimo e devolução
    public void cadastrarLivro(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o título do livro: ");
        String titulo = input.nextLine();
        System.out.println("Digite o autor  do livro: ");
        String autor = input.nextLine();
        System.out.println("Digite o ano de publicação: ");
        int anoPublicacao = input.nextInt();

        if (livros.contains(new Livro(titulo, autor, anoPublicacao))) {
            System.out.println("Livro já cadastrado");
            return;
        }

        livros.add(new Livro(titulo, autor, anoPublicacao));
        System.out.println("Livro cadastrado com sucesso");
    }

    public void cadastrarUsuario(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do usuário: ");
        String nome = input.nextLine();
        System.out.println("Digite o email do usuário: ");
        String email = input.nextLine();
        System.out.println("Digite a data de nascimento: ");
        LocalDate dataNascimento = LocalDate.parse(input.nextLine());

        if (usuarios.contains(new Usuario(nome, email, dataNascimento))) {
            System.out.println("Usuário já cadastrado");
            return;
        }
        usuarios.add(new Usuario(nome, email, dataNascimento));
        System.out.println("Usuário cadastrado com sucesso");
    }

    public void listarLivros(){
        if (livros.isEmpty())
            System.out.println("Nenhum livro cadastrado");

        livros.stream().sorted().forEach(System.out::println);
    }

    public void listarUsuarios(){
        if (usuarios.isEmpty())
            System.out.println("Nenhum usuário cadastrado");

        usuarios.stream().sorted().forEach(System.out::println);
    }

    public void buscarLivro(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o titulo do livro: ");
        String titulo = input.nextLine();

        if (livros.stream().anyMatch(livro -> livro.getTitulo().equals(titulo))) {
            livros.stream().filter(l -> l.getTitulo().equals(titulo)).toList().forEach(System.out::println);
        }else
            throw new LivroNaoEncontradoException("Livro não cadastrado");
    }

    public void buscarUsuario(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o email do usuário: ");
        String email = input.nextLine();

        if (usuarios.stream().anyMatch(u -> u.getEmail().equals(email))) {
            usuarios.stream().filter(u -> u.getEmail().equals(email)).toList().forEach(System.out::println);
        }else
            throw new UsuarioNaoEncontradoException("Usuário não cadastrado");
    }

    private Optional<Livro> buscarLivro(String titulo){
        return livros.stream().filter(u -> u.getTitulo().equals(titulo)).findFirst();
    }

    private Optional<Usuario> buscarUsuario(String email){
        return usuarios.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    public void buscarLivrosPorAutor(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o autor do livro: ");
        String autor = input.nextLine();

        livros.stream().filter(a -> a.getAutor().equalsIgnoreCase(autor)).toList().forEach(System.out::println);
    }

    public void listarLivrosDisponiveis(){
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        }

        livros.stream().filter(Livro::isDisponivel).forEach(System.out::println);
    }

    public void listarLivrosEmprestados() {
        livros.stream().filter(l -> !l.isDisponivel()).forEach(System.out::println);
    }

    public void ordenarLivrosPorAno(){
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        }

        livros.stream().sorted().forEach(System.out::println);
    }

    public void ordenarLivrosPorTitulo(){
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        }

        livros.stream().sorted(Comparator.comparing(Livro::getTitulo)).forEach(System.out::println);
    }

    public void agruparLivrosPorAutor(){
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        }

        Map<String, List<Livro>> mapa = livros.stream().collect(Collectors.groupingBy(Livro::getAutor));
        mapa.forEach((autor, lista) -> {
            System.out.println("Autor: " + autor);

            lista.forEach(livro -> System.out.println(" - " + livro.getTitulo()));
        });
    }

    public void listarHistorico(){
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        }

        historico.forEach(System.out::println);
    }

    public void listarUsuariosComEmprestimos(){
        usuarios.stream().filter(u -> !u.getLivrosEmprestados().isEmpty()).forEach(System.out::println);
    }

    public void listarUsuariosSemEmprestimos(){
        usuarios.stream().filter(u -> u.getLivrosEmprestados().isEmpty()).forEach(System.out::println);
    }

    public void listarEmails(){
        usuarios.stream().map(Usuario::getEmail).forEach(System.out::println);
    }

    public long quantidadeDeLivrosDisponiveis(){
        return livros.stream().filter(Livro::isDisponivel).count();
    }

    public long quantidadeDeLivrosEmprestados(){
        return livros.stream().filter(l -> !l.isDisponivel()).count();
    }

    public int quantidadeDeLivros(){
        return livros.size();
    }

    public int quantidadeDeUsuarios(){
        return usuarios.size();
    }

    public void emprestarLivro() throws LivroIndisponivelException, LivroNaoEncontradoException, UsuarioNaoEncontradoException {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o titulo do livro: ");
        String titulo = input.nextLine();

        Livro livro = buscarLivro(titulo).orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        System.out.println("Digite o email do usuário");
        String email = input.nextLine();

        Usuario usuario = buscarUsuario(email).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário nao encontrado"));

        if (!livro.isDisponivel())
            throw new LivroIndisponivelException("Livro indisponível para empréstimo");

        livro.setDisponivel(false);

        usuario.getLivrosEmprestados().add(livro);

        historico.add(new Emprestimo(livro, usuario, LocalDateTime.now(),null));

        System.out.println("Empréstimo realizado com sucesso");
    }

    public void devolverLivro() throws LivroNaoEncontradoException, UsuarioNaoEncontradoException {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o titulo do livro: ");
        String titulo = input.nextLine();

        Livro livro = buscarLivro(titulo).orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        System.out.println("Digite o email do usuário: ");
        String email = input.nextLine();

        Usuario usuario = buscarUsuario(email).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        livro.setDisponivel(true);

        usuario.getLivrosEmprestados().remove(livro);

        historico.stream().filter(e -> e.getLivro().equals(livro) && e.getUsuario().equals(usuario) && !e.foiDevolvido()).
                findFirst().ifPresent(e -> e.setDataDevolucao(LocalDateTime.now()));

        System.out.println("Livro devolvido com sucesso");
    }

    public void mostrarResumo(){
        System.out.println("\n========== RESUMO ==========");

        System.out.println("Livros cadastrados : " + quantidadeDeLivros());

        System.out.println("Usuários cadastrados : " + quantidadeDeUsuarios());

        System.out.println("Livros disponíveis : " + quantidadeDeLivrosDisponiveis());

        System.out.println("Livros emprestados : " + quantidadeDeLivrosEmprestados());

        System.out.println("============================");
    }

    //Metodos que será usado para carregamento de dados (deserialização)
    public void carregarDados(DadosBiblioteca dados){
        this.livros = dados.livros();
        this.usuarios = dados.usuarios();
        this.historico = dados.historicos();
    }
}
