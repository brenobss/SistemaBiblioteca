package Console;

import Biblioteca.Biblioteca;

public interface Comando {
    void executar(Biblioteca biblioteca, Parametros parametros);
}
