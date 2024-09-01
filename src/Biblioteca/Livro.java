package Biblioteca;

import java.util.ArrayList;
import java.util.List;
import Observador.*;
import Reserva.Reserva;

public class Livro implements Observavel {
    private final int codigo;
    private String titulo;
    private String editora;
    private List<String> autores;
    private int edicao;
    private int anoPublicacao;
    private List<Exemplar> exemplares;
    private List<Observador> observadores;
    private List<Reserva> reservas;

    public Livro(int anoPublicacao, int edicao, List<String> autores, String editora, String titulo, int codigo) {
        this.anoPublicacao = anoPublicacao;
        this.edicao = edicao;
        this.autores = autores;
        this.editora = editora;
        this.titulo = titulo;
        this.codigo = codigo;
        this.exemplares = new ArrayList<>();
        this.observadores = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    @Override
    public void notificarObservadores(){
        for (Observador observador : observadores) {
            observador.atualizar(this);
        }
    }

    @Override
    public void adicionarObservador(Observador observador){
        this.observadores.add(observador);
    }

    @Override
    public void removerObservador(Observador observador){
        this.observadores.remove(observador);
    }

    public List<Observador> listarObservadores() {
        return this.observadores;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public List<Exemplar> getExemplares(){
        return exemplares;
    }
    public void devolver(int codigoExemplar) {
        for (Exemplar exemplar : exemplares) {
            if(exemplar.getCodigoExemplar() == codigoExemplar) {
                exemplar.setDisponivel(true);
            }
        }
    }

    public void emprestar(int codigoExemplar) {
        for (Exemplar exemplar : exemplares) {
            if(exemplar.getCodigoExemplar() == codigoExemplar) {
                exemplar.setDisponivel(false);
            }
        }
    }

    public void adicionarReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }

    public void removerReserva(Reserva reserva) {
        this.reservas.remove(reserva);
    }

    public List<Reserva> getReservas(){
        return reservas;
    }

    public Exemplar getExemplar(int codigoExemplar) {
        for (Exemplar exemplar : exemplares) {
            if(exemplar.getCodigoExemplar() == codigoExemplar) {
                return exemplar;
            }
        }
        return null;
    }

    public List<Exemplar> getExemplaresDisponiveis(){
        List<Exemplar> exemplaresDisponiveis = new ArrayList<>();
        for(Exemplar exemplar : exemplares) {
            if(exemplar.isDisponivel()){
                exemplaresDisponiveis.add(exemplar);
            }
        }
        return exemplaresDisponiveis;
    }
}
