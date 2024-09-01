package ComportamentoEmprestar;

import Usuario.Usuario;
import Biblioteca.*;

import java.util.List;

public class EmprestimoProfessor implements ComportamentoEmprestimo{
    @Override
    public void emprestarLivro(Livro livro, Usuario usuario, Biblioteca biblioteca) {
        List<Exemplar> exemplaresDisponiveis = livro.getExemplaresDisponiveis();
        Exemplar exemplarDisponivel = exemplaresDisponiveis.get(0);

        int diasEmprestimo = usuario.prazoEmprestimo();
        Emprestimo emprestimo = new Emprestimo(exemplarDisponivel, usuario, diasEmprestimo);
        biblioteca.adicionarEmprestimo(emprestimo);
        exemplarDisponivel.setDisponivel(false);
        usuario.fazerEmprestimo(emprestimo);
        biblioteca.removerReserva(livro, usuario);
        System.out.println("Livro emprestado com sucesso ao professor.");
    }
}
