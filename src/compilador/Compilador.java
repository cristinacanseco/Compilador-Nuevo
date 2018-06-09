/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import analisislexico.AbrirArchivo;
import analisislexico.Lexico;
import analisislexico.Sintactico;
import analisislexico.Sintactico_Ensamblador;
import ensamblador.Ensamblador;

/**
 *
 * @author Cristy
 */
public class Compilador {
    
    public static void main(String args[]){   
        
        AbrirArchivo abrirA = new AbrirArchivo();
        
//        Sintactico sin = new Sintactico(new Lexico(abrirA.abrirInstancia()));
//        sin.Programa();
//        
//        Ensamblador em = new Ensamblador(sin);
//        em.generarTxt();

        Sintactico_Ensamblador sin_en = new Sintactico_Ensamblador(new Lexico(abrirA.abrirInstancia()));
        sin_en.Programa();
        
    }
    
}
