package RegraEmprestimo;
import Usuario.Usuario;
import Biblioteca.Livro;

public class RegraLivroAtrasado implements RegraEmprestimo{
    @Override
    public boolean validar(Livro livro, Usuario usuario) {
        return !usuario.temLivroAtrasado();
    }

    @Override
    public String mensagemErro() {
        return "Primeiro devolva os exemplares atrasados!";
    }
}
