package RegraEmprestimo;

import Biblioteca.Exemplar;
import Biblioteca.Livro;
import Usuario.Usuario;

import java.util.List;

public class RegraExemplaresDisponiveis implements RegraEmprestimo{
    @Override
    public boolean validar(Livro livro, Usuario usuario) {
        List<Exemplar> exemplares = livro.getExemplares();
        for(Exemplar exemplar : exemplares) {
            if(exemplar.isDisponivel()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String mensagemErro() {
        return "Não há exemplares disponíveis.";
    }
}
