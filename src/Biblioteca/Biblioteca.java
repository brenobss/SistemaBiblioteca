package Biblioteca;

import ComportamentoEmprestar.ComportamentoEmprestimo;
import Observador.Observador;
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
    private ComportamentoEmprestimo comportamentoEmprestimo;

    public Biblioteca() {
        this.regras = new ArrayList<>();
        this.regras.add(new RegraExemplaresDisponiveis());
        this.regras.add(new RegraLimiteEmprestimos());
        this.regras.add(new RegraLivroAtrasado());
        this.regras.add(new RegraLivroJaEmprestado());
    }

    public void adicionarObservador(int codigoUsuario, int codigoLivro) {
        Observador observador = (Observador) buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        livro.adicionarObservador(observador);

    }

    public void removerObservador(int codigoUsuario, int codigoLivro) {
        Observador observador = (Observador) buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        livro.removerObservador(observador);
    }


    public void notificarObservadores(Livro livro) {
        int quantidadeReservas = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getLivro().equals(livro)) {
                quantidadeReservas++;
            }
        }

        if (quantidadeReservas > 2) {
            livro.notificarObservadores();
        }
    }
    public void consultarNotificacoesObservadores(int codigoObservador) {
        Observador observador = (Observador) buscarUsuarioPorCodigo(codigoObservador);
        observador.getNotificacoes();
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
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        for(Exemplar exemplar : livro.getExemplares()){
            if(exemplar.getEmprestimo().getUsuario().getId() == codigoUsuario){
                exemplar.setDisponivel(true);
                exemplar.getEmprestimo().getUsuario().devolverLivro(exemplar.getEmprestimo());
                exemplar.getEmprestimo().setStatus("Concluído");
                emprestimos.remove(exemplar.getEmprestimo());
                System.out.println("Livro devolvido com sucesso.");
                notificarObservadores(livro);
            } else{
                System.out.println("Você não tem esse livro emprestado.");
            }
        }

    }

    public Emprestimo buscarEmprestimo(int codigoExemplar, int codigoUsuario){
        for(Emprestimo e: emprestimos){
            if(e.getExemplar().getCodigoExemplar() == codigoExemplar && e.getUsuario().getId() == codigoUsuario){
                return e;
            }
        }
        return null;
    }

    public void consultarLivroPorCodigo(int codigoLivro){
        for(Livro livro: livros){
            if(livro.getCodigo() == codigoLivro){
                List<Reserva> reservasLivro = livro.getReservas();
                System.out.println("Livro " + livro.getTitulo() + "encontrado com sucesso. Reservas desse livro: " + reservasLivro.size() );
                for(Reserva reserva: reservasLivro){
                    System.out.println(reserva.getUsuario().getNome());
                }
                System.out.println("Exemplares: ");
                for(Exemplar exemplar: livro.getExemplares()){
                    System.out.println("Código do Exemplar:" + exemplar.getCodigoExemplar() + ", Disponibilidade: " + exemplar.isDisponivel());
                    if (!exemplar.isDisponivel()) {
                        System.out.println("Este exemplar está emprestado para " + exemplar.getEmprestimo().getUsuario().getNome());
                        System.out.println("Data do Emprestimo: " + exemplar.getEmprestimo().getDataEmprestimo() + ", Data da devolução: " + exemplar.getEmprestimo().getDataDevolucao());
                    }
                }
            }
        }

    }

    public void consultarUsuarioPorCodigo(int codigoUsuario){
        for(Usuario usuario: usuarios){
            if(usuario.getId() == codigoUsuario){
                System.out.println("Usuario encontrado com sucesso.");
                List<Emprestimo> emprestimosUsuario = usuario.getEmprestimos();
                listarEmprestimos(emprestimosUsuario);
                List<Reserva> reservasUsuario = usuario.getReservas();
                listarReservas(reservasUsuario);
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
        List<Reserva> reservas = livro.getReservas();
        List<Exemplar> exemplaresDisponiveis = livro.getExemplaresDisponiveis();
        if(reservas.size() >= exemplaresDisponiveis.size()){
            System.out.println("Esse livro não tem mais exemplares disponíveis");
            return;
        }

        if(usuario.totalReservas() > 3){
            System.out.println("Esse usuário atingiu o limite máximo de reservas");
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
    public void listarEmprestimos(List<Emprestimo> emprestimosUsuario){
        for(Emprestimo emprestimo: emprestimosUsuario){
            System.out.println("Título do livro:" + emprestimo.getExemplar().getTitulo());
            System.out.println("Data  do empréstimo" + emprestimo.getDataEmprestimo());
            System.out.println("Status: " + emprestimo.getStatus());
        }
    }

    public void listarReservas(List<Reserva> reservasUsuario){
        for(Reserva reserva: reservas){
            System.out.println("Título do livro:" + reserva.getLivro().getTitulo() + ", data da reserva: " + reserva.getDataReserva());
        }
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }
}
