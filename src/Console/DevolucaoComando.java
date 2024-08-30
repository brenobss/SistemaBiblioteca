package Console;

import Biblioteca.Biblioteca;

public class DevolucaoComando implements Comando{
    @Override
    public void executar(Biblioteca biblioteca, Parametros parametros) {
        int codigoUsuario = parametros.getCodigoUm();
        int codigoLivro = parametros.getCodigoDois();
        biblioteca.devolverLivro(codigoLivro, codigoUsuario);

    }
}
