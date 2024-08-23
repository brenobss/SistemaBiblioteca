package Biblioteca;

import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import RegraEmprestimo.*;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private List<RegraEmprestimo> regras;

    public Biblioteca() {
        this.regras = new ArrayList<>();
        this.regras.add(new RegraExemplaresDisponiveis());
        this.regras.add(new RegraLimiteEmprestimos());
        this.regras.add(new RegraLivroAtrasado());
        this.regras.add(new RegraLivroJaEmprestado());
    }

    public void adicionarLivro(Livro livro){
        livros.add(livro);
    }

    public void removerLivro(Livro livro){
        livros.remove(livro);
    }
    public void registrarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    public void emprestarLivro(Livro livro, Usuario usuario) {

        for (RegraEmprestimo regra : regras) {
            if (!regra.validar(livro, usuario)) {
                System.out.println(regra.mensagemErro());
                return;
            }
        }

        int diasEmprestimo = usuario.prazoEmprestimo();
        Emprestimo emprestimo = new Emprestimo(livro, usuario, diasEmprestimo);
        emprestimos.add(emprestimo);
        livro.emprestar();
        usuario.fazerEmprestimo(emprestimo);
        System.out.println("Livro emprestado com sucesso.");

    }
    public void devolverLivro(Livro livro, Usuario usuario) {
        Emprestimo emprestimo = buscarEmprestimo(livro, usuario);
        emprestimos.remove(emprestimo);
        usuario.devolverLivro(emprestimo);
        livro.devolver();
        System.out.println("Livro devolvido com sucesso.");
    }
//    public void reservarLivro(Livro livro, Usuario usuario) {
//        usuario.reservarLivro(livro);
//    }
//    public void notificarObservadores(Livro livro){
//        //notifica observadores caso um livro tenha mais de duas reservas
//    }

    public Emprestimo buscarEmprestimo(Livro livro, Usuario usuario){
        for(Emprestimo e: emprestimos){
            if(e.getLivro().equals(livro) && e.getUsuario().equals(usuario)){
                return e;
            }
        }
        return null;
    }
}
