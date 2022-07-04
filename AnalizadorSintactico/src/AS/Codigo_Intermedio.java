package AS;

import java.util.Stack;

/**
 *
 * @author Leo E Itzi
 */
public class Codigo_Intermedio {
    Stack pila=new Stack();
    public int prioridad_operador(char operador){
        int prioridad = 0;
        switch(operador){
            case '+':
                prioridad=1;
                break;
            case '-':
                prioridad=1;
                break;
            case '*':
                prioridad=2;
                break;
            case '/':
                prioridad=2;
                break;
            case '^':
                prioridad=3;
                break;
            default:
                prioridad=-1;
                break;
        }
        return prioridad;
    }
    
    public String Vaciar_Pila(){
        String Resto_Pila="";
        while(!pila.isEmpty()){
            Resto_Pila+=pila.pop()+",";
        }
        return Resto_Pila;
    }
    
    public String posfijo(String infijo){
        //Esta funcion recibe una expresion en infijo y regresa la expresion en posfijo
        char caracter;
        int indice=0, longitud=0;
        String expresion_posfijo="", cadena="";
        
        longitud=infijo.length();
        
        while(indice<longitud){
            caracter=infijo.charAt(indice);
            switch(caracter){
                case ';':
                    if (!cadena.isEmpty()) {
                        expresion_posfijo+=cadena+",";
                        cadena="";
                        indice++;
                    }
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                case '(':
                case ')':// aqui va la operacion de la pila
                    //Primero pasamos la variable o constante a la expresionde posfijo
                    if (!cadena.isEmpty()) {
                        expresion_posfijo+=cadena+",";
                    }
                    
                    cadena="";
                    indice++;
                    if(pila.isEmpty()){
                        pila.push(caracter);
                    }else{
                        if ((caracter!='(')&&(caracter!=')')) {
                            //Analizamos si es +,-,*,/ o ^
                            pila.lastElement();
                            if ((prioridad_operador(caracter)>prioridad_operador((char)pila.lastElement()))) {
                                pila.push(caracter);
                            }else{
                                expresion_posfijo+=pila.pop()+",";
                                pila.push(caracter);
                            }
                        }else{
                            if (caracter=='(') {
                                pila.push(caracter);
                            }else{
                                while((char)pila.lastElement()!='('){
                                    expresion_posfijo+=pila.pop()+",";
                                }
                                pila.pop();
                            }
                        }
                    }
                    break;
                default:
                    cadena+=caracter;
                    indice++;
                    break;
            }
        }
        //Por ultimo nececitamos vaciar la pila y agregarlo a expresion_posfijo
        System.out.println(expresion_posfijo+=Vaciar_Pila());
        return expresion_posfijo+=Vaciar_Pila();
    }
}
