package Console;

import Biblioteca.Biblioteca;

public class NotificaComando implements Comando{
    @Override
    public void executar(Biblioteca biblioteca, Parametros parametros) {
        int codigoLivro = parametros.getCodigoUm();
        biblioteca.notificarObservadores(codigoLivro);
    }
}
