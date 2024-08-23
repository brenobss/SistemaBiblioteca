package Biblioteca;

import java.util.List;
import Observador.Observador;

public class Livro {
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
    }

//    public void adicionarExemplar(Exemplar exemplar){
//        exemplares.add(exemplar);
//    }
//
//    public void removerExemplar(Exemplar exemplar){
//        exemplares.remove(exemplar);
//    }

    public void notificarObservadores(){
        //notifica observadores caso um livro tenha mais de duas reservas
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
        if (exemplares > 0) {
            exemplares--;
        } else {
            System.out.println("Nenhum exemplar disponível.");
        }
    }
}
