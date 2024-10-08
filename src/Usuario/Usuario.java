package Usuario;

import Biblioteca.Emprestimo;
import Biblioteca.Livro;
import Reserva.Reserva;

import java.util.ArrayList;
import java.util.List;

public interface Usuario {
    String nome = "";
    List<Emprestimo> emprestimos = new ArrayList<>();
    List<Reserva> reservas = new ArrayList<>();

    public boolean temLivroAtrasado();
    public boolean aindaPodePegarLivro();

    public void fazerEmprestimo(Emprestimo emprestimo);

    public void devolverLivro(Emprestimo emprestimo);

    public boolean jaTemLivroIgualEmprestado(Livro livro);

    public int prazoEmprestimo();

    public int totalReservas();

    public void adicionarReserva(Reserva reserva);

    public boolean temReserva(Livro livro);

    void removerReserva(Reserva reservaParaRemover);

    public List<Emprestimo> getEmprestimos();
    public List<Reserva> getReservas();

    public int getId();
    public void setId(int id);

    public String getNome();
    public void setNome(String nome);
}
