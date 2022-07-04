package flex;
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jimy
 */
public class main {
    public static void main(String[] args) {
        String path="C:/Users/ricky/Documents/NetBeansProjects/jflex/src/flex/lexer.flex";      
  generarLexer(path);
    }

    public static void generarLexer(String path){
        File file=new File(path);
        jflex.Main.generate(file);
 	   }
    
}
