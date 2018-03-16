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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristy
 */
public class AbrirArchivo {

    public ArrayList cadena = new ArrayList();
    public int bandera = 0;
    public int v = 0;
    public int d = 0;
    public int p = 0;
    public int i = 0;

    public String cadenaV = "";
    public int e = 0;
    TablaSimbolos ts;
    ListaError le = new ListaError();
    String cadenaE1 = le.getError().get(1).getMsj();
    String cadenaE0 = le.getError().get(0).getMsj();
    public ArrayList<Variable> variable = new ArrayList<Variable>();
    ListaVariables lv = new ListaVariables();
    public int id=0;
    
    public void abrir() {
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
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void crearCadena() {
        cadenaV = ""; 
        
        if (i < this.cadena.size()) {
            if (!validarLetra((char) this.cadena.get(i)) && (!validarNumero((char) this.cadena.get(i)) && (!validarPunto((char) this.cadena.get(i))) && (!validarEspacio((char) this.cadena.get(i))) )) {
                
                //Validar ;)
                if ((validarPuntoComa((char) this.cadena.get(i))) && (validarGuino((char) this.cadena.get(i+1))) ){
                    cadenaV += this.cadena.get(i) +""+ this.cadena.get(i+1);
                    i++;
                    i++;
                }else{
                    cadenaV += this.cadena.get(i);
                    i++;

                    if (!esPalabraReservada(cadenaV)){ 
                        e++;
                    }
                }
                
            } else {
                while (i < cadena.size()) {

                    //Validar Espacio
                    if (validarEspacio((char) this.cadena.get(i))) {
                        i++;
                        break;
                    }

                    if (this.bandera == 0) {
                        //Validar Variable
                        if (validarLetra((char) this.cadena.get(i))) {
                            bandera++;
                            v++;
                            cadenaV += this.cadena.get(i);
                            i++;
                        } else {
                            //Validar Dígito
                            if (validarNumero((char) this.cadena.get(i))) {
                                bandera++;
                                d++;
                                cadenaV += this.cadena.get(i);
                                i++;
                            } else {
                                cadenaV = "" + ((char) this.cadena.get(i));
                                i++;
                            }
                        }
                    } else {
                        //Validar variable
                        if (v > 0) {
                            if (validarLetra((char) this.cadena.get(i)) || validarNumero((char) this.cadena.get(i))) {
                                cadenaV += this.cadena.get(i);
                                i++;
                            }
                            if ((i < cadena.size()) && (!validarLetra((char) this.cadena.get(i)) && (!validarNumero((char) this.cadena.get(i))))) {
                                break;
                            }
                        } else {
                            //Validar digito
                            if (d > 0) {
                                if (validarPunto((char) this.cadena.get(i)) && p == 0) {
                                    p++;
                                    cadenaV += this.cadena.get(i);
                                    i++;
                                }
                                if (validarNumero((char) this.cadena.get(i)) && p == 0) {
                                    cadenaV += this.cadena.get(i);
                                    i++;
                                }
                                if (validarNumero((char) this.cadena.get(i)) && p == 1) {
                                    cadenaV += this.cadena.get(i);
                                    i++;
                                }
                                if ((i < cadena.size()) && (!validarNumero((char) this.cadena.get(i)) && (!validarPunto((char) this.cadena.get(i))))) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            cadenaV = "No hay más caracteres";
        }
        bandera = 0;
        v = 0;
        d = 0;
        p = 0;

    }

    public boolean validarLetra(char c) {
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarNumero(char c) {
        if (c >= 48 && c <= 57) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarPunto(char c) {
        if (c == 46) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarEspacio(char c) {
        if (c == 32) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarPuntoComa(char c) {
        if (c == 59) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarGuino(char c) {
        if (c == 41) {
            return true;
        } else {
            return false;
        }
    }

    private boolean esPalabraReservada(String cadenaV) {
        TablaSimbolos ts = new TablaSimbolos();
        int error = 0;

        for (int x = 0; x < ts.simbolos.size(); x++) {
            if (cadenaV.equals(ts.getSimbolos().get(x).toString())) {
                error++;
                return true;
            }
        }

        if (error < 1) {
            return false;
        } else {
            return true;
        }

    }

    public String respuestaVariable(){

        //Comprobar que la caden no sea una palabra reservada
        crearCadena();       
        if(e!=0){
            e=0;
            return cadenaE0 + cadenaV;    
        }else{
            if (!esPalabraReservada(cadenaV)) {
                if (!validarEspacio((char) this.cadena.get(i-1))){
                    if(validarNumero(cadenaV.charAt(0))){
                       return cadenaV;
                    }else{
                       agregarVariables(cadenaV);
                       return cadenaV;
                    }
                }else{
                    return "";
                }
            } else {
                return cadenaE1 + cadenaV;
            }
        }
    }
    
    public void agregarVariable(String cadena){
        int id=0;
        variable.add(new Variable(id, cadena));
        id++;
    }

    public void agregarVariables(String cadena){       
        lv.agregarVariable(id, cadena);
        id++;
    }
    
    public void getVariable() {
        for(int i=0; i<lv.variable.size(); i++){
            System.out.println("ID: "+lv.variable.get(i).getId() + "   Variable: "+ lv.variable.get(i).getVariable());
        }
    }
   
}
