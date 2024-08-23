package RegraEmprestimo;
import Usuario.Usuario;
import Biblioteca.Livro;

public class RegraLivroJaEmprestado implements RegraEmprestimo{
    @Override
    public boolean validar(Livro livro, Usuario usuario) {
        return !Usuario.jaTemLivroIgualEmprestado(livro);
    }

    @Override
    public String mensagemErro() {
        return "Usuário já tem esse livro emprestado.";
    }
}
