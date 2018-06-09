/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensamblador;

import analisislexico.Sintactico;
import analisislexico.Variable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Cristy
 */
public class Ensamblador {

    public String ruta = "D:/crist/Compiladores/ensamblador2.txt";
    public String t1 = ".model tiny";
    public String t2 = ".stack";
    public String t3 = ".data ";
    public String t4 = ".code";
    public String t5 = ".startup";
    
    public int indice, ifNum, elseNum;
    
    public BufferedWriter bw;
    public File archivo;
    
    Sintactico sint ;
    public ArrayList<Variable> variables;
    public ArrayList<String> palabras;
    public ArrayList<String> identificadores;
    
    public Ensamblador(Sintactico s){
        sint = s;
        variables  = sint.getVariables();
        palabras = sint.getPalabras();
        identificadores = sint.getIdentificadores();
        indice = 0;
        ifNum = 0;
        elseNum = 0;
    }
    
    public void generarTxt(){      
        try {
            archivo = new File(ruta);
            bw = new BufferedWriter(new FileWriter(archivo));
          
            escribir(t1);
            escribir(t2);
            escribir(t3);
            escribirVariables();
            escribir(t4);
            escribir(t5);
            operaciones();
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    private void escribirVariables() {
      
       for(int i=0; i<variables.size(); i++){
           
           String tipo = "";

           if(variables.get(i).getTipo().equals("Dec")  || variables.get(i).getTipo().equals("Ent") ){
               tipo = "DB";
           }else{
               if(variables.get(i).getTipo().equals("Let") ){
                   tipo = "DW";
               }
           }

           String var = variables.get(i).getVariable() +" "+ tipo + " " + variables.get(i).getValor();
           escribir(var);
           
       }
       
    }

    public void obtenerInstrucciones(){
         for(int i =0; i<palabras.size(); i++){
             if(palabras.get(i).equals("{"))
                 indice = i;
         }
    }
    
    private void operaciones() {
        obtenerInstrucciones();
        
        for(int i =indice; i<palabras.size(); i++){
            switch (palabras.get(i)){ 
                case "ident":
                    int posIni = i; 
                    i++;
                    i++;
                    while(!palabras.get(i).equals(";)")){
                        String mov = "";
                        switch(identificadores.get(i)){
                            case "+":
                                i++;
                                mov = "add al,"+identificadores.get(i);
                                break;
                            case "-":
                                i++;
                                mov = "sub al,"+identificadores.get(i);
                                break;
                            case "*":
                                i++;
                                mov = "mul al,"+identificadores.get(i);
                                break;
                            case "/":
                                i++;
                                mov = "div al,"+identificadores.get(i);
                                break;
                            default: 
                                mov = "mov al,"+identificadores.get(i);
                                break;
                        }
                       
                        escribir(mov);
                        i++;   
                    }
                    
                    String mov = "mov "+identificadores.get(posIni)+",al";
                    escribir(mov);
                    break;
                
                case "Ja":
                    i++;
                    escribir("mov al,"+identificadores.get(i));
                    i++;
                    escribir("cmp al,"+identificadores.get(i+1));
                   
                    switch(identificadores.get(i)){
                        case "<": 
                            ifNum++;
                            escribir("jl if"+ifNum);  
                            elseNum++;
                            escribir("jge else"+elseNum);
                            break;
                        case ">":                            
                            ifNum++;
                            escribir("jg if"+ifNum);                           
                            elseNum++;
                            escribir("jle else"+elseNum);
                            break;
                        case "<=":               
                            ifNum++;
                            escribir("jle if"+ifNum);          
                            elseNum++;
                            escribir("jl else"+elseNum);
                            break;
                        case ">=":                          
                            ifNum++;
                            escribir("jge if"+ifNum);
                            elseNum++;
                            escribir("jg else"+elseNum);
                            break;
                        case "!=":
                            ifNum++;
                            escribir("jne if"+ifNum);
                            elseNum++;
                            escribir("je else"+elseNum);
                            break;
                        case "==":   
                            ifNum++;
                            escribir("je if"+ifNum);
                            elseNum++;
                            escribir("jne else"+elseNum);
                            break;                            
                    }
                    
                    escribir("if"+ifNum+":");
                    i++;
                    i++;
                    
                        escribir("jmp if"+ifNum);
                    break;
                
               
                default:
                    break;
            }
            
        }
        
    }
    
    public void escribir(String texto){
        try {
            bw.append(texto);
            bw.newLine();
        } catch (IOException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
