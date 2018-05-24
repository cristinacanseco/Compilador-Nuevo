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
public class Sintactico4 {
    Lexico lex;
    ListaError le = new ListaError();
    String error2 = le.getError().get(2).getMsj();
    String error3 = le.getError().get(3).getMsj();
    String error4 = le.getError().get(4).getMsj();
    String error5 = le.getError().get(5).getMsj();
   
    ArrayList<String> palabras;
    int pos, error, llave, inst, instPermiso;
    long tiempoT;
    long startTime;
    
    public Sintactico4(Lexico lex){
        this.lex = lex;
        this.palabras = new ArrayList();
        this.pos = 0;
        this.error =0;
        this.tiempoT =0;
        this.startTime =0;
        this.inst =0;
        this.instPermiso = 0;
        this.llave = 0;
        generarPalabras();
        System.out.println("");
    }
    
    public void Programa(){
        startTime = System.currentTimeMillis();
        Var();
        Fijo();
        Instrucciones();
        calcularError();
    }
    
    public void calcularError(){
        tiempoT = (System.currentTimeMillis()- startTime);
         if(error>0){
            System.out.println("Se encontraron "+ error + " error(es). (Tiempo total: " +tiempoT + " segundos)" );
        }else{
            System.out.println("CONSTRUIDO CORRECTAMENTE (Tiempo total: " +tiempoT + " segundos)" );
        }
    }
    
    public void errorEncontrado(String e, String mensaje){
        error++;
        System.out.println(e + mensaje);
        calcularError();
        System.exit(0);
    }

    private void generarPalabras() {
        String token = lex.lexicoR();
        if(token.equals("soyVacio")){
            palabras.add(token);
        }else{
            while(!token.equals("noMas")){  
                palabras.add(token);  
                token = lex.lexicoR();
            }
            palabras.add("noMas"); 
        }
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }
    
    private void Var() {
        if(pos < palabras.size() && !palabras.get(pos).equals("soyVacio") ){
            if(!palabras.get(pos).equals("Fijo") && !palabras.get(pos).equals("Itera") && !palabras.get(pos).equals("Ja") && !palabras.get(pos).equals("ident") && !palabras.get(pos).equals("Funcion") && !palabras.get(pos).equals("Ven") && !palabras.get(pos).equals("{") && pos < palabras.size() && !palabras.get(pos).equals("noMas") ){
                AuxVar2();
                if(pos < palabras.size() && palabras.get(pos).equals("ident") ){
                    pos++;
                    AuxVar();
                    if(pos < palabras.size() && palabras.get(pos).equals(";)") ){
                        pos++;
                        Var();
                    }else{
                        errorEncontrado(error3, ";)");
                    }
                }else{
                    errorEncontrado(error3, "un identificador");
                }
            }
        }else{
            errorEncontrado(error5, ""+pos);
        }
    }

    private void AuxVar2() {
        if(palabras.get(pos).equals("Ent") || palabras.get(pos).equals("Dec") || palabras.get(pos).equals("Let") ){
            pos++;
        }else{
            errorEncontrado(error3, "Ent, Dec o Let");
        }
    }

    private void AuxVar() {
        if(!palabras.get(pos).equals(";)") ){
            if(palabras.get(pos).equals(",") ){
               pos++;
               if(palabras.get(pos).equals("ident") ){
                   pos++;
                   AuxVar();
               }else{
                    errorEncontrado(error3, "un identificador");
               }
            }else{
                errorEncontrado(error3, ",");
            }
        }
    }

    private void Fijo() {
        if(pos < palabras.size() && !palabras.get(pos).equals("soyVacio") ){
            if(pos < palabras.size() && !palabras.get(pos).equals("Itera") && !palabras.get(pos).equals("Ja") && !palabras.get(pos).equals("ident") && !palabras.get(pos).equals("Funcion") && !palabras.get(pos).equals("Ven") && !palabras.get(pos).equals("{") && !palabras.get(pos).equals("noMas") ){
                if(pos < palabras.size() && palabras.get(pos).equals("Fijo") ){
                    pos++;
                    if(pos < palabras.size() && palabras.get(pos).equals("ident") ){
                        pos++;
                        if(pos < palabras.size() && palabras.get(pos).equals("=") ){
                            pos++;
                            AuxFijo();
                            AuxFijo2();
                            if(pos < palabras.size() && !palabras.get(pos).equals(";)")){
                                errorEncontrado(error3, ";)");
                            }else{
                                pos++;
                                Fijo();
                            }
                        }else{
                            errorEncontrado(error3, "=");
                        }
                    }else{
                        errorEncontrado(error3, "identificador válido");
                    }
                }else{
                    errorEncontrado(error3, "Fijo");
                }
            }
        }else{
            errorEncontrado(error5, ""+pos);
        }
    }

    private void AuxFijo() {
        if(pos < palabras.size() && !palabras.get(pos).equals("ident") && !palabras.get(pos).equals("num") ){
            errorEncontrado(error3, "identificador o número válido");                    
        }else{
            pos++;
        }
    }

    private void AuxFijo2() {
        if(pos < palabras.size() && !palabras.get(pos).equals("soyVacio")){
            if(pos < palabras.size() && !palabras.get(pos).equals(";)")){
                if(pos < palabras.size() && palabras.get(pos).equals(",")){
                    pos++;
                    if(pos < palabras.size() && palabras.get(pos).equals("ident")){
                        pos++;
                        if(pos < palabras.size() && palabras.get(pos).equals("=")){
                            pos++;
                            AuxFijo();
                            AuxFijo2();
                        }
                    }else{
                        errorEncontrado(error3, "identificador válido");
                    }
                }else{
                    errorEncontrado(error3, ",");
                }
            }
        }else{
            errorEncontrado(error5, "");
        }
    }

    private void Instrucciones() {
        if(pos < palabras.size() && !palabras.get(pos).equals("soyVacio") ){
             if(pos < palabras.size() && !palabras.get(pos).equals("Fin") && !palabras.get(pos).equals("Kein") && !palabras.get(pos).equals("Nein") && !palabras.get(pos).equals(":)") && !palabras.get(pos).equals("}") && !palabras.get(pos).equals("noMas") ){
                 switch (palabras.get(pos)){
                    case "Itera":
                        inst++;
                        pos++;
                        if(pos < palabras.size() && palabras.get(pos).equals("ident")){
                            pos++;
                            if(pos < palabras.size() && palabras.get(pos).equals("=")){
                                pos++;
                                if(pos < palabras.size() && palabras.get(pos).equals("num")){
                                    pos++;
                                    if(pos < palabras.size() && palabras.get(pos).equals(":")){
                                        pos++;
                                        if(pos < palabras.size() && palabras.get(pos).equals("num")){
                                            pos++;
                                            if(pos < palabras.size() && palabras.get(pos).equals(":")){
                                                AuxFor();
                                                AuxFor2();
                                                if(pos < palabras.size() && !palabras.get(pos).equals("Fin")){                                                  
                                                    if(!palabras.get(pos).equals("noMas")){
                                                        errorEncontrado(error3, "Fin Itera");
                                                    }
                                                }else{
                                                    if(inst>0 && llave==0 && instPermiso ==0 &&!palabras.get(pos+1).equals("noMas")){
                                                        errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                                    }else{
                                                        if(llave==0 && instPermiso == 0 && !palabras.get(pos+1).equals("noMas")){
                                                            errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                                        }else{
                                                            pos++;
                                                        }
                                                    }
                                                }
                                            }else{
                                                if(!palabras.get(pos).equals("noMas")){ 
                                                    errorEncontrado(error3, ":");
                                                }
                                            }
                                        }else{
                                            if(!palabras.get(pos).equals("noMas")){ 
                                                errorEncontrado(error3,"un número");
                                            }
                                        }
                                    }else{
                                        if(!palabras.get(pos).equals("noMas")){
                                            errorEncontrado(error3, ":");
                                        }
                                    }
                                }else{
                                    if(!palabras.get(pos).equals("noMas")){ 
                                        errorEncontrado(error3, "un número");
                                    }
                                }
                            }else{
                                if(!palabras.get(pos).equals("noMas")){ 
                                    errorEncontrado(error3, "=");
                                }
                            }
                        }else{
                            if(!palabras.get(pos).equals("noMas")){ 
                                errorEncontrado(error3, "identificador válido");
                            }
                        }
                        break;
                    case "Funcion":
                        inst++;
                        pos++;
                        if(palabras.get(pos).equals("ident")){
                            pos++;
                            if(palabras.get(pos).equals(":")){
                                pos++;
                                instPermiso++;
                                Instrucciones();
                                //pos++;
                                if(palabras.get(pos).equals("Fin")){
                                    if(inst>0 && llave==0 && instPermiso ==0 &&!palabras.get(pos+1).equals("noMas")){
                                        errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                       }else{
                                        if(llave==0 && instPermiso == 0 && !palabras.get(pos+1).equals("noMas")){
                                            errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                        }else{
                                            pos++;
                                        }
                                    }
                                }else{
                                    if(!palabras.get(pos).equals("noMas")){ 
                                        errorEncontrado(error3, "Fin");
                                    }
                                }
                            }else{
                                if(!palabras.get(pos).equals("noMas")){
                                    errorEncontrado(error3, ":");
                                }
                            }
                        }else{
                            if(!palabras.get(pos).equals("noMas")){
                                errorEncontrado(error3, "identificador válido");
                            }
                        }
                        break;
                    case "Ven":
                        inst++;
                        pos++;
                        if(palabras.get(pos).equals("ident")){
                            pos++;
                            if(palabras.get(pos).equals(";)")){
                                if(inst>0 && llave==0 && instPermiso ==0 &&!palabras.get(pos+1).equals("noMas")){
                                    errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                }else{
                                    if(llave==0 && instPermiso == 0 && !palabras.get(pos+1).equals("noMas")){
                                        errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                    }else{
                                        pos++;
                                    }
                                }
                            }else{
                                if(!palabras.get(pos).equals("noMas")){ 
                                    errorEncontrado(error3, ";) ven");
                                }
                            }
                        }else{   
                            if(!palabras.get(pos).equals("noMas")){ 
                                errorEncontrado(error3, "identificador válido");
                            }
                        }
                        break;
                    case "Ja":
                        inst++;
                        pos++;
                        Condicion();
                        if(palabras.get(pos).equals("to")){
                            pos++;
                            instPermiso++;
                            Instrucciones();
                            //pos++;
                            AuxIf();
                           
                        }else{
                            errorEncontrado(error3, "to");
                        }
                        break;
                    case "ident":
                        inst++;
                        pos++;
                        if(palabras.get(pos).equals("=")){
                            Expresion();
                            if(palabras.get(pos).equals(";)")){
                                if(inst>0 && llave==0 && instPermiso ==0 &&!palabras.get(pos+1).equals("noMas")){
                                    errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                }else{
                                    if(llave==0 && instPermiso==0 && !palabras.get(pos+1).equals("noMas") ){
                                        errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                    }else{
                                        pos++;
                                    }
                                }
                            }else{
                                if(!palabras.get(pos).equals("noMas")){
                                    errorEncontrado(error3, ";)");
                                
                                }
                            }
                            
                        }else{
                            if(!palabras.get(pos).equals("noMas")){ 
                                errorEncontrado(error3, "= ident");
                            }
                        }
                        break;
                        
                     case "{":
                        pos++;
                        inst++;
                        llave++;
                        Instrucciones();
                        AuxInst();                     
                        if(!palabras.get(pos).equals("}")){
                            errorEncontrado(error3, "}");
                        }
                        
                        break;    
                    default:
                        if(!palabras.get(pos).equals("noMas"))
                                errorEncontrado(error3, "una instrucción. Se encontró "+ palabras.get(pos));
                        
                        break;
                }
             }else{
                 if(!palabras.get(pos).equals("noMas"))
                    errorEncontrado(error2, ". Se encontró "+ palabras.get(pos));
             }
        
            
            
        }else{
            errorEncontrado(error5, "");
        }
    }
    
