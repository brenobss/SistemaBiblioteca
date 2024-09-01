package Usuario;

import Biblioteca.*;
import Observador.Observador;
import Reserva.Reserva;

import java.util.List;
import java.util.Map;

public class Professor implements Usuario, Observador {
    private int id;
    private String nome;
    private Map<Integer, Integer> notificacoesPorLivro;

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
        List<Exemplar> exemplaresLivro = livro.getExemplares();
        for(Emprestimo emprestimo : emprestimos){
            for(Exemplar exemplar : exemplaresLivro){
                if(emprestimo.getExemplar().getCodigoExemplar() == exemplar.getCodigoExemplar()){
                    return true;
                }
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
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    @Override
    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {

    }

    @Override
    public void atualizar(Livro livro) {
        System.out.println("Professor " + nome + " notificado: O livro '" + livro.getTitulo() + "' está disponível.");
        int codigoLivro = livro.getCodigo();
        notificacoesPorLivro.put(codigoLivro, notificacoesPorLivro.getOrDefault(codigoLivro, 0) + 1);
    }

    @Override
    public String getNotificacoes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Notificações para o professor ").append(nome).append(":\n");

        for (Map.Entry<Integer, Integer> entry : notificacoesPorLivro.entrySet()) {
            sb.append("Código do Livro: ").append(entry.getKey())
                    .append(" - Notificações: ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
