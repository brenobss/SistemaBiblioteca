package ComportamentoEmprestar;

import Biblioteca.*;
import Usuario.Usuario;
import Reserva.Reserva;

import java.util.List;


public class EmprestimoAluno implements ComportamentoEmprestimo{
    @Override
    public void emprestarLivro(Livro livro, Usuario usuario, Biblioteca biblioteca) {

        if (biblioteca.jaTemReserva(usuario, livro)) {
            Reserva reserva = biblioteca.buscarReserva(livro);
            if (reserva != null && !reserva.getUsuario().equals(usuario)) {
                if(livro.getReservas().size() >= livro.getExemplares().size())
                    System.out.println("Não é possível fazer o empréstimo porque todos os exemplares desse livro estão reservados.");
                    return;
            }
        }
        List<Exemplar> exemplaresDisponiveis = livro.getExemplaresDisponiveis();
        Exemplar exemplarDisponivel = exemplaresDisponiveis.get(0);

        int diasEmprestimo = usuario.prazoEmprestimo();
        Emprestimo emprestimo = new Emprestimo(exemplarDisponivel, usuario, diasEmprestimo);
        exemplarDisponivel.setDisponivel(false);
        biblioteca.adicionarEmprestimo(emprestimo);
        usuario.fazerEmprestimo(emprestimo);
        biblioteca.removerReserva(livro, usuario);
        System.out.println("Livro emprestado com sucesso.");
    }
}
