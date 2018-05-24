/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import analisislexico.AbrirArchivo;
import analisislexico.Lexico;
import analisislexico.Sintactico;
import analisislexico.Sintactico3;
import analisislexico.Sintactico4;

/**
 *
 * @author Cristy
 */
public class Compilador {
    
    public static void main(String args[]){       
        AbrirArchivo abrirA = new AbrirArchivo();
        Sintactico4 sin = new Sintactico4(new Lexico(abrirA.abrirInstancia()));
        sin.Programa();
    }
    
}
