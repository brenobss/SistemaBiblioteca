package Console;

import Biblioteca.Biblioteca;

public class SaidaComando implements Comando{
    @Override
    public void executar(Biblioteca biblioteca, Parametros parametros) {
        System.out.println("Comando saída, encerrando o sistema...");
        System.exit(0);
    }
}
