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
    public int id=0;
    public String tipo;
    Variable aux;
    
    public boolean esVariableAun;
    
    public Lexico(ArrayList<Character> lista) {
        this.cadena = lista;
        this.esVariableAun = true;
        this.tipo = "";
    }
    
    public Lexico (){}
    
    public String lexicoR(){
        //Comprobar que la caden no sea una palabra reservada
        if(cadena.isEmpty()){
            return "soyVacio";
        }else{
            crearCadena();    
            //System.out.println("La cadena es: " +cadenaV);
            if(e!=0){
                e=0;
                //return cadenaE0 + cadenaV;    
                return cadenaE0 + cadenaV;    
            }else{
                if (!esPalabraReservada(cadenaV)){       
                    if(cadenaV.equals("")){
                        return "noMas";
                    }else{   
                        if(validarNumero(cadenaV.charAt(0))){
                           //return cadenaV;
                           return "num";
                        }else{
                           if(cadenaV.equals("noMas")){
                               return "noMas";
                           }else{         
                                if(validarLetra(cadenaV.charAt(0))){
                                    if(esVariableAun){
                                        String mensaje = agregarVariables(cadenaV);
                                        if(!mensaje.equals("exito")){
                                            return "repetido";
                                        }else{
                                            return "ident";
                                        }
                                       
                                    }else{
                                        if(buscarPalabraEnVariables(cadenaV)){
                                            return "ident";
                                        }else{
                                            return "noEncontrada";
                                        }
                                    }
                                    
                                }else{
                                     return cadenaV;
                                }
                           }
                        }
                    }
                        
                }else {
                    
                    if(cadenaV.equals("")){
                        i++;
                        crearCadena();
                    }else{
                    //return cadenaE1 + cadenaV;
                        if(cadenaV.equals("Fijo") || cadenaV.equals("{") | cadenaV.equals("Itera") | cadenaV.equals("Ja") | cadenaV.equals("Funcion") | cadenaV.equals("Ven")   )
                            setEsVariableAun(false);
                        
                        if(cadenaV.equals("Ent") || cadenaV.equals("Dec") || cadenaV.equals("Let") )
                            this.tipo  = cadenaV;
                        
                        return cadenaV;
                    }
                
                }
            }
        }
        return null;
        
    }
    
    
     public String lexicoR2(){
        //Comprobar que la caden no sea una palabra reservada
        if(cadena.isEmpty()){
            return "soyVacio";
        }else{
            crearCadena();    
            //System.out.println("La cadena es: " +cadenaV);
            if(e!=0){
                e=0;
                //return cadenaE0 + cadenaV;    
                return cadenaE0 + cadenaV;    
            }else{
                if (!esPalabraReservada(cadenaV)){       
                    if(cadenaV.equals("")){
                        return "noMas";
                    }else{   
                        if(validarNumero(cadenaV.charAt(0))){
                           //return cadenaV;
                           return "num";
                        }else{
                           if(cadenaV.equals("noMas")){
                               return "noMas";
                           }else{         
                                if(validarLetra(cadenaV.charAt(0))){
                                    if(esVariableAun){
                                        String mensaje = agregarVariables(cadenaV);
                                        if(!mensaje.equals("exito")){
                                            return "repetido";
                                        }else{
                                            return cadenaV;
                                        }
                                       
                                    }else{
                                        if(buscarPalabraEnVariables(cadenaV)){
                                            return cadenaV;
                                        }else{
                                            return cadenaV;
                                        }
                                    }
                                    
                                }else{
                                     return cadenaV;
                                }
                           }
                        }
                    }
                        
                }else {
                    
                    if(cadenaV.equals("")){
                        i++;
                        crearCadena();
                    }else{
                    //return cadenaE1 + cadenaV;
                        if(cadenaV.equals("Fijo") || cadenaV.equals("{") | cadenaV.equals("Itera") | cadenaV.equals("Ja") | cadenaV.equals("Funcion") | cadenaV.equals("Ven")   )
                            setEsVariableAun(false);
                        
                        if(cadenaV.equals("Ent") || cadenaV.equals("Dec") || cadenaV.equals("Let") )
                            this.tipo  = cadenaV;
                        
                        return cadenaV;
                    }
                
                }
            }
        }
        return null;
        
    }
    
    
    public void crearCadena() {
        cadenaV = ""; 
                
        if (i < this.cadena.size()) {
            while(i < this.cadena.size() && validarEspacio((char) this.cadena.get(i)) ){
                    i++;
                    cadenaV="";
            }  
            
            while(i < this.cadena.size() && tabulador((char) this.cadena.get(i)) ){
                    i++;
                    cadenaV="";
            }  
            
            if (i < this.cadena.size() &&!validarLetra((char) this.cadena.get(i)) && (!validarNumero((char) this.cadena.get(i)) && (!validarPunto((char) this.cadena.get(i)))  )) {      
                
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
                                            //Validar :)
                                            if ((validar2Puntos((char) this.cadena.get(i)))  ){
                                                cadenaV += this.cadena.get(i);
                                                i++;
                                                if ( i<this.cadena.size() && (validarGuino((char) this.cadena.get(i))) ){
                                                    cadenaV += this.cadena.get(i);
                                                    i++;
                                                }
                                            }else{
                                            
                                                if ( llaveAbre((char) this.cadena.get(i)) ){
                                                    cadenaV += this.cadena.get(i);
                                                    i++;

                                                }else{
                                                    if ( llaveCierra((char) this.cadena.get(i)) ){
                                                        cadenaV += this.cadena.get(i);
                                                        i++;
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
                        }
                    }
                }
                
                
            } else {
                
                while (i < cadena.size()) {    
  
                    if (this.bandera == 0) {
                        //Validar Variable
                        if ( i<cadena.size() && validarLetra((char) this.cadena.get(i))) {
                            bandera++;
                            v++;
                            cadenaV += this.cadena.get(i);
                            i++;
                        } else {
                            //Validar Dígito
                            if ( i<cadena.size() && validarNumero((char) this.cadena.get(i)) ) {
                                bandera++;
                                d++;
                                cadenaV += this.cadena.get(i);
                                i++;
                            } else {
                                if( i<cadena.size() && validarEspacio((char) this.cadena.get(i)) ){
                                    i++;
                                    break;
                                }else{
                                    //Es punto para entrar aquí
                                    cadenaV = "" + ((char) this.cadena.get(i));
                                    i++;
                                    break;
                                }
                                //break;
                            }
                        }
                    } else {
                        //Validar variable
                        if (v > 0) {
                            if ( i<cadena.size() && (validarLetra((char) this.cadena.get(i)) || validarNumero((char) this.cadena.get(i)))) {
                                cadenaV += this.cadena.get(i);
                                i++;
                            }
                            if ((i < cadena.size()) && (!validarLetra((char) this.cadena.get(i)) && (!validarNumero((char) this.cadena.get(i))))) {
                                break;
                            }
                            if (esPalabraReservada(cadenaV)){ 
                                break;
                            }
                            if( i<cadena.size() && validarEspacio((char) this.cadena.get(i)) ){
                                i++;
                                break;
                            }
                        } else {
                            //Validar digito
                            if (d > 0) {
                                if (  i<cadena.size() &&validarPunto((char) this.cadena.get(i)) && p == 0  ) {
                                    p++;
                                    cadenaV += this.cadena.get(i);
                                    i++;
                                }
                                if ( i<cadena.size() && validarNumero((char) this.cadena.get(i)) && p == 0) {
                                    cadenaV += this.cadena.get(i);
                                    i++;
                                }
                                if (i <cadena.size() && validarNumero((char) this.cadena.get(i)) && p == 1  ) {
                                    cadenaV += this.cadena.get(i);
                                    i++;
                                }
                                if ((i < cadena.size()) && (!validarNumero((char) this.cadena.get(i)) && (!validarPunto((char) this.cadena.get(i)))) ) {
                                    break;
                                }
                                if( i<cadena.size() && validarEspacio((char) this.cadena.get(i))){
                                    i++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            cadenaV = "noMas";
            i=0;
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
    
    public boolean validar2Puntos(char c) {
        if (c == 58) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean llaveAbre(char c) {
        if (c == 123) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean llaveCierra(char c) {
        if (c == 125) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean tabulador(char c) {
        if (c == 9) {
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

    public String agregarVariables(String cadena){   
        int cont=0;
        
        for (int h= 0; h<variable.size(); h++){
            if(variable.get(h).getVariable().equals(cadena))
                cont++;
        }
        
        if (cont == 0){
            variable.add(new Variable(id,"ident", cadena, this.tipo));
            id++;
            return "exito";
        }else{
            return "invalido";
        }
    }

    public ArrayList<Variable> getVariable() {
        return variable;
    }

    public boolean isEsVariableAun() {
        return esVariableAun;
    }

    public void setEsVariableAun(boolean esVariableAun) {
        this.esVariableAun = esVariableAun;
    }

    private boolean buscarPalabraEnVariables(String cadenaV) {       
        for(int p=0; p<variable.size(); p++){
            if(variable.get(p).getVariable().equals(cadenaV)){
                this.aux = new Variable(variable.get(p).getId(), variable.get(p).getValor(), variable.get(p).getVariable(), variable.get(p).getTipo());
                return true;
            }
        }    
        return false;
    }  
    
}
