/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisislexico;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Cristy
 */
public class ListaVariables {
    public ArrayList<Variable> variable;
 
    
    public ListaVariables() {
        this.variable = new ArrayList<Variable>();
    }

    public ArrayList<Variable> getVariable() {
        return variable;
    }

    public void setVariable(ArrayList<Variable> variable) {
        this.variable = variable;
    }
    
    public void agregarVariable(int id, String variable){
        this.variable.add(new Variable(id, variable));
        //System.out.println();
    }
     
}
