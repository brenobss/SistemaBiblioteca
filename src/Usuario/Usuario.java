package Usuario;

import Biblioteca.Emprestimo;
import Biblioteca.Livro;
import Biblioteca.Reserva;

import java.util.List;

public abstract class Usuario {
    private int id;
    private String nome;
    private List<Emprestimo> emprestimos;
    private List<Reserva> reservas;

    public void fazerEmprestimoLivro(Livro livro){

    }
    public void devolverLivro(Livro livro){}
    public void reservarLivro(Livro livro){}
    public void notificar(String mensagem){}
}
