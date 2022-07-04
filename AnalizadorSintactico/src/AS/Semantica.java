package AS;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Semantica {

    File f = new File("C:\\077CA\\Codigo_Intermedio.txt");
    Stack pila_Triplos = new Stack();
    int indice_var_temp = 1;
    static String Tabla_Simbolos[][] = new String[100][5];
    int indice_TS = 0;
    boolean dos = false;
    Codigo_Intermedio ci = new Codigo_Intermedio();
    String resultadoS = "", resultadoS2="", variable_result="", var_temp_triplos_glo="";
    int cont_varTemp=0, cont_Verifi_Triplo=0;
//funcion para buscar simbolos

    public String Buscar_Simbolo(String Simbolo) {
//esta funcion busca un simbolo o variable
//y si se encuentra retorna el tipo
//esta funcion la van a ocupar en el analisis
//de expresiones matematicas
        String Resultado = "";
        String dato = "";
        for (int i = 0; i < indice_TS; i++) {
            dato = Tabla_Simbolos[i][0];
            if (dato.equals(Simbolo)) {
                Resultado = Tabla_Simbolos[i][1];
            }
        }
        return Resultado;
    }

    public int Inserta_Simbolo(String Simbolo, String Tipo) {
        int Resultado = 0;
        int Tam = 0;
        if (Buscar_Simbolo(Simbolo) == "") {
            Tabla_Simbolos[indice_TS][0] = Simbolo;
            Tabla_Simbolos[indice_TS][1] = Tipo;
            switch (Tipo) {
                case "entero":
                    Tam = 4;
                    break;
                case "flotante":
                    Tam = 8;
                    break;
                case "caracter":
                    Tam = 1;
                    break;
            }
            Tabla_Simbolos[indice_TS][2] = Integer.toString(Tam);
            Tabla_Simbolos[indice_TS][4] = "false";
            indice_TS++;
            Resultado = 1;
        } else {
            Resultado = 0;
        }
        return Resultado;
    }

    public String Declaracion_Variables(String Linea, int Indice, String Tipo) {
        String Error = "";
        int Longitud_Linea = 0, Resultado_Inserta = 0;
        char Caracter = ' ';
        String Variable = "";
        Longitud_Linea = Linea.length();
        while (Indice < Longitud_Linea) {
            Caracter = Linea.charAt(Indice);
            if ((Caracter != ',') && (Caracter != ';')) {
                Variable += Caracter;
                Indice++;
            } else {
                Indice++;
                Resultado_Inserta = Inserta_Simbolo(Variable, Tipo);
                if (Resultado_Inserta == 0) {
                    Error += "La variable: " + Variable + " Ya esta declarada";
                }
                Variable = "";
            }
        }
        return Error;
    }

    public String Analizar_Linea(String Linea) {
        String Error = "";
        int Indice = 0, Longitud_Linea = 0, bandera = 0;
        char Caracter = ' ';
        String Token = "";
        Longitud_Linea = Linea.length();
        while (Indice < Longitud_Linea) {
            Caracter = Linea.charAt(Indice);
            if ((Caracter != ' ') && (Caracter != ':') && (Caracter != '{') && (Caracter != '<') && (Caracter != '>')) {
                Token += Caracter;
                Indice++;
            } else {
                if (Caracter == ':') {
                    Indice += 2;
                } else {
                    Indice++;
                }
                switch (Token) {
                    case "entero":
                    case "flotante":
                    case "caracter":
                        if (!dos) {
                            Error += Declaracion_Variables(Linea, Indice, Token);
                            bandera = 1;
                        }
                        break;
                    case "For":
                        break;
                    default:
                        if (!dos) {
                            Error += buscar(Token);
                            if (Error.equals("")) {
                                Error += divicion(Linea);
                            }
                        } else {
                            String Linea1="";
                            char listaC=' ';
                            int cont=0;
                            for (int i = 0; i < Linea.length(); i++) {
                                listaC=Linea.charAt(i);
                                if (listaC=='=' || listaC==':') {
                                    cont++;
                                }else if(cont==2){
                                    Linea1+=listaC+"";
                                }else if(cont==0 && Linea.contains("=")) {
                                    //AQUÍ SOLO GUARDAMOS LA VARIABLE QUE VA A RECIBIR EL VALOR DE LA OPERACIÓN MATEMATICA
                                    variable_result+=listaC+"";
                                }
                            }
                            String posfijo = ci.posfijo(Linea1);
                            Triplos(posfijo);
                        }
                        Token = "";
                }
            }
            if (bandera == 1) {
                break;
            }
        }
        return Error;
    }

    public String Ejecutar_Semantica(String Codigo_Fuente) {
        String Errores = "";
        int Indice_Cad = 0, Longitud_Cad = 0;
        char Caracter = ' ';
        String Linea = "";
        Longitud_Cad = Codigo_Fuente.length();
        while (Indice_Cad < Longitud_Cad) {
            Caracter = Codigo_Fuente.charAt(Indice_Cad);
            if (Caracter != '\n') {
                Linea += Caracter;
                Indice_Cad++;
            } else {
                Errores = Analizar_Linea(Linea);
                if (!Errores.equals("")) {
                    break;
                }
                Indice_Cad++;
                Linea = "";
            }
        }
        if (!dos) {
            if (Errores.equals("")) {
                Errores += buscar_Uso();
            } else {
            }
        }

        dos = true;
        return Errores;
    }

    private String buscar(String Token) {
        String Error = "";
        for (int i = 0; i <= indice_TS; i++) {
            if (Token.equals(Tabla_Simbolos[i][0])) {
                Tabla_Simbolos[i][4] = "true";
                Error = "";
                break;
            } else {
                Error = "La variable" + Token + " no ah sido declarada";
            }
        }
        return Error;
    }

    private String buscar_Uso() {
        String Error = "";
        for (int i = 0; i < indice_TS; i++) {
            if (Tabla_Simbolos[i][4].equals("false")) {
                Error += "La variable " + Tabla_Simbolos[i][0] + " no se esta utilizando\n";
            } else {
                Error += "";
            }
        }
        return Error;
    }

    public String divicion(String cadena) {
        String c1 = "", c2 = "", c3 = "", Error = "";
        int s = 0;
        if (cadena.contains("/")) {
            cadena = cadena.replace(":=", " ");
            cadena = cadena.replace("/", " ");
            cadena = cadena.replace(";", "");
            for (int i = 0; i < cadena.length(); i++) {
                if (cadena.charAt(i) != ' ') {
                    switch (s) {
                        case 0:
                            c1 = c1.concat(cadena.charAt(i) + "");
                            break;
                        case 1:
                            c2 = c2.concat(cadena.charAt(i) + "");
                            break;
                        case 2:
                            c3 = c3.concat(cadena.charAt(i) + "");
                            break;
                    }
                } else {
                    s++;
                }
            }
            for (int i = 0; i < indice_TS; i++) {
                if (Tabla_Simbolos[i][0].equals(c1)) {
                    if (Tabla_Simbolos[i][1].equals("flotante")) {
                        Error += "";
                    } else {
                        Error += "la variable " + c1 + " no es flotante\n";
                    }
                }
                if (Tabla_Simbolos[i][0].equals(c2)) {
                    if (Tabla_Simbolos[i][1].equals("flotante")) {
                        Error += "";
                    } else {
                        Error += "la variable " + c2 + " no es flotante\n";
                    }
                }
                if (Tabla_Simbolos[i][0].equals(c3)) {
                    if (Tabla_Simbolos[i][1].equals("flotante")) {
                        Error += "";
                    } else {
                        Error += "la variable " + c3 + " no es flotante\n";
                    }
                }
            }
        }
        return Error;
    }

    
    //Triplos
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void evaluar_expresion(String op1, String op2, char operador) {
        double N1 = 0, N2 = 0;
        double resultado = 0;
        double R2 = 0;
        N1 = Double.parseDouble(op1);
        N2 = Double.parseDouble(op2);
        switch (operador) {
            case '+':
                resultado = N1 + N2;
                pila_Triplos.push(resultado);
                break;
            case '-':
                resultado = N1 - N2;
                pila_Triplos.push(resultado);
                break;
            case '*':
                resultado = N1 * N2;
                pila_Triplos.push(resultado);
                break;
            case '/':
                R2 = N1 / N2;
                pila_Triplos.push(R2);
                break;
            case '^':
                resultado = Math.pow(N1, N2);
                pila_Triplos.push(resultado);
                break;
            default:
                break;
        }
        var_temp_triplos_glo=resultado+"";
    }

    public void ejecutar_operacion(char operador, String var_temp_triplo) {
        //Esta función extraerá dos operandos y los evaluará
        String op1 = "", op2 = "";

        op2 = pila_Triplos.pop() + "";
        op1 = pila_Triplos.pop() + "";
        if (isNumeric(op1) && isNumeric(op2)) {
            //Mandamos llamar la funcion para evaluar la op.
            evaluar_expresion(op1, op2, operador);
        } else {
            cont_Verifi_Triplo++;
            var_temp_triplo += indice_var_temp;
            Tabla_Simbolos[indice_TS][1] = "flotante";
            Tabla_Simbolos[indice_TS][0] = var_temp_triplo;
            indice_TS++;
            indice_var_temp++;
            resultadoS = var_temp_triplo + "=" + op1 + operador + op2;
            
            //ES PARA IR INGRESANDO EN EL ARCHIVO LAS OPERACIONES DE LOS TRIPLOS
            resultadoS2+=resultadoS;
            resultadoS2 = resultadoS2 + (char) (13);
            resultadoS2 = resultadoS2 + (char) (10);
            
            //ES PARA EXTRAER EL TRIPLO EN EL QUE SE QUEDA AL FINALIZAR UNA EXPRESION MATEMATICA
            var_temp_triplos_glo=var_temp_triplo;
            //Agregamos a la pila la vaiable temporal
            pila_Triplos.push(var_temp_triplo);
        }
    }

    public void Triplos(String posfijo) {
        //Esta funcion recibe la expresion en posfijo y hace la separación de los triplos
        String cadena = "", var_temp = "_-T";
        char caracter = ' ';
        int indice = 0, longitud_cadena = 0;
        longitud_cadena = posfijo.length();
        while (indice < longitud_cadena) {
            caracter = posfijo.charAt(indice);
            switch (caracter) {
                //xx1,2,3,*,+
                case '+':
                    ejecutar_operacion(caracter, var_temp);
                    indice++;
                    break;
                case '-':
                    ejecutar_operacion(caracter, var_temp);
                    indice++;
                    break;
                case '*':
                    ejecutar_operacion(caracter, var_temp);
                    indice++;
                    break;
                case '/':
                    ejecutar_operacion(caracter, var_temp);
                    indice++;
                    break;
                case '^':
                    ejecutar_operacion(caracter, var_temp);
                    indice++;
                    break;
                case ',':
                    if (!cadena.equals("")) {
                        pila_Triplos.push(cadena);
                        cadena = "";
                    }
                    indice++;
                    break;
                default:
                    cadena += caracter;
                    indice++;
                    break;
            }
        }
        //ES PORQUE HABIA UN ERROR AL MOMENTO DE QUERER INSERTAR LA IGUALDAD DE LA VARIABLE QUE RECIBE Y EL ULTIMO TRIPLO, YA CON ESTO SE SOLUCIONÓ EL ERROR
        //if (cont_varTemp!=0 && cont_Verifi_Triplo!=0) {
            variable_result+=":="+var_temp_triplos_glo;
            System.out.println("Var result 2: "+variable_result);
            System.err.println(variable_result);
            resultadoS2+=variable_result;
            resultadoS2 = resultadoS2 + (char) (13);
            resultadoS2 = resultadoS2 + (char) (10);
            
        //}
        variable_result="";
        cont_varTemp++;
        
        //Por ultimo debemos extraer el resultado final de la pila que es el que se debera de asignar a la variable de asignacion original
        //Esta accion es un cuadruplo. por el momento solo la imprimimos.
        

        try {
            
            FileWriter fw = new FileWriter(f, false);
            String tipo = "", variable = "", registro = "";
            for (int i = 0; i < indice_TS; i++) {
                tipo = Tabla_Simbolos[i][1];
                variable = Tabla_Simbolos[i][0];
                registro = tipo + " " + variable;
                registro = registro + (char) (13);
                registro = registro + (char) (10);
                fw.write(registro);
                registro = "";
            }
            fw.write(resultadoS2);
            fw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}