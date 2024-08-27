package ComportamentoEmprestar;

import Biblioteca.Biblioteca;
import Biblioteca.Livro;
import Usuario.Usuario;
import Biblioteca.Emprestimo;

public class EmprestimoProfessor implements ComportamentoEmprestimo{
    @Override
    public void emprestarLivro(Livro livro, Usuario usuario, Biblioteca biblioteca) {
        int diasEmprestimo = usuario.prazoEmprestimo();
        Emprestimo emprestimo = new Emprestimo(livro, usuario, diasEmprestimo);
        biblioteca.adicionarEmprestimo(emprestimo);
        livro.emprestar();
        usuario.fazerEmprestimo(emprestimo);
        biblioteca.removerReserva(livro, usuario);
        System.out.println("Livro emprestado com sucesso ao professor.");
    }
}
