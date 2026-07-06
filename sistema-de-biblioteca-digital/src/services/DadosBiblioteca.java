package services;

import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.util.List;
import java.util.Objects;

//Classe auxiliar. Como métodos só podem retornar um objeto, criaremos uma classe que armazenará as três listas.
public final class DadosBiblioteca {
    private final List<Livro> livros;
    private final List<Usuario> usuarios;
    private final List<Emprestimo> historicos;

    //Construtor
    public DadosBiblioteca(List<Livro> livros, List<Usuario> usuarios, List<Emprestimo> historicos) {
        this.livros = livros;
        this.usuarios = usuarios;
        this.historicos = historicos;
    }

    public List<Livro> livros() {
        return livros;
    }

    public List<Usuario> usuarios() {
        return usuarios;
    }

    public List<Emprestimo> historicos() {
        return historicos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DadosBiblioteca) obj;
        return Objects.equals(this.livros, that.livros) &&
                Objects.equals(this.usuarios, that.usuarios) &&
                Objects.equals(this.historicos, that.historicos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livros, usuarios, historicos);
    }

    @Override
    public String toString() {
        return "DadosBiblioteca[" +
                "livros=" + livros + ", " +
                "usuarios=" + usuarios + ", " +
                "historicos=" + historicos + ']';
    }

}