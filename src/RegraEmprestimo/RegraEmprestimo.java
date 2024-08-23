package RegraEmprestimo;

import Biblioteca.Livro;
import Usuario.Usuario;

public interface RegraEmprestimo {
    boolean validar(Livro livro, Usuario usuario);
    String mensagemErro();
}
