/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricky
 */
package pkgwhile;
import java.util.Scanner;

public class While{
    public static void main(String[] args) {
        While pr = new While();
    
        if (pr.whl()!=30) {
            System.out.println("Sintaxis correcta");
        } else {
            System.out.println("Sintaxis erronea");
        }
    }

    public int whl() {
        int s=0;
            Scanner leer = new Scanner(System.in);
        String txt;
        System.out.println("Ingrasa texto");
        txt = leer.nextLine();
        int b=0;
     //   int tbl[][]=new int[27][19];
       // for (int i = 0; i < 19; i++) {
         //   for (int j = 0; j < 27; j++) {
           //     tbl[j][i]=30;
            //}
        //}
        int i=0;
           while (b!=13||b==30) {
               
        switch (txt.charAt(i)) {
            
            case 'w':
                  if(b ==0)b=1;
                else b=30;
                break;
            case 'h':
                  if(b ==1)b=2;
                else b=30;
                break;
            case 'i':
                  if(b ==2)b=3;
                else b=30;
                break;
            case 'l':
                  if(b ==3)b=4;
                else b=30;
                break;
            case 'e':
                  if(b ==4)b=5;
                else b=30;
                break;
            case '(':
                  if(b ==5)b=6;
                else b=30;
                break;
            case 'a':
                  if(b ==6)b=7;
                else b=30;
                break;
            case '>':
                 if(b==7)b=8;
                else b=30;
                break;
            case '<':
                  if(b ==7)b=8;
                else b=30;
                break;
            case'>' ^ '=':
                  if(b ==7)b=8;
                else b=30;
                break;
            case '<' ^ '=':
                  if(b ==7)b=8;
                else b=30;
                break;
            case '=' ^ '=':
                 if(b ==7)b=8;
                else b=30;
                break;
            case  'b':
                if(b ==8)b=9;
                else b=30;
                break;
            case ')':
                if(b ==9)b=10;
                else b=30;
                break;
            case '{':
                if(b ==10)b=11;
                else b=30;
                break;
                
                default:
                    if (b!=11) {
                b=30;        
                    }
                break;
                  
            case ';':
                if(b ==11)b=12;
                else b=30;
                break;
            
            case '}':
                if(b ==12)b=13;
                else b=30;
                break;  
        }     
        i++;
         if(b ==11){
                   txt = txt.concat(leer.nextLine()); 
               }
        if (b==30) {
                break;    
                }
           }
        return b;
    }
}