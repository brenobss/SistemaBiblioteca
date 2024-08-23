package Usuario;

import Biblioteca.Emprestimo;
import Biblioteca.Livro;

import java.util.ArrayList;
import java.util.List;

public interface Usuario {
    List<Emprestimo> emprestimos = new ArrayList<>();

    public boolean temLivroAtrasado();
    public boolean aindaPodePegarLivro();

    public void fazerEmprestimo(Emprestimo emprestimo);

    public void devolverLivro(Emprestimo emprestimo);

    public boolean jaTemLivroIgualEmprestado(Livro livro);

    public int prazoEmprestimo();

    //public void reservarLivro(Livro livro);

    //public void notificar(String mensagem);

//    public Emprestimo buscarEmprestimo(Livro livro);


    //public List<Livro> getReservas();
}
