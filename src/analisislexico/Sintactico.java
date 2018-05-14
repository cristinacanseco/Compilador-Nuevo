/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisislexico;

/**
 *
 * @author Cristy
 */
public class Sintactico {
    Lexico lex;
    ListaError le = new ListaError();
    String error2 = le.getError().get(2).getMsj();
    String error3 = le.getError().get(3).getMsj();
    String token;
    long tiempoT=0;
    long startTime =0;
    int e=0;
    
    public Sintactico(Lexico lex){
        this.lex = lex;
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
         if(e>0){
            System.out.println("Se encontraron "+ e + " error(es). (Tiempo total: " +tiempoT + " segundos)" );
        }else{
            System.out.println("CONSTRUIDO CORRECTAMENTE (Tiempo total: " +tiempoT + " segundos)" );
        }
    }
    
    public void Var(){
        token = lex.lexicoR();
        //System.out.println("1 "+token);
        if(!token.equals("soyVacio")){
            if(!token.equals("Fijo") && !token.equals("Itera") && !token.equals("Ja") && !token.equals("ident") && !token.equals("Ven") && !token.equals("Funcion") && !token.equals("{")){
                if(token.equals("Ent") || token.equals("Dec") || token.equals("Let")){
                    token = lex.lexicoR();
                    //System.out.println("2 "+token);
                    if(token.equals("ident")){
                        AuxVar();
                        //token = lex.lexicoR();
                        //System.out.println("3 "+token);
                        if(!token.equals(";)")){
                            if(!token.equals("noMas")){
                                System.out.println(error3 + ";)");
                                e++;
                            }                                
                        }else{
                            Var();
                        }
                    }else{
                        if(!token.equals("noMas")){
                            System.out.println(error3+ "un identificador válido");
                            e++;
                        }
                    }
                }else{
                    if(!token.equals("noMas")){
                        e++;
                        System.out.println(error3 + "Ent, Dec o Let");
                    }
                }
            }
        }else{
            e++;
            System.out.println(error3 + ":)");
        }
        
    }
    
    public void AuxVar(){
        token = lex.lexicoR();
        //System.out.println("4 "+token);
        if(!token.equals("soyVacio")){
            if(!token.equals(";)")){
                if(token.equals(",")){
                    token = lex.lexicoR();
                    //System.out.println("5 "+token);
                    if(token.equals("ident") ){
                        AuxVar();
                    }else{
                        if(!token.equals("noMas")){
                            e++;
                            System.out.println(error3 + "un identificador válido");
                        }
                    }
                }else{
                    if(!token.equals("noMas")){
                        e++;
                        System.out.println(error3 + ",");
                    }
                }
            }
        }
        
    }
    
    public void Fijo(){
        //token = lex.lexicoR(); 
         if(!token.equals("soyVacio")){
            if(!token.equals("Itera") && !token.equals("Ja") && !token.equals("ident") && !token.equals("Funcion") && !token.equals("Ven") && !token.equals("{")){
                if(token.equals("Fijo")){
                    token = lex.lexicoR();
                    if(token.equals("ident")){
                        token = lex.lexicoR();
                        if(token.equals("=")){
                            AuxFijo();
                            AuxFijo2();
                            if(!token.equals(";)")){
                                if(!token.equals("noMas")){
                                    e++;
                                    System.out.println(error3+";) Fijo");
                                }
                            }else{
                                token = lex.lexicoR();
                                Fijo(); 
                            }
                        }else{
                            if(!token.equals("noMas")){
                                e++;
                                System.out.println(error3+"= Fijo");
                            }
                        }
                    }else{
                        if(!token.equals("noMas")){
                            e++;
                            System.out.println(error3+"un identificador válido");
                        }    
                    }
                }else{
                    if(!token.equals("noMas")){
                        e++;
                        System.out.println(error3+"Fijo");
                    }
                }
            }
        }else{
            e++;
            System.out.println(error3 + ":)");
        }
    }
    
    public void AuxFijo(){
        token = lex.lexicoR();
        if(!token.equals("soyVacio")){
            if(!token.equals("num") && !token.equals("ident")){
                if(!token.equals("noMas")){
                    e++;
                    System.out.println(error3+" un número o identificador válido!");
                }
            }
        }
    }
    
