/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.intermedio;

import java.util.Stack;

/**
 *
 * @author kevin
 */
public class Triplos {
    //Declaracion de la pila para evaluar expresiones en posfijo
    Stack Pila_Triplos=new Stack();
    int Indice_Var_Temp=1;
    
    public static boolean isNumeric(String str){
        try{
            double d=Double.parseDouble(str);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
    
    public void Evaluar_Expresion(String Op1,String Op2,char Operador){
        double N1=0,N2=0;
        double R=0;
        double R2=0;
        N1=Double.parseDouble(Op1);
        N2=Double.parseDouble(Op2);
        switch(Operador){
            case '+':R=N1+N2;Pila_Triplos.push(R);break;
            case '-':R=N1-N2;Pila_Triplos.push(R);break;
            case '*':R=N1*N2;Pila_Triplos.push(R);break;
            case '/':R2=N1/N2;Pila_Triplos.push(R2);break;
            case '^':R=Math.pow(N1, N2);Pila_Triplos.push(R);break;
            default:break;
        }
    }
    
    public void Ejecutar_Operacion(char Operador, String Var_Temp_Triplo){
        //Esta funcion extraera 2 operandos y los evaluara
        String Op1="",Op2="",Resultado="";
        Op2=Pila_Triplos.pop()+"";
        Op1=Pila_Triplos.pop()+"";
        if (isNumeric(Op1)&&isNumeric(Op2)) {
            //Se manda llamar la funcion para evaluar la operacion
            Evaluar_Expresion(Op1,Op2,Operador);
        }else{
            Var_Temp_Triplo+=Indice_Var_Temp++;
            Resultado=Var_Temp_Triplo+"="+Op1+Operador+Op2;
            System.out.println(Resultado);
            //Agregamos  la pila la viable temporal
            Pila_Triplos.push(Var_Temp_Triplo);
        }
        //Por ultimo debemos extraer el resultado final de la pila que es 
        //el que se debera de asignar a la variable de asignacion original.
        //Esta accion es un cuadroplo, por el momento solo la imprimimos
    }
    
    public void Triplos(String Posfijo){
        //Esta funcion recibe una la expresion en posfijo y hace la separacion de los triplos
        String Cadena="",Var_Temp="_-T";
        char Caracter;
        int Indice=0,Longitud_Cadena=0;
        
        Longitud_Cadena=Posfijo.length();
        while(Indice<Longitud_Cadena){
            Caracter=Posfijo.charAt(Indice);
            switch(Caracter){
                case'+':Ejecutar_Operacion(Caracter,Var_Temp);Indice++;break;
                case'-':Ejecutar_Operacion(Caracter,Var_Temp);Indice++;break;
                case'*':Ejecutar_Operacion(Caracter,Var_Temp);Indice++;break;
                case'/':Ejecutar_Operacion(Caracter,Var_Temp);Indice++;break;
                case'^':Ejecutar_Operacion(Caracter,Var_Temp);Indice++;break;
                        //Se manda a evaluar
                case',':
                    if (!Cadena.equals("")) {
                        Pila_Triplos.push(Cadena);
                        Cadena="";
                    }
                    Indice++;
                    break;
                default:
                    Cadena+=Caracter;
                    Indice++;
            }
        }
        System.out.println("Resultado final: "+Pila_Triplos.pop());
    }
}