    public void AuxFor(){
      pos++;
        if(!palabras.get(pos).equals("Mas") && !palabras.get(pos).equals("Menos")){
            errorEncontrado(error3, "Mas o Menos");
        }
    }
    
    public void AuxFor2(){
      pos++;
        if(!palabras.get(pos).equals("Fin")){
            instPermiso++;
            Instrucciones();
        }
    }
    
    public void AuxIf(){
        if(!palabras.get(pos).equals("soyVacio")){
            if( !palabras.get(pos).equals("Itera") && !palabras.get(pos).equals("Ja") && !palabras.get(pos).equals("ident") && !palabras.get(pos).equals("Funcion") && !palabras.get(pos).equals("Ven") && !palabras.get(pos).equals("{") && !palabras.get(pos).equals(":)")  ){
                if(palabras.get(pos).equals("Kein")){
                    pos++;
                    Condicion();
                    if(palabras.get(pos).equals("to")){
                      pos++;
                      instPermiso++;
                        Instrucciones();
                        //token = lex.lexicoR();
                        if(!palabras.get(pos).equals("Nein")){
                            errorEncontrado(error3,"Nein");                                                       
                        }else{
                             if(inst>0 && llave==0 && instPermiso ==0 &&!palabras.get(pos+1).equals("noMas")){
                                errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                            }else{
                                if(llave==0 && instPermiso == 0 && !palabras.get(pos+1).equals("noMas")){
                                    errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
                                }else{
                                    pos++;
                                }
                            }
                        }
                    }else{
                        if(!palabras.get(pos).equals("noMas")){ 
                            errorEncontrado(error3, "to");
                        }
                    }
                }else{
                    if(!palabras.get(pos).equals("noMas")){ 
                        errorEncontrado(error3, "Kein");
                    }
                }
            }
        }
        
        
        
    }
    
