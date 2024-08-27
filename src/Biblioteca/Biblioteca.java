package Biblioteca;

import ComportamentoEmprestar.ComportamentoEmprestimo;
import Observador.Observador;
import Observador.Observavel;
import Usuario.Usuario;
import Reserva.Reserva;

import java.util.ArrayList;
import java.util.List;
import RegraEmprestimo.*;

public class Biblioteca implements Observavel {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private List<RegraEmprestimo> regras;
    private List<Reserva> reservas;
    private List<Observador> observadores;
    private ComportamentoEmprestimo comportamentoEmprestimo;

    public Biblioteca() {
        this.regras = new ArrayList<>();
        this.regras.add(new RegraExemplaresDisponiveis());
        this.regras.add(new RegraLimiteEmprestimos());
        this.regras.add(new RegraLivroAtrasado());
        this.regras.add(new RegraLivroJaEmprestado());
        this.observadores = new ArrayList<>();
    }

    @Override
    public void adicionarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(Livro livro) {
        for (Observador observador : observadores) {
            observador.atualizar(livro);
        }
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

       comportamentoEmprestimo.emprestarLivro(livro, usuario, this);

    }

    public void devolverLivro(Livro livro, Usuario usuario) {
        Emprestimo emprestimo = buscarEmprestimo(livro, usuario);
        emprestimos.remove(emprestimo);
        usuario.devolverLivro(emprestimo);
        livro.devolver();
        System.out.println("Livro devolvido com sucesso.");
        notificarObservadores(livro);
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
        notificarObservadores(livro);
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


    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }
}
