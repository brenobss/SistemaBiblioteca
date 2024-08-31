package Usuario;

import Biblioteca.Emprestimo;
import Biblioteca.Livro;
import Reserva.Reserva;

public class AlunoGraduacao implements Usuario {
    private int id;
    private String nome;

    public AlunoGraduacao(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public boolean temLivroAtrasado() {
        for(Emprestimo e : emprestimos){
            if(e.estaAtrasado()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean aindaPodePegarLivro() {
        if(emprestimos.size() >= 3){
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
        return 3;
    }

    @Override
    public int totalReservas() {
        return reservas.size();
    }

    @Override
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public boolean temReserva(Livro livro) {
        for(Reserva r: reservas){
            if(r.getLivro().equals(livro)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removerReserva(Reserva reservaParaRemover) {
        reservas.remove(reservaParaRemover);
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
