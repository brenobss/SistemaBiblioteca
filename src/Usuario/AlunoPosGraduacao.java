package Usuario;

import Biblioteca.*;
import Reserva.Reserva;

import java.util.List;

public class AlunoPosGraduacao implements Usuario {
    private int id;
    private String nome;

    public AlunoPosGraduacao(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean temLivroAtrasado() {
        for(Emprestimo e : emprestimos){
            if(e.estaAtrasado()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean aindaPodePegarLivro() {
        if(emprestimos.size() > 4){
            return false;
        }
        return true;
    }

    @Override
    public void fazerEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    @Override
    public void devolverLivro(Emprestimo emprestimo) {
        if(emprestimo.estaAtrasado()){
            System.out.println("Da próxima vez devolva o livro dentro do prazo.");
        }
        emprestimos.remove(emprestimo);
    }

    @Override
    public boolean jaTemLivroIgualEmprestado(Livro livro) {
        for(Emprestimo e:emprestimos){
            if(e.getLivro().equals(livro)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int prazoEmprestimo() {
        return 5;
    }

    @Override
    public int totalReservas() {
        return reservas.size();
    }

    @Override
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }
}
