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
        this.error.add(new Error(0, "¡¡Error!! Caracter Inválido: "));
        this.error.add(new Error(1, "¡¡Error!! Cadena inválida, es una palabra reservada: "));
    }

    public ArrayList<Error> getError() {
        return error;
    }

    
    @Override
    public String toString() {
        return "ListaError{" + "error=" + error + '}';
    }
    
    
}
