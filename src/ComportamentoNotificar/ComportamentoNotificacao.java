package ComportamentoNotificar;

import Biblioteca.Biblioteca;
import Biblioteca.Livro;
import Observador.Observador;

public interface ComportamentoNotificacao {
    public void notificar(Biblioteca biblioteca, Observador observador, Livro livro);
}
