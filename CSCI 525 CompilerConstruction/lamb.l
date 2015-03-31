%{
#include <cstdio>
#include <iostream>
using namespace std;
#define YY_DECL extern "C" int yylex()

#include "lamb.tab.h" //get token types

%}
TOK_INCREMENT	(\+\+)
TOK_DECREMENT	(\-\-)

%%
[ \t\n]			;
int			{ yylval.sval = strdup(yytext); return TYPE;}
if			{ yylval.sval = strdup(yytext); return IF;}
else			{ yylval.sval = strdup(yytext); return ELSE;}
while			{ yylval.sval = strdup(yytext); return WHILE;}
main			{ yylval.sval = strdup(yytext); return MAIN;}
[0-9]+			{ yylval.ival = atoi(yytext); return NUM;}
[a-zA-Z][a-zA-Z0-9]*	{ yylval.sval = strdup(yytext); return VAR;}
[;]			{ yylval.sval = strdup(yytext); return SEMICOLON;}
\=\=			{ yylval.sval = strdup(yytext); return EQUAL;}
\!\=			{ yylval.sval = strdup(yytext); return NOTEQUAL;}
\>\=			{ yylval.sval = strdup(yytext); return GREATERTHANEQUAL;}
\<\=			{ yylval.sval = strdup(yytext); return LESSTHANEQUAL;}
[<]			{ yylval.sval = strdup(yytext); return LESSTHAN;}
[>]			{ yylval.sval = strdup(yytext); return GREATERTHAN;}
[=]			{ yylval.sval = strdup(yytext); return ASSIGN;}
\+\+         	 	{ yylval.sval = strdup(yytext); return INC;}
\-\-          	 	{ yylval.sval = strdup(yytext); return DEC;}
[+]			{ yylval.sval = strdup(yytext); return PLUS;}
[-]			{ yylval.sval = strdup(yytext); return MINUS;}
[*]			{ yylval.sval = strdup(yytext); return MULT;}
[/]			{ yylval.sval = strdup(yytext); return DIV;}
[(]			{ yylval.sval = strdup(yytext); return LPAR;}
[)]			{ yylval.sval = strdup(yytext); return RPAR;}
[{]			{ yylval.sval = strdup(yytext); return LCUR;}
[}]			{ yylval.sval = strdup(yytext); return RCUR;}
[%]			{ yylval.sval = strdup(yytext); return MOD;}
.			{ yylval.sval = strdup(yytext); return JUNK;}
%%

