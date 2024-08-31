package Observador;

import Biblioteca.Livro;

public interface Observavel {
    void adicionarObservador(int codigoUsuario, int codigoLivro);
    void removerObservador(Observador observador);
    void notificarObservadores(int codigoLivro);
}
