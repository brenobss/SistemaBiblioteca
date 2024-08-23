package Usuario;

import Biblioteca.Emprestimo;
import Biblioteca.Livro;
import RegraEmprestimo.RegraEmprestimo;

import java.util.List;

public class AlunoGraduacao implements Usuario {
    private int id;
    private String nome;
    private List<Livro> reservas;
    private List<Emprestimo> emprestimos;

    public AlunoGraduacao(String nome) {
        this.nome = nome;
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

}