    public void Condicion(){
        AuxFijo();
        AuxCond2();
        AuxFijo();
        AuxCond3();
    }
    
    public void AuxCond2(){
        
        if(!palabras.get(pos).equals("<") && !palabras.get(pos).equals(">") && !palabras.get(pos).equals(">=") && !palabras.get(pos).equals("<=") && !palabras.get(pos).equals("!=") && !palabras.get(pos).equals("==")){
            if(!palabras.get(pos).equals("noMas")){
                errorEncontrado(error3, "<, >, <=, >=, !=, ==");
            }
        }else{
            pos++;
        }
    }
    
    public void AuxCond3(){
        if(!palabras.get(pos).equals("noMas")){
            if(!palabras.get(pos).equals("num") && !palabras.get(pos).equals("ident") && !palabras.get(pos).equals("to") ){
                if(palabras.get(pos).equals("&&") || palabras.get(pos).equals("||") ){
                    pos++;
                    AuxCond4();
                }else{
                    if(!palabras.get(pos).equals("noMas")){
                        errorEncontrado(error3,"&& o || ");
                    }
                }
            } 
        }
        
    }
    
    public void AuxCond4(){
        if(!palabras.get(pos).equals("noMas")){
            if(!palabras.get(pos).equals("to")){
                Condicion();
                AuxCond3();
            }
        }
        
    }
    
    public void Expresion(){
      pos++;
        if(!palabras.get(pos).equals(")") && !palabras.get(pos).equals(";)") && !palabras.get(pos).equals("noMas") ){        
            AuxExpre();
            AuxExpre2();
        }
    }
    
    public void AuxExpre(){
        if(palabras.get(pos).equals("num") || palabras.get(pos).equals("ident")){}
        else{
            if(palabras.get(pos).equals("(")){
                Expresion();
                //token = lex.lexicoR();
                if(!palabras.get(pos).equals(")")){
                    errorEncontrado(error3, ")");
                   
                }
            }else{
                if(!palabras.get(pos).equals("noMas")){ 
                    errorEncontrado(error3, "número, identificador o (");
                    
                }
                
            }
        }
    }
    
    public void AuxExpre2(){
      pos++;
        if(!palabras.get(pos).equals(")") && !palabras.get(pos).equals(";)") && !palabras.get(pos).equals("noMas")){        
            AuxExpre3();
            Expresion();
            if(!palabras.get(pos).equals(")") && !palabras.get(pos).equals(";)") && !palabras.get(pos).equals("noMas") ){        
             AuxExpre2();
            }          
        }
    }
    
    public void AuxExpre3(){
        if(!palabras.get(pos).equals("soyVacio")){
            if(!palabras.get(pos).equals("+") && !palabras.get(pos).equals("-") && !palabras.get(pos).equals("*") && !palabras.get(pos).equals("/")){
                if(!palabras.get(pos).equals("noMas")){
                    errorEncontrado(error3,"+,*,-,/");           
                }        
            }
        }      
    }
   
    public void AuxInst(){
        if(!palabras.get(pos).equals("}")){     
            if(palabras.get(pos).equals(":)")){
                pos++;
                instPermiso++;
                Instrucciones();
                AuxInst();
            }
        }
    }
}
