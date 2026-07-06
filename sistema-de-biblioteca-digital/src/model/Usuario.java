package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Usando interface de serialização para armazenamento de dados em bytes
public class Usuario implements Serializable, Comparable<Usuario> {
    @Serial
    private static final long serialVersionUID = 1L;

    //Atributos
    private final String nome;
    private final String email;
    private final LocalDate dataNascimento;
    private final List<Livro> livrosEmprestados;

    //Construtor
    public Usuario(String nome, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.livrosEmprestados = new ArrayList<>();
    }

    //Getters & Setters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getIdade(){
        return Period.between(getDataNascimento(), LocalDate.now()).getYears();
    }

    //Esse metodo sobrescreve (@Override) o metodo compareTo da interface Comparable<Usuario>. Ele define como dois objetos Usuario serão comparados, normalmente para ordenação.
    @Override
    public int compareTo(Usuario o) {
        return this.nome.compareToIgnoreCase(o.nome);
    }

    //Metodos equals() & hashCode() implementados para impedir automaticamente objetos considerados iguais, desde seja feita uma verificação com contains() em uma List.
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Usuario usuario = (Usuario) o;

        return email.equalsIgnoreCase(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }

    //Formatando saída das informações sobre o usuário
    @Override
    public String toString() {

        return String.format("""
                ----------------------------
                Nome : %s
                Email: %s
                Idade: %d anos
                Livros emprestados: %d
                ----------------------------
                """,
                nome, email, getIdade(), livrosEmprestados.size());
    }
}
