package RegraEmprestimo;

import Biblioteca.Livro;
import Usuario.Usuario;

public class RegraExemplaresDisponiveis implements RegraEmprestimo{
    @Override
    public boolean validar(Livro livro, Usuario usuario) {
        return livro.getExemplares() > 0;
    }

    @Override
    public String mensagemErro() {
        return "Não há exemplares disponíveis.";
    }
}
