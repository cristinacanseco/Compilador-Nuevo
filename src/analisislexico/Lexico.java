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
public class Lexico {
    ArrayList<Character> cadena = new ArrayList<>();
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
    
    public Lexico(ArrayList<Character> lista) {
        this.cadena = lista;   
    }
    
    public Lexico (){}
    
    public String lexicoR(){
        //Comprobar que la caden no sea una palabra reservada
        if(cadena.isEmpty()){
            return "soyVacio";
        }else{
            crearCadena();    
            System.out.println("La cadena es: " +cadenaV);
            if(e!=0){
                e=0;
                //return cadenaE0 + cadenaV;    
                return cadenaE0 + cadenaV;    
            }else{
                if (!esPalabraReservada(cadenaV)) {       

                        if(validarNumero(cadenaV.charAt(0))){
                           //return cadenaV;
                           return "num";
                        }else{
                           if(cadenaV.equals("noMas")){
                               return "noMas";
                           }else{
                           agregarVariables(cadenaV);
                           return "ident";
                           }
                           //return cadenaV;
                        }
                }else {
                    //return cadenaE1 + cadenaV;
                    return cadenaV;
                }
            }
        }
        
    }
    
    public void crearCadena() {
        cadenaV = ""; 
        
        if (i < this.cadena.size()) {
            if (!validarLetra((char) this.cadena.get(i)) && (!validarNumero((char) this.cadena.get(i)) && (!validarPunto((char) this.cadena.get(i)))  )) {      
                //Validar ;)
                if ((validarPuntoComa((char) this.cadena.get(i)))  ){
                    cadenaV += this.cadena.get(i);
                    i++;
                    if ( i<this.cadena.size() && (validarGuino((char) this.cadena.get(i))) ){
                        cadenaV += this.cadena.get(i);
                        i++;
                    }
                }else{
                    //Vaidar ==
                    if ((validarIgual((char) this.cadena.get(i))) ){
                        cadenaV += this.cadena.get(i);
                        i++;
                        if ( i<this.cadena.size() && (validarIgual((char) this.cadena.get(i))) ){
                            cadenaV += this.cadena.get(i);
                            i++;
                        }
                    }else{
                        //Validar <=
                        if ((validarMenor((char) this.cadena.get(i)))){
                            cadenaV += this.cadena.get(i);
                            i++;
                            if ( i<this.cadena.size() && (validarIgual((char) this.cadena.get(i))) ){
                                cadenaV += this.cadena.get(i);
                                i++;
                            }
                        }else{
                            //Validar >= 
                            if ((validarMayor((char) this.cadena.get(i))) ){
                                cadenaV += this.cadena.get(i);
                                i++;
                                if ( i<this.cadena.size() && (validarIgual((char) this.cadena.get(i))) ){
                                    cadenaV += this.cadena.get(i);
                                    i++;
                                }
                            }else{
                                //Validar ||
                                if ((validarPipe((char) this.cadena.get(i))) ){
                                    cadenaV += this.cadena.get(i);
                                    i++;
                                    if ( i<this.cadena.size() && (validarPipe((char) this.cadena.get(i))) ){
                                        cadenaV += this.cadena.get(i);
                                        i++;
                                    }
                                }else{
                                    //Validar !=
                                    if ((validarAdmira((char) this.cadena.get(i)))){
                                        cadenaV += this.cadena.get(i);
                                        i++;
                                        if ( i<this.cadena.size() && (validarIgual((char) this.cadena.get(i))) ){
                                            cadenaV += this.cadena.get(i);
                                            i++;
                                        }
                                    }else{
                                        //Validar &&
                                        if ((validarAmperson((char) this.cadena.get(i))) ){
                                            cadenaV += this.cadena.get(i);
                                            i++;
                                            if ( i<this.cadena.size() && (validarAmperson((char) this.cadena.get(i))) ){
                                                cadenaV += this.cadena.get(i);
                                                i++;
                                            }                                           
                                        }else{
                                            cadenaV += this.cadena.get(i);
                                            i++;
                                            if (!esPalabraReservada(cadenaV)){ 
                                                e++;
                                            }
                                           
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
            } else {
                while (i < cadena.size()) {                    
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
                                //Es punto para entrar aquí
                                cadenaV = "" + ((char) this.cadena.get(i));
                                i++;
                                //break;
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
                            if (esPalabraReservada(cadenaV)){ 
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
            cadenaV = "noMas";
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

    public boolean validarIgual(char c) {
        if (c == 61) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validarMenor(char c) {
        if (c == 60) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validarMayor(char c) {
        if (c == 62) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validarAdmira(char c) {
        if (c == 33) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validarAmperson(char c) {
        if (c == 38) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validarPipe(char c) {
        if (c == 124) {
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

    public void agregarVariables(String cadena){       
        lv.agregarVariable(id, cadena);
        id++;
    }
    
}