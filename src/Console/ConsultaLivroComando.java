package Console;

import Biblioteca.Biblioteca;

public class ConsultaLivroComando implements Comando{
    @Override
    public void executar(Biblioteca biblioteca,Parametros parametros) {
        int codigoLivro = parametros.getCodigoUm();
        biblioteca.consultarLivroPorCodigo(codigoLivro);

    }
}
