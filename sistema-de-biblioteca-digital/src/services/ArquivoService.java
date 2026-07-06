package services;

import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Classe de serialização e deserialização
public class ArquivoService {
    private static final String ARQUIVO = "biblioteca.dat";

    public void salvarArquivo(List<Livro> livros, List<Usuario> usuarios, List<Emprestimo> historico) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(livros);
            oos.writeObject(usuarios);
            oos.writeObject(historico);
            System.out.println("Arquivo salvo com sucesso");
        }catch (IOException e) {
            System.out.println("Erro ao salvar arquivo");
            e.fillInStackTrace();
        }finally {
            System.out.println("Operação finalizada com sucesso");
        }
    }

    @SuppressWarnings("unchecked")
    public DadosBiblioteca carregar(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            List<Livro> livros = (List<Livro>) ois.readObject();
            List<Usuario> usuarios = (List<Usuario>) ois.readObject();
            List<Emprestimo> historico = (List<Emprestimo>) ois.readObject();

            System.out.println("Dados carregados com sucesso");

            return new DadosBiblioteca(livros,usuarios,historico);
        }catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            System.out.println("Uma nova biblioteca será criada");
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Erro ao carregar arquivo");
            e.fillInStackTrace();
        }finally {
            System.out.println("Operação finalizada com sucesso");
        }

        return new DadosBiblioteca(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }
}
