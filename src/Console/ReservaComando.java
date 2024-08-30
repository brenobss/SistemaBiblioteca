package Console;

import Biblioteca.Biblioteca;

public class ReservaComando implements Comando{
    @Override
    public void executar(Biblioteca biblioteca, Parametros parametros) {
        int codigoUsuario = parametros.getCodigoUm();
        int codigoLivro = parametros.getCodigoDois();
        biblioteca.fazerReserva(codigoUsuario, codigoLivro);

    }
}
