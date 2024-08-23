package RegraEmprestimo;

import Biblioteca.Livro;
import Usuario.Usuario;

public class RegraLimiteEmprestimos implements RegraEmprestimo{
    @Override
    public boolean validar(Livro livro, Usuario usuario) {
        return usuario.aindaPodePegarLivro();
    }

    @Override
    public String mensagemErro() {
        return "O usuário chegou ao limite máximo de livros emprestados.";
    }
}
