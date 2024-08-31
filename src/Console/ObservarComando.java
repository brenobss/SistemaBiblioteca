package Console;

import Biblioteca.Biblioteca;

public class ObservarComando implements Comando{
    @Override
    public void executar(Biblioteca biblioteca, Parametros parametros) {
        int codigoUsuario = parametros.getCodigoUm();
        int codigoLivro = parametros.getCodigoDois();
        biblioteca.adicionarObservador(codigoUsuario, codigoLivro);
    }
}
