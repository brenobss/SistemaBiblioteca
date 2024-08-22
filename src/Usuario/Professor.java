package Usuario;

public class Professor extends Usuario {
    @Override
    public void fazerEmprestimoLivro(Livro livro) {
        super.fazerEmprestimoLivro(livro);
    }

    @Override
    public void devolverLivro(Livro livro) {
        super.devolverLivro(livro);
    }

    @Override
    public void reservarLivro(Livro livro) {
        super.reservarLivro(livro);
    }

    @Override
    public void notificar(String mensagem) {

    }
}
