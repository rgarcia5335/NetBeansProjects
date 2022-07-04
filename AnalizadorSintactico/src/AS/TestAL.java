
package AS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class TestAL {
    String resultado;
    String variables;
    /*Crear lista de los identificadores*/
    List<Identificador> tokenlist;
    
    public static void main(String[] args) throws IOException {
        TestAL onj1 = new TestAL(); /**/

        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        String cadena = leer.readLine();
        
        onj1.probarLexer(cadena);
    }

    public void probarLexer(String cadena) throws IOException{
        int contIds = 0;
        /*Lista ligada para los tokens*/
        tokenlist = new LinkedList<Identificador>();
        
        /*crear fichero donde se guardara la cadena a analizar*/
        File fichero = new File("fichero.txt");
        
        /*Objeto para oder escribir en el fichero*/
        PrintWriter writer;
        
        /*En caso de que no se encuentre el archivo para crear en el*/
        try{
            writer = new PrintWriter(fichero);
            writer.printf(cadena);
            writer.close();
            
        }catch(FileNotFoundException ex){
            System.out.println("No se encontro el Archivo");
        }
        
        /*Crear el Objeto Input Stream*/
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer(reader);
        /*Para mostrar la informacion que se va a ir creando*/
        resultado="";
        /*Generamos ciclo para la cadena*/
        while(true){
            Token token = lexer.yylex();
            if(token==null){
                for (int i = 0; i <tokenlist.size(); i++) {
                    /*Lista de las variables que encontro*/
                    variables=tokenlist.get(i).nombre + " = "+tokenlist.get(i).ID;
                    //System.out.println(tokenlist.get(i).nombre + " = "+tokenlist.get(i).ID);
                    System.out.println(variables);
                }
                /*Muestra variables*/
                System.out.println(resultado);
                return;
            }
            
            /*Determinar cual de los operadores se identifico para guardarlo en la variale resultado*/
            switch(token){
                case ERROR:
                    resultado =" Sentencia Incorrecta, Verifique su Escritura "+"\t\n";
                    break;    
                case CORRECTO:
                    resultado += "";
                    break;
            }
        }
        
    }
    
}
