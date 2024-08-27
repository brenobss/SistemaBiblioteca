package ComportamentoEmprestar;

import Biblioteca.Biblioteca;
import Biblioteca.Livro;
import Usuario.Usuario;

public interface ComportamentoEmprestimo {
    void emprestarLivro(Livro livro, Usuario usuario, Biblioteca biblioteca);
}
