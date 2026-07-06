package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

//Usando interface de serialização para armazenamento de dados em bytes
public class Livro implements Serializable, Comparable<Livro> {
    @Serial
    private static final long serialVersionUID = 1L;

    //Atributos
    private final String titulo;
    private final String autor;
    private final int anoPublicacao;
    private boolean disponivel = true;

    //Construtor
    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    //Getters & Setters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    //Esse metodo sobrescreve (@Override) o metodo compareTo da interface Comparable<Usuario>. Ele define como dois objetos Usuario serão comparados, normalmente para ordenação.
    public int compareTo(Livro outro) {
        return Integer.compare(this.anoPublicacao, outro.anoPublicacao);
    }

    //Metodos equals() & hashCode() implementados para impedir automaticamente objetos considerados iguais, desde seja feita uma verificação com contains() em uma List.
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Livro livro))
            return false;

        return titulo.equalsIgnoreCase(livro.titulo) && autor.equalsIgnoreCase(livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor);
    }

    //Formatando saída das informações sobre o livro
    @Override
    public String toString() {
        String status = disponivel ? "Disponível" : "Emprestado";

        return String.format("""
                ----------------------------
                Título : %s
                Autor  : %s
                Ano    : %d
                Status : %s
                ----------------------------
                """,
                getTitulo(), getAutor(), getAnoPublicacao(), status);
    }
}
