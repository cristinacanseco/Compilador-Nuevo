/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisislexico;

/**
 *
 * @author Cristy
 */
public class Variable {
    public int id;
    public String valor;
    public String variable;
    public String tipo;
    
    
    public Variable(){
        this.id=-1;
        this.variable="";
        this.tipo = "";
        this.valor = "?";
    }
    
    public Variable(int id, String valor, String variable, String tipo){
       this.id = id;
        this.variable = variable;
        this.tipo = tipo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getVariable() {
        return variable;
    }

    public String getTipo() {
        return tipo;
    } 

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
        
}
