package Biblioteca;

import Usuario.Usuario;
import Reserva.Reserva;

import java.util.ArrayList;
import java.util.List;
import RegraEmprestimo.*;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private List<RegraEmprestimo> regras;
    private List<Reserva> reservas;

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

        if (jaTemReserva(usuario, livro)) {
            Reserva reserva = buscarReserva(livro);
            if (reserva != null && !reserva.getUsuario().equals(usuario)) {
                System.out.println("Outro usuário tem prioridade de reserva sobre este livro.");
                return;
            }
        }

        int diasEmprestimo = usuario.prazoEmprestimo();
        Emprestimo emprestimo = new Emprestimo(livro, usuario, diasEmprestimo);
        emprestimos.add(emprestimo);
        livro.emprestar();
        usuario.fazerEmprestimo(emprestimo);
        removerReserva(livro, usuario);
        System.out.println("Livro emprestado com sucesso.");

    }

    public void devolverLivro(Livro livro, Usuario usuario) {
        Emprestimo emprestimo = buscarEmprestimo(livro, usuario);
        emprestimos.remove(emprestimo);
        usuario.devolverLivro(emprestimo);
        livro.devolver();
        System.out.println("Livro devolvido com sucesso.");
    }

    public Emprestimo buscarEmprestimo(Livro livro, Usuario usuario){
        for(Emprestimo e: emprestimos){
            if(e.getLivro().equals(livro) && e.getUsuario().equals(usuario)){
                return e;
            }
        }
        return null;
    }

    public void fazerReserva(Usuario usuario, Livro livro){
        if(jaTemReserva(usuario, livro)){
            System.out.println("O usuário já possui uma reserva para este livro.");
            return;
        }
        Reserva reserva = new Reserva(usuario, livro);
        this.reservas.add(reserva);
        usuario.adicionarReserva(reserva);
        System.out.println("Reserva feita com sucesso.");
    }

    public boolean jaTemReserva(Usuario usuario, Livro livro) {
        for (Reserva reserva : reservas) {
            if (reserva.getUsuario().equals(usuario) && reserva.getLivro().equals(livro)) {
                return true;
            }
        }
        return false;
    }

    public Reserva buscarReserva(Livro livro) {
        for (Reserva reserva : reservas) {
            if (reserva.getLivro().equals(livro)) {
                return reserva;
            }
        }
        return null;
    }

    public void removerReserva(Livro livro, Usuario usuario){
        Reserva reservaParaRemover = null;
        for(Reserva r: reservas){
            if(r.getLivro().equals(livro) && r.getUsuario().equals(usuario)){
                reservaParaRemover = r;
            }
        }
        if(reservaParaRemover != null){
            reservas.remove(reservaParaRemover);
            usuario.removerReserva(reservaParaRemover);
        }
    }


}