    public void AuxFijo2(){
        token = lex.lexicoR();
        
        if(!token.equals("soyVacio")){
            if(!token.equals(";)")){
                if(token.equals(",")){
                    token  = lex.lexicoR();
                    if(token.equals("ident")){
                        token = lex.lexicoR();
                        if(token.equals("=")){
                            AuxFijo();
                            AuxFijo2();
                        }
                    }else{
                        if(!token.equals("noMas")){
                            e++;
                            System.out.println(error3+"ident");
                        }             
                    }
                }else{
                    if(!token.equals("noMas")){
                        e++;
                        System.out.println(error3+",");
                    }         
                }
            }
        }else{
            System.out.println("Programa Correcto");
        }
        
    }
    
    public void AuxInst(){
        token = lex.lexicoR();
        if(!token.equals("}")){     
            if(token.equals(";)")){
                Instrucciones();
                AuxInst();
            }
        }
    }
    
    public void Instrucciones(){
         if(!token.equals("soyVacio")){
             if(!token.equals("Fin") && !token.equals("Kein") && !token.equals("Nein") && !token.equals(":)") && !token.equals("}")){
                switch (token){
                    case "Itera":
                        token = lex.lexicoR();
                        if(token.equals("ident")){
                            token = lex.lexicoR();
                            if(token.equals("=")){
                                token = lex.lexicoR();
                                if(token.equals("num")){
                                    token = lex.lexicoR();
                                    if(token.equals(":")){
                                        token = lex.lexicoR();
                                        if(token.equals("num")){
                                            token = lex.lexicoR();
                                            if(token.equals(":")){
                                                AuxFor();
                                                AuxFor2();
                                                if(!token.equals("Fin")){                                                  
                                                    if(!token.equals("noMas")){
                                                        e++;
                                                         System.out.println(error3+"Fin");
                                                    }
                                                }else{
                                                    token = lex.lexicoR();
                                                    Instrucciones();
                                                }
                                            }else{
                                                if(!token.equals("noMas")){ e++;
                                                    System.out.println(error3+":");
                                                }
                                            }
                                        }else{
                                            if(!token.equals("noMas")){ e++;
                                                System.out.println(error3+"número");
                                            }
                                        }
                                    }else{
                                        if(!token.equals("noMas")){ e++;
                                            System.out.println(error3+":");
                                        }
                                    }
                                }else{
                                    if(!token.equals("noMas")){ e++;
                                        System.out.println(error3+"número");
                                    }
                                }
                            }else{
                                if(!token.equals("noMas")){ e++;
                                    System.out.println(error3+"= Itera"
                                            + "");
                                }
                            }
                        }else{
                            if(!token.equals("noMas")){ e++;
                                System.out.println(error3+"identificador válido");
                            }
                        }
                        break;
                    case "Funcion":
                        token = lex.lexicoR();
                        if(token.equals("ident")){
                            token = lex.lexicoR();
                            if(token.equals(":")){
                                token = lex.lexicoR();
                                Instrucciones();
                                //token = lex.lexicoR();
                                if(token.equals("Fin")){
                                    token = lex.lexicoR();
                                    Instrucciones();
                                }else{
                                    if(!token.equals("noMas")){ e++;
                                        System.out.println(error3+"Fin");
                                    }
                                }
                            }else{
                                if(!token.equals("noMas")){ e++;
                                    System.out.println(error3+":");
                                }
                            }
                        }else{
                            if(!token.equals("noMas")){ e++;
                                System.out.println(error3+"identificador válido");
                            }
                        }
                        break;
                    case "Ven":
                        token = lex.lexicoR();
                        if(token.equals("ident")){
                            token = lex.lexicoR();
                            if(token.equals(";)")){
                                token = lex.lexicoR();
                                Instrucciones();
                            }else{
                                if(!token.equals("noMas")){ e++;
                                    System.out.println(error3+";) ven");
                                }
                            }
                        }else{   
                            if(!token.equals("noMas")){ e++;
                                System.out.println(error3+"identificador válido");
                            }
                        }
                        break;
                    case "Ja":
                        Condicion();
                        if(token.equals("to")){
                            token = lex.lexicoR();
                            Instrucciones();
                            AuxIf();
                            token = lex.lexicoR();
                            Instrucciones();
                        }
                        break;
                    case "ident":
                        token = lex.lexicoR();
                        if(token.equals("=")){
                            Expresion();
                            if(token.equals(";)")){
                                token = lex.lexicoR();
                                Instrucciones();
                            }else{
                                if(!token.equals("noMas")){ e++;
                                System.out.println(error3+";)");
                                }
                            }
                            
                        }else{
                            if(!token.equals("noMas")){ e++;
                                System.out.println(error3+"= ident");
                            }
                        }
                        break;
                        
                     case "{":
                        token = lex.lexicoR();
                        Instrucciones();
                        AuxInst();
                        
                        if(!token.equals("}")){
                            if(!token.equals("noMas")){ e++;
                                System.out.println(error3+" }");
                            }
                            
                        }
                        break;    
                    default:
                        if(!token.equals("noMas")){ e++;
                                System.out.println(error2);
                        }
                        
                        break;
                }
             }
        }else{
            e++;
            System.out.println(error3 + ":)");
        }
        
    }
    
