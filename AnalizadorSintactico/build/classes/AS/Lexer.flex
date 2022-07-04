package AS;
import static AS.Token.*; 

%%
%class Lexer
%type Token

E = [ ]
L = [a-zA-Z]
D = [0-9]
F = [_]
G = [!-@]
KO = [{]
KC = [}]
PC=[;]
OPLOG=[><=]
MAS = [+]
COMA=[,]
MAS = [+]
MENOS = [-]
POR = [*]
ENTRE = [/]

IGUAL=":="
FOR="For"
EACH="Each"
ELEMENTO="Elemento"
AS="As"
INTEGER="entero"
BOOLEANO="booleano"
STRING="String"
FLOTANTE="flotante"
IN="In"
GRUPO="Grupo"
NEXT="Next"
WHILE="While"


WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/*Ignore*/}


{WHILE}{E}(({D})+|({L}({L}|{F}|{D})+))*{OPLOG}(({D})+|({L}({L}|{F}|{D})+))*{E}{KO}{KC} {lexeme=yytext(); return CORRECTO;}
{FOR}{E}{L}({L}|{D})*{PC}{E}{L}({L}|{D})*{OPLOG}({L}|{D}){PC}{E}{L}({L}|{D})*{MAS}{MAS}{E}{KO}{E}(({INTEGER}|{BOOLEANO}|{FLOTANTE}){E}{L}({L}|{D})*({COMA}{L}({L}|{D})*)*{PC}{E}|{L}({L}|{F}|{D})*{IGUAL}({L}({L}|{F}|{D})*|{D}+)(({MAS}|{MENOS}|{POR}|{ENTRE})({L}({L}|{F}|{D})*|{D}+))*{PC}{E})*{KC} {lexeme=yytext(); return CORRECTO;}
({INTEGER}|{BOOLEANO}|{FLOTANTE}){E}{L}({L}|{D})*({COMA}{L}({L}|{D})*)*{PC} {lexeme=yytext(); return CORRECTO;}
/*x1=y2+x3+2;*/
{L}({L}|{F}|{D})*{IGUAL}({L}({L}|{F}|{D})*|{D}+)(({MAS}|{MENOS}|{POR}|{ENTRE})({L}({L}|{F}|{D})*|{D}+))*{PC} {lexeme=yytext(); return CORRECTO;}


. {return ERROR; }


