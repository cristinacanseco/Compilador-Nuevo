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
public class Sintactico2 {
    Lexico lex;
    ListaError le = new ListaError();
    String error2 = le.getError().get(2).getMsj();
    String error3 = le.getError().get(3).getMsj();
    String token;
    ArrayList<String> palabras;
    int p;
    long tiempoT=0;
    long startTime =0;
    int e=0;
    
    public Sintactico2(Lexico lex){
        this.lex = lex;
        this.palabras = new ArrayList();
        this.p = 0;
    }
    
    public void Programa(){
        startTime = System.currentTimeMillis();
        Var();
        //Fijo();
        //Instrucciones();
        calcularError();
    }
    
    public void calcularError(){
        tiempoT = (System.currentTimeMillis()- startTime);
         if(e>0){
            System.out.println("Se encontraron "+ e + " error(es). (Tiempo total: " +tiempoT + " segundos)" );
        }else{
            System.out.println("CONSTRUIDO CORRECTAMENTE (Tiempo total: " +tiempoT + " segundos)" );
        }
    }

    private void encontrarPalabra() {
        token = lex.lexicoR();
        palabras.add(p, token);
        p++;
    }
    
    public void encontrarError(String mensaje){
        e++;
        System.out.println(error3 + mensaje);
        calcularError();
        System.exit(0);
    }

    private void Var() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
