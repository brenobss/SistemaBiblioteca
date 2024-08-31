package Console;

import Biblioteca.Biblioteca;

public class ConsultarNotificacoesComando implements Comando{
    @Override
    public void executar(Biblioteca biblioteca, Parametros parametros) {
        int codigoObservador = parametros.getCodigoUm();
        biblioteca.consultarNotificacoesObservadores(codigoObservador);
    }
}
