package Observador;

import Biblioteca.Livro;

public interface Observavel {
    void adicionarObservador(Observador observador);
    void removerObservador(Observador observador);
    void notificarObservadores(int codigoLivro);
}
