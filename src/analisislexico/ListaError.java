/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisislexico;

import java.util.ArrayList;

/**
 *
 * @author Cristy
 */
public class ListaError {
    public ArrayList<Error> error;
    
    public ListaError(){
        this.error = new ArrayList<Error>();
        this.error.add(new Error(0, "Error 0.  Caracter Inválido "));
        this.error.add(new Error(1, "Error 1.  Cadena inválida, es una palabra reservada "));
        this.error.add(new Error(2, "Error 2.  No es un identificador válido "));
        this.error.add(new Error(3, "Error 3.  Se esperaba "));
        this.error.add(new Error(4, "Error 4.  No se esperaban más instrucciones"));
        this.error.add(new Error(5, "Error 5.  Programa incorrecto, debe contener por lo menos una instrucción"));
    }

    public ArrayList<Error> getError() {
        return error;
    }

    
    @Override
    public String toString() {
        return "ListaError{" + "error=" + error + '}';
    }
    
    
}
