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
    public String variable;
    
    public Variable(){
        this.id=-1;
        this.variable="";
    }
    
    public Variable(int id, String variable){
       this.id = id;
        this.variable = variable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
    
    
}
