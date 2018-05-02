/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisislexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristy
 */
public class AbrirArchivo {

    public ArrayList<Character> cadena = new ArrayList();
    
    public ArrayList<Character> abrirArchivo() {
        try {
            //llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();
            FileReader fr = new FileReader(abre);
            int caracter = fr.read();
            while (caracter != -1) {
                cadena.add((char) caracter);
                caracter = fr.read();
            }
            for(int i=0; i<cadena.size(); i++){
                if(cadena.get(i)==32)
                    cadena.remove(i);
            }
            return cadena;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
    
     public ArrayList<Character> abrirInstancia() {

        LinkedList<String> lista = new LinkedList();
        try {
            //llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();

            //recorremos el archivo y lo leemos
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                
                while (true) {
                    int caracter = archivos.read();
                    while (caracter != -1) {
                        if(caracter == 10 || caracter == 13 || caracter == 32){
                            
                        }else{
                            cadena.add((char) caracter);
                        }
                        caracter = archivos.read();
                        
                    }
                   break;
                }
                lee.close();
                    
                return cadena;
            }
        } catch (Exception e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Archivo no encontrado",
                    "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}