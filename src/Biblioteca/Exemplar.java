package Biblioteca;

public class Exemplar {
    private int codigoExemplar;
    private boolean disponivel;
    private Emprestimo emprestimo;

    public Exemplar(boolean disponivel) {
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
}
