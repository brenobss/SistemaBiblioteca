package Biblioteca;

public class Exemplar {
    private int codigo;
    private String status;

    public void alterarStatus(String novoStatus){
        this.setStatus(novoStatus);
    }

    public void setStatus(String status){
        this.status = status;
    }
}
