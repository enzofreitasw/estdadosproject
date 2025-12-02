package aed;

public class Recurso {
    // Tipos estáticos para facilitar menus
    public static String[] tipos = {"Água Potável","Alimentos","Higiene","Medicamentos", "Roupas", "Combustível"};
    
    private String tipo;
    private int qtd;

    public Recurso(int tipoIndex, int qtd){
        // tipoIndex 1 a 6
        if(tipoIndex > 0 && tipoIndex <= tipos.length)
            this.tipo  = tipos[tipoIndex-1];
        else 
            this.tipo = "Indefinido";
            
        this.qtd = qtd;
    }

    public int getQtd() { return qtd; }
    public void setQtd(int qtd) { this.qtd = qtd; }
    public String getTipo() { return tipo; }
}