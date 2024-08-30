package Console;

import Biblioteca.Biblioteca;

import java.util.HashMap;
import java.util.List;

public class Console {
    private static Console instancia;
    private HashMap<String, Comando> comandos = new HashMap<String, Comando>();

    public static Console obterInstancia(){
        if(instancia == null){
            instancia = new Console();
        }
        return instancia;
    }

    private void initComandos() {
        comandos.put("emp", new EmprestimoComando());
        comandos.put("dev", new DevolucaoComando());
        comandos.put("res", new ReservaComando());
        comandos.put("liv", new ConsultaLivroComando());
        comandos.put("obs", new ObservarComando());
        comandos.put("usu", new ConsultaUsuarioComando());
        comandos.put("ntf", new NotificaComando());
        comandos.put("sai", new SaidaComando());
    }

    public void lerComando(Biblioteca biblioteca,String comando, Parametros Parametros){
        comandos.get(comando).executar(biblioteca, Parametros);

    }
    public void mostrarMensagem(String mensagem){
        System.out.println(mensagem);
    }
}
