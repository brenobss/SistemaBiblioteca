package Console;

public class Console {
    private static Console instancia;

    public static Console obterInstancia(){
        if(instancia == null){
            instancia = new Console();
        }
        return instancia;
    }

    public void lerComando(){

    }
    public void mostrarMensagem(String mensagem){
        System.out.println(mensagem);
    }
}
