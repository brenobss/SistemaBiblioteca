package Biblioteca;

public class Exemplar {
    private String titulo;
    private int codigoExemplar;
    private boolean disponivel;
    private Emprestimo emprestimo;

    public Exemplar(String titulo, boolean disponivel) {
        this.titulo = titulo;
        this.disponivel = disponivel;
    }

    public int getCodigoExemplar() {
        return codigoExemplar;
    }

    public void setCodigoExemplar(int codigoExemplar) {
        this.codigoExemplar = codigoExemplar;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
