//1. IMPORTACIONES
package flex;
import static flex.Token.*;
%%
//2. DEFINICIONES
%class Lexer
%type Token
L=[a-zA-Z_]
D=[0-9]
WHITE=[ \t\r\n]
%{
public String lexeme;
%}
//3. REGLAS LEXICAS (EXPRECIONES REGULARES)
%%
{WHITE} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return ASIGNACION;}
"==" {return IGUAL;}
"+" {return MAS;}
"*" {return POR;}
"-" {return MENOS;}
{L} ({L}|{D})* {lexeme=yytext(); return ID;} //Un identificador siempre comenzara por una letra y después pueden ir letras o digitos
[-+]?{D}+ {lexeme=yytext(); return INT;} //Los dígitos podrán llevar signo - o + antes del digito él ? es pueden o no
. {return ERROR;}
