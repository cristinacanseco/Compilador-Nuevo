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
public class Error {
    
    public int id;
    public String msj;
    
    public Error(){
        this.id=-1;
        this.msj="";
    }
    
    public Error(int i, String msj){
        this.id=i;
        this.msj=msj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }
    
    
}
