package Biblioteca;

import java.util.ArrayList;
import java.util.List;
import Observador.*;

public class Livro implements Observavel {
    private final int codigo;
    private String titulo;
    private String editora;
    private List<String> autores;
    private int edicao;
    private int anoPublicacao;
    private int exemplares;
    private List<Observador> observadores;

    public Livro(int anoPublicacao, int edicao, List<String> autores, String editora, String titulo, int codigo, int exemplares) {
        this.anoPublicacao = anoPublicacao;
        this.edicao = edicao;
        this.autores = autores;
        this.editora = editora;
        this.titulo = titulo;
        this.codigo = codigo;
        this.exemplares = exemplares;
        this.observadores = new ArrayList<>();
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

    public int getExemplares(){
        return exemplares;
    }
    public void devolver() {
        this.exemplares++;
    }

    public void emprestar() {
        this.exemplares--;
    }
}
