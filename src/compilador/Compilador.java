/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import analisislexico.AbrirArchivo;
import analisislexico.ListaVariables;
import analisislexico.TablaSimbolos;

/**
 *
 * @author Cristy
 */
public class Compilador {
    
    public static void main(String args[]){
        AbrirArchivo abrirA = new AbrirArchivo();
        abrirA.abrir();
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println(abrirA.respuestaVariable());
        System.out.println("\n-- Variables encontradas --");
        abrirA.getVariable();
        
        System.out.println();
    }
    
}
