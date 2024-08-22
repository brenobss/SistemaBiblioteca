package Biblioteca;

import Usuario.Usuario;

import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;

    public void adicionarLivro(Livro livro){
        livros.add(livro);
    }

    public void removerLivro(Livro livro){
        livros.remove(livro);
    }
    public void registrarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    public void emprestarLivro(Usuario usuario, Livro livro){

    }
    public void devolverLivro(Usuario usuario, Livro livro){

    }
    public void reservarLivro(Usuario usuario, Livro livro){

    }
    public void notificarObservadores(Livro livro){
        //notifica observadores caso um livro tenha mais de duas reservas
    }
}
