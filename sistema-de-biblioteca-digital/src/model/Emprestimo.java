package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Usando interface de serialização para armazenamento de dados em bytes
public class Emprestimo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //Atributos
    private final Livro livro;
    private final Usuario usuario;
    private final LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    //Construtor
    public Emprestimo(Livro livro, Usuario usuario, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    //Getters & Setters
    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    //Esse metodo facilita a verificação de devolução de um livro
    public boolean foiDevolvido(){
        return dataDevolucao != null;
    }

    //Formatando saída das informações sobre o livro que foi emprestado e para quem ele foi emprestado
    @Override
    public String toString() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String devolucao = (getDataDevolucao() == null)
                ? "Livro ainda não devolvido"
                : getDataDevolucao().format(formato);

        return String.format("""
                        ===============================
                        Livro      : %s
                        Usuário    : %s
                        Empréstimo : %s
                        Devolução  : %s
                        ===============================
                        """,
                livro.getTitulo(), usuario.getNome(), getDataEmprestimo().format(formato), devolucao);
    }
}
