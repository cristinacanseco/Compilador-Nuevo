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
    String error6 = le.getError().get(6).getMsj();
    String error7 = le.getError().get(7).getMsj();
    String error8 = le.getError().get(8).getMsj();
    String error9 = le.getError().get(9).getMsj();
    String error10 = le.getError().get(10).getMsj();
    String error11 = le.getError().get(11).getMsj();
   
    ArrayList<String> palabras,identificadores;
    ArrayList<Variable> variables, funciones;
    
    int pos, error, llave, inst, instPermiso, numTipo, ip;
    long tiempoT, startTime;
    
    String tipo1, tipo2;
    
    public Sintactico4(Lexico lex){
        this.lex = lex;
        this.palabras = new ArrayList();
        this.identificadores = new ArrayList<>();
        this.variables = new ArrayList<>();
        this.funciones = new ArrayList<>();
        this.pos = 0;
        this.ip = 0;
        this.tipo1 = "";
        this.tipo2 = "";
        this.error =0;
        this.tiempoT =0;
        this.startTime =0;
        this.inst =0;
        this.instPermiso = 0;
        this.llave = 0;
        this.numTipo = 0;
        generarPalabras();
        generarIdentificadores();
        this.variables = lex.getVariable();
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

     private void generarIdentificadores() {
        String token = lex.lexicoR2();
        if(token.equals("soyVacio")){
            identificadores.add(token);
        }else{
            while(!token.equals("noMas")){  
                identificadores.add(token);  
                token = lex.lexicoR2();
            }
            identificadores.add("noMas"); 
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
                    if(palabras.get(pos).equals("repetido")){
                        errorEncontrado(error6, "");
                    }else{
                        errorEncontrado(error3, "un identificador");
                    }
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
                   if(palabras.get(pos).equals("repetido")){
                        errorEncontrado(error6, "");
                    }else{
                        errorEncontrado(error3, "un identificador");
                    }
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
                        comparaTipo();
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
                        if(palabras.get(pos).equals("noEncontrada")){
                            errorEncontrado(error7,"");
                        }else{
                            errorEncontrado(error3, "identificador válido");
                        }
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
            if(palabras.get(pos).equals("noEncontrada")){
                 errorEncontrado(error7,"");
            }else{
                errorEncontrado(error3, "identificador o número válido");
            }
        }else{
           
            if(palabras.get(pos).equals("ident")){
                comparaTipo();
                if(!this.tipo1.equals(this.tipo2)){
                    if((this.tipo1.equals("Dec") && this.tipo2.equals("Ent"))  || (this.tipo2.equals("Dec") && this.tipo1.equals("Ent")) ){}else{
                        errorEncontrado(error8, identificadores.get(pos-2) + " y " + identificadores.get(pos) );
                    }
                }else{                    
                    this.tipo1 = "";
                    this.tipo2 = "";                    
                }
            }
            
            if(palabras.get(pos).equals("num")){
                if(this.tipo1.equals("Dec") || this.tipo1.equals("Ent")){
                    numTipo = 0;
                    this.tipo1 = "";
                    this.tipo2 = "";
                }else{
                    errorEncontrado(error8, identificadores.get(pos-2) + " y " + identificadores.get(pos));               
                }
            }
            
            pos++;
        }
    }

    private void AuxFijo2() {
        if(pos < palabras.size() && !palabras.get(pos).equals("soyVacio")){
            if(pos < palabras.size() && !palabras.get(pos).equals(";)")){
                if(pos < palabras.size() && palabras.get(pos).equals(",")){
                    pos++;
                    if(pos < palabras.size() && palabras.get(pos).equals("ident")){
                        comparaTipo();
                        
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
                            esIdentificador();
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
//                                                    if(inst>0 && llave==0 && instPermiso ==0 &&!palabras.get(pos+1).equals("noMas")){
//                                                        errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
//                                                    }else{
//                                                        if(llave==0 && instPermiso == 0 && !palabras.get(pos+1).equals("noMas")){
//                                                            errorEncontrado(error4, ". Se encontró "+ palabras.get(pos+1) + " pos: "+ pos);
//                                                        }else{
//                                                            pos++;
//                                                        }
//                                                    }
                                                    posibleError();
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
                                if(palabras.get(pos).equals("noEncontrada")){
                                    errorEncontrado(error7,"");
                                }else{
                                    errorEncontrado(error3, "identificador válido");
                                }
                            }
                        }
                        break;
                    case "Funcion":
                        inst++;
                        pos++;
                        if(palabras.get(pos).equals("noEncontrada")){
                            agregarFuncion(identificadores.get(pos));
                            pos++;
                            if(palabras.get(pos).equals(":")){
                                pos++;
                                instPermiso++;
                                Instrucciones();
                                //pos++;
                                if(palabras.get(pos).equals("Fin")){
                                    posibleError();                                 
                                }else{
                                    if(!palabras.get(pos).equals("noMas")) 
                                        errorEncontrado(error3, "Fin");
                                    
                                }
                            }else{
                                if(!palabras.get(pos).equals("noMas")){
                                    errorEncontrado(error3, ":");
                                }
                            }
                        }else{
                            if(!palabras.get(pos).equals("noMas")){
                                if(palabras.get(pos).equals("ident")){
                                    errorEncontrado(error2,"");
                                }else{
                                    errorEncontrado(error3, "identificador válido");
                                }
                            }
                        }
                        break;
                    case "Ven":
                        inst++;
                        pos++;
                        if(palabras.get(pos).equals("noEncontrada")){
                            llamaALaFuncion();
                            pos++;
                            if(palabras.get(pos).equals(";)")){
                                posibleError();
                                
                            }else{
                                if(!palabras.get(pos).equals("noMas")){ 
                                    errorEncontrado(error3, ";) ven");
                                }
                            }
                        }else{   
                            if(!palabras.get(pos).equals("noMas")){ 
                                if(palabras.get(pos).equals("ident")){
                                    errorEncontrado(error3,"una función");
                                }else{
                                    errorEncontrado(error3, "identificador válido");
                                }
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
                        numTipo = 0;
                        comparaTipo();                       
                        inst++;
                        pos++;
                        if(palabras.get(pos).equals("=")){
                            Expresion();
                            if(palabras.get(pos).equals(";)")){
                                posibleError();
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
                        if(!palabras.get(pos).equals("Nein")){
                            errorEncontrado(error3,"Nein");                                                       
                        }else{                           
                             posibleError();
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
        AuxCond();
        AuxCond2();
        AuxCond();
        AuxCond3();
    }
    
    private void AuxCond() {
        if(pos < palabras.size() && !palabras.get(pos).equals("ident") && !palabras.get(pos).equals("num") ){
            if(palabras.get(pos).equals("noEncontrada")){
                 errorEncontrado(error7,"");
            }else{
                errorEncontrado(error3, "identificador o número válido");
            }
        }else{
           
            if(palabras.get(pos).equals("ident")){
                esIdentificador();
            }
            
            pos++;
        }
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
        if(palabras.get(pos).equals("num") || palabras.get(pos).equals("ident")){
            comparaTipo();
            if(palabras.get(pos).equals("ident")){
                comparaTipo();
                if(!this.tipo1.equals(this.tipo2) && (!this.tipo1.equals("Dec") || !this.tipo2.equals("Ent")) ){
                    errorEncontrado(error8, identificadores.get(pos-2) + " y " + identificadores.get(pos) );
                }else{           
                    this.tipo1 = "";
                    this.tipo2 = "";
                    
                }
            }
            
            if(palabras.get(pos).equals("num")){
                if(this.tipo1.equals("Dec") || this.tipo1.equals("Ent")){
                    numTipo = 0;
                    this.tipo1 = "";
                    this.tipo2 = "";
                }else{
                    errorEncontrado(error8, identificadores.get(pos-2) + " y " + identificadores.get(pos));               
                }
            }
            
        }
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

    private void comparaTipo() {
        numTipo++;
        String tipo = "";
         if(numTipo > 2)
            numTipo = 1;
         
        for(int i=0; i<this.variables.size(); i++){
            if(identificadores.get(pos).equals(variables.get(i).getVariable()))
                tipo = variables.get(i).getTipo();
        }
        
        if (numTipo == 1)
            tipo1 = tipo;
        
        if(numTipo == 2)
            tipo2 = tipo;
    }

    private void esIdentificador() {
        numTipo = 0;
        comparaTipo();
        if(this.tipo1.equals("Dec") || this.tipo1.equals("Ent")){
        }else{
            errorEncontrado(error9, identificadores.get(pos));
        }
    }

    private void posibleError() {
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

    private void llamaALaFuncion() {
        int cont =0;
    
        for(int k=0; k<funciones.size(); k++ ){
            if(funciones.get(k).getVariable().equals(identificadores.get(pos)))
                cont++;
        }
        
        if(cont == 0){
            errorEncontrado(error10, variables.get(pos).getVariable());
        }
    }
    
    public void agregarFuncion(String cadena){
        
        for (int h= 0; h<funciones.size(); h++){
            if(funciones.get(h).getVariable().equals(cadena)){
                errorEncontrado(error11, cadena);
                break;
            }
        }
        
        for (int hh= 0; hh<variables.size(); hh++){
            if(variables.get(hh).getVariable().equals(cadena)){
                errorEncontrado(error2, cadena + ", fue declarado como variable");
                break;
            }
        }
        
        funciones.add(new Variable(ip,"ident", cadena, "Funcion"));
        ip++;
        
    }
    
}
