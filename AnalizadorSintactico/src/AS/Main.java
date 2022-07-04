package AS;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\077CA\\AnalizadorSintactico\\src\\AS\\Lexer.flex";
        /*Generar el archivo correspondiente al archivo lexer*/
        File file= new File(path);
        JFlex.Main.generate(file);
    }
}