    public void AuxFor(){
        token = lex.lexicoR();
        if(!token.equals("Mas") && !token.equals("Menos")){
            System.out.println(error3+"Mas o Menos");
        }
    }
    
    public void AuxFor2(){
        token = lex.lexicoR();
        if(!token.equals("Fin")){
            Instrucciones();
        }
    }
    
    public void AuxIf(){
        if(!token.equals("soyVacio")){
            if( !token.equals("Itera") && !token.equals("Ja") && !token.equals("ident") && !token.equals("Funcion") && !token.equals("Ven") && !token.equals("{") ){
                if(token.equals("Kein")){
                    Condicion();
                    if(token.equals("to")){
                        token = lex.lexicoR();
                        Instrucciones();
                        //token = lex.lexicoR();
                        if(!token.equals("Nein")){
                            if(token.equals("noMas")){ e++;
                                System.out.println(error3+"Nein");
                            }
                        }else{
                            token = lex.lexicoR();
                            Instrucciones();
                        }
                    }else{
                        if(!token.equals("noMas")){ e++;
                        System.out.println(error3+"to");
                        }
                    }
                }else{
                    if(!token.equals("noMas")){ e++;
                    System.out.println(error3+"Kein");
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
        AuxCond4();
    }
    
    public void AuxCond2(){
        token = lex.lexicoR();
        if(!token.equals("<") && !token.equals(">") && !token.equals(">=") && !token.equals("<=") && !token.equals("!=") && !token.equals("==")){
            if(!token.equals("noMas")){ e++;
                System.out.println(error3+"<, >, <=, >=, !=, ==");
            }
        }
    }
    
    public void AuxCond3(){
        token = lex.lexicoR();
        
        if(!token.equals("noMas")){
            if(!token.equals("num") && !token.equals("ident") && !token.equals("to") ){
                if(token.equals("&&") || token.equals("||") ){
                    
                }else{
                    if(!token.equals("noMas")){ e++;
                        System.out.println(error3+"&& o || ");
                    }
                }
            }   
        }
        
    }
    
    public void AuxCond4(){
        //token = lex.lexicoR();
        if(!token.equals("noMas")){
            if(!token.equals("to")){
                Condicion();
                AuxCond4();
            }
        }
        
    }
    
    public void Expresion(){
        token = lex.lexicoR();
        if(!token.equals(")") && !token.equals(";)") && !token.equals("noMas") ){        
            AuxExpre();
            AuxExpre2();
        }
    }
    
    public void AuxExpre(){
        //token = lex.lexicoR();
        
        if(token.equals("num") || token.equals("ident")){}
        else{
            if(token.equals("(")){
                Expresion();
                //token = lex.lexicoR();
                if(!token.equals(")")){
                    System.out.println(error3+")");
                }
            }else{
                if(!token.equals("noMas")){ e++;
                    System.out.println(error3+"número, identificador o (");
                }
                
            }
        }
    }
    
    public void AuxExpre2(){
        token = lex.lexicoR();
        if(!token.equals(")") && !token.equals(";)") && !token.equals("noMas")){        
            AuxExpre3();
            Expresion();
            if(!token.equals(")") && !token.equals(";)") && !token.equals("noMas") ){        
             AuxExpre2();
            }          
        }
    }
    
    public void AuxExpre3(){
       
        if(!token.equals("soyVacio")){
            if(!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")){
                if(!token.equals("noMas")){ e++;
                       System.out.println(error3+"+,*,-,/");
                }
                
            }
        }      
    }
   
}