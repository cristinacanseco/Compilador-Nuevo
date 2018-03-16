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
public class TablaSimbolos {
    protected ArrayList simbolos = new ArrayList();

    public TablaSimbolos() {
        this.simbolos.add("Ent");
        this.simbolos.add("Dec");
        this.simbolos.add("Let");
        this.simbolos.add("Fijo");
        this.simbolos.add("Itera");
        this.simbolos.add("Fin");
        this.simbolos.add("Mas");
        this.simbolos.add("Menos");
        this.simbolos.add("Ja");
        this.simbolos.add("to");
        this.simbolos.add("Kein");
        this.simbolos.add("Nein");
        this.simbolos.add("Funcion");
        this.simbolos.add("Ven");
        this.simbolos.add("+");
        this.simbolos.add("-");
        this.simbolos.add("*");
        this.simbolos.add("/");
        this.simbolos.add(":");
        this.simbolos.add("(");
        this.simbolos.add(")");
        this.simbolos.add("=");
        this.simbolos.add("==");
        this.simbolos.add("<");
        this.simbolos.add(">");
        this.simbolos.add("<=");
        this.simbolos.add(">=");
        this.simbolos.add("||");
        this.simbolos.add("!=");
        this.simbolos.add(";)");
        this.simbolos.add(",");
        this.simbolos.add("&&");
    }
    
    
    
    public void agregarElementos(String e){
        this.simbolos.add(e);
    }

    public ArrayList getSimbolos() {
        return simbolos;
    }

    @Override
    public String toString() {
        return "TablaSimbolos{" + "simbolos=" + simbolos + '}';
    }
    
    
    
}
