package Usuario;

import Biblioteca.*;
import Observador.Observador;
import Reserva.Reserva;

public class Professor implements Usuario, Observador {
    private int id;
    private String nome;
    private int notificacoes = 0;

    public Professor(String nome, int id) {
        this.nome = nome;
        this.id = id;
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
        return 7;
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
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public void atualizar(Livro livro) {
        System.out.println("Professor " + nome + " notificado: O livro '" + livro.getTitulo() + "' está disponível.");
        notificacoes ++;
    }
}
