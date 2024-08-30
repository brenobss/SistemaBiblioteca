package Console;

import Biblioteca.Biblioteca;

public class ConsultaUsuarioComando implements Comando{
    @Override
    public void executar(Biblioteca biblioteca, Parametros parametros) {
        int codigoUsuario = parametros.getCodigoUm();
        biblioteca.consultarUsuarioPorCodigo(codigoUsuario);
    }
}
