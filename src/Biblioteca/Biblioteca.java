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
    public void notificarObservadores(int codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
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
    public void emprestarLivro(int codigoLivro, int codigoUsuario) {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        for (RegraEmprestimo regra : regras) {
            if (!regra.validar(livro, usuario)) {
                System.out.println(regra.mensagemErro());
                return;
            }
        }

       comportamentoEmprestimo.emprestarLivro(livro, usuario, this);

    }

    public void devolverLivro(int codigoLivro, int codigoUsuario) {
        Emprestimo emprestimo = buscarEmprestimo(codigoLivro, codigoUsuario);
        emprestimos.remove(emprestimo);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        usuario.devolverLivro(emprestimo);
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        livro.devolver();
        System.out.println("Livro devolvido com sucesso.");
        notificarObservadores(codigoLivro);
    }

    public Emprestimo buscarEmprestimo(int codigoLivro, int codigoUsuario){
        for(Emprestimo e: emprestimos){
            if(e.getLivro().getCodigo() == codigoLivro && e.getUsuario().getId() == codigoUsuario){
                return e;
            }
        }
        return null;
    }

    public void consultarLivroPorCodigo(int codigoLivro){
        for(Livro livro: livros){
            if(livro.getCodigo() == codigoLivro){
                System.out.println("Livro encontrado com sucesso.");
               // System.out.println(Detalhes do livro);
            }
        }

    }

    public void consultarUsuarioPorCodigo(int codigoUsuario){
        for(Usuario usuario: usuarios){
            if(usuario.getId() == codigoUsuario){
                System.out.println("Usuario encontrado com sucesso.");
                // System.out.println(Detalhes do usuario);
            }
        }
    }

    public Usuario buscarUsuarioPorCodigo(int codigoUsuario){
        for(Usuario usuario: usuarios){
            if(usuario.getId() == codigoUsuario){
                return usuario;
            }
        }
        return null;
    }

    public Livro buscarLivroPorCodigo(int codigoLivro){
        for(Livro livro: livros){
            if(livro.getCodigo() == codigoLivro){
                return livro;
            }
        }
        return null;
    }

    public void fazerReserva(int codigoUsuario, int codigoLivro){
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        if(jaTemReserva(usuario, livro)){
            System.out.println("O usuário já possui uma reserva para este livro.");
            return;
        }
        Reserva reserva = new Reserva(usuario, livro);
        this.reservas.add(reserva);
        usuario.adicionarReserva(reserva);
        System.out.println("Reserva feita com sucesso.");
        notificarObservadores(codigoLivro);
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
