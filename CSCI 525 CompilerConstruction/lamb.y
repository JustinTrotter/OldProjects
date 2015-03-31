%{
#include <cstdio>
#include <iostream>
#include <fstream>
#include <sstream>
#include <tuple>
#include <functional>
#include <string>
#include <cstring>
#include <map>
#include <vector>
//using namespace std;


extern "C" int yylex();
extern "C" int yyparse();
extern "C" FILE *yyin;

void yyerror(const char *s);
void loadVar(char *str, int r);
void loadOp(char *result, char *opr, int r2, int r0, int r1); 
void setType(char *type, char *var);
void num2var(int num, char *var);
int var2reg(char *var, int reg);
int num2reg(int num, int reg);
void reg2var(int reg, char *var);
int reg2reg(int reg1, int reg2);
void numOpreg(int num, char *opr, int reg);

int add(int reg1, int reg2);
int sub(int reg1, int reg2);
int mult(int reg1, int reg2);
int divz(int reg1, int reg2);
int inc(int reg);
int dec(int reg);
void resetReg(int num, int reg);
int jump(int reg1, char *cmpr, int reg2, int loc);
int jump2(int loc);
void setLoc(int loc);
int setLoc2(int loc);
typedef std::tuple<int,const char*> tuples;
//std::map<char*,tuples> tbl;

class test_map {
private:
	std::vector<char*> keyList;
	std::vector<tuples> elemList;
	std::vector<char*> dataList;

	int getKeyIndex(char* key) {
		for (int i = 0; i < keyList.size(); i++) {
			if (std::strcmp(keyList[i], key) == 0)
			{
				return i;
			}
		}

		return -1;
	}
public:
	bool hasKey(char* key)
	{
		for (char* s : keyList)
		{
			if (std::strcmp(s, key) == 0)
			{
				return true;
			}
		}

		return false;
	}

	tuples getElement(char* key)
	{
		int i = getKeyIndex(key);
		if (i != -1) {
			return elemList[i];
		}
		else {
			exit(-1);
		}
	}

	char* getData(char* key)
	{
		int i = getKeyIndex(key);
		if (i != -1){
			return dataList[i];
		}
		else {
			exit(-1);
		}
	}

	void setData(char* key, char* data)
	{
		if(hasKey(key))
		{
			int i = getKeyIndex(key);
			if (i != -1){
				dataList[i] = data;
			}
			else {
				exit(-1);
			}
		}
	}

	void put(char* key, tuples elem, char* data)
	{
		if (!hasKey(key))
		{
			keyList.push_back(key);
			elemList.push_back(elem);
			dataList.push_back(data);
		}
		else {
			elemList[getKeyIndex(key)] = elem;
		}
	}
};

std::string output;
std::ostringstream s;
int reg = 0;
int offset = 0;
int regCount = 0;
bool flag = false;
bool first = false;
int level = 1;

char *memVar = "";
int memReg = 0;

test_map tbl = test_map();
%}

%union {
	int ival;
	float fval;
	char *sval;
}

%token <sval> TYPE
%token <ival> NUM
%token <sval> VAR
%token <sval> ASSIGN
%token <sval> SEMICOLON
%token <sval> OPR
%token <sval> PLUS
%token <sval> MINUS
%token <sval> MULT
%token <sval> DIV
%token <sval> MOD
%token <sval> JUNK
%token <sval> LPAR
%token <sval> RPAR
%token <sval> LCUR
%token <sval> RCUR
%token <sval> INC
%token <sval> DEC
%token <sval> MAIN
%token <sval> EQUAL
%token <sval> NOTEQUAL
%token <sval> LESSTHANEQUAL
%token <sval> GREATERTHANEQUAL
%token <sval> LESSTHAN
%token <sval> GREATERTHAN
%token <sval> IF
%token <sval> ELSE
%token <sval> WHILE

%type <ival> Exp Term Factor cond elsecomp whilecomp whileblock
%type <sval> cmpopr
%%

main:
	mainTitle LCUR body RCUR	{s << "pop %rbp" << std::endl << "ret" << std::endl;}
	;

mainTitle:
	MAIN				{s << "push %rbp" << std::endl << "mov %rsp --> %rbp" << std::endl;}
	;

body:
	body blocks
	| blocks
	;

blocks:
	if
	| while
	| blocks lines
	| lines
	;
if:
	IF ifelseblock
	| IF ifblock				
	;

while:
	whilecomp whileblock			{jump2($1);
						 setLoc($2);}
	;

whilecomp:
	WHILE					{$$ = setLoc2(++level);}

whileblock:
	cond LCUR blocks RCUR			{$$ = $1;}	
	;
	
ifblock:
	cond LCUR blocks RCUR			{setLoc($1);}
	;

ifelseblock:
	cond LCUR blocks RCUR elsecomp LCUR blocks RCUR	{setLoc($5);}	

elsecomp:
	ELSE					{$$ = jump2(++level);
						 setLoc(level-1);}

cond:
	LPAR Exp cmpopr Exp RPAR		{$$ = jump($2,$3,$4,++level);}
	;

cmpopr:
	EQUAL					{$$ = "jne";}
	| NOTEQUAL				{$$ = "je";}
	| GREATERTHAN				{$$ = "jle";}
	| LESSTHAN				{$$ = "jge";}
	| GREATERTHANEQUAL			{$$ = "jl";}
	| LESSTHANEQUAL				{$$ = "jg";}
	;


lines:
	lines lines
	| line
	;

Goal:
	VAR ASSIGN Exp SEMICOLON		{ reg2var($3,$1);}

	;

Exp:
	Exp PLUS Term				{ $$ = add($1,$3);}
	| Exp MINUS Term			{ $$ = sub($1,$3);}
	| Term					{ $$ = $1;}
	;

Term:
	Term MULT Factor			{ $$ = mult($1,$3);}
	| Term DIV Factor			{ $$ = divz($1,$3);}
	| Factor				{ $$ = $1;}
	;

Factor:
	INC VAR                                 { $$ = inc(var2reg($2,++regCount));}
        | VAR INC                               { $$ = inc(var2reg($1,++regCount));}
	| DEC VAR				{ $$ = dec(var2reg($2,++regCount));}
	| VAR DEC				{ $$ = dec(var2reg($1,++regCount));}
	| LPAR Exp RPAR				{ $$ = $2;}
	| NUM					{ $$ = num2reg($1,++regCount);}
	| VAR					{ $$ = var2reg($1,++regCount);}

	;

line:
	TYPE VAR SEMICOLON			{ setType($1,$2);}
	| TYPE VAR ASSIGN NUM SEMICOLON		{ setType($1,$2);
						  num2var($4, $2);}
	| TYPE VAR ASSIGN VAR SEMICOLON		{ setType($1,$2);
						  var2reg($4,reg);
						  reg2var(reg,$2);
						  ++reg;}
	| Goal					{}
	| VAR ASSIGN NUM SEMICOLON		{ num2var($3, $1);}
	| VAR ASSIGN VAR SEMICOLON		{ var2reg($3,reg);
						  reg2var(reg,$1);
						  ++reg;}
	;
%%
main() {
	//open input file
	FILE *myfile = fopen("input.lmb","r");
	if(!myfile){
		std::cout << "Invalid File" << std::endl;
		return -1;
	}

	//create output file
	std::ofstream outputFile;
	outputFile.open("output.ir");

	//set input
	yyin = myfile;

	//parse input
	do{
		yyparse();
	} while (!feof(yyin));
	output += s.str();

	//print output to screen
	std::cout << output << std::endl;
	//write output to file
	outputFile << output;
	outputFile.close();
}

void resetReg(int num, int reg)
{
	s << "mov $";
	s << num;
        s << " --> ";
        s << "%r"; 
        s << reg;
        s << "\n";	
}

int jump(int reg1, char *cmpr, int reg2, int loc)
{
	s << "cmp %r";
	s << reg1;
	s << " --> %r";
	s << reg2;
	s << "\n";
	s << cmpr;
	s << " .L";
	s << loc;
	s << "\n";
	return loc;
	}

int jump2(int loc)
{
	s << "jmp .L";
	s << loc;
	s << "\n";
	return loc;
}

int setLoc2(int loc)
{
	s << ".L";
	s << loc;
	s << ":\n";
	return loc;
}

void setLoc(int loc)
{
	s << ".L";
	s << loc;
	s << ":\n";
}


void setType(char *type, char *var)
{
	if (tbl.hasKey(var)) 
	{	
		yyerror("Variable has already been given a type");
	}
	else
	{
		offset = offset - 4;
		tuples tup = std::make_tuple(offset,type);
		tbl.put(var,tup,"nil");
		std::cout << "Type: " << type << " Var: " << var << " Offset: " << offset << std::endl;
	}
}

void num2var(int num, char *var)
{
	if(tbl.hasKey(var))
	{
		tbl.setData(var,"notnil");
		s << "mov $";
		s << num;
		s << " --> ";
		s << std::get<0>(tbl.getElement(var));
	//	s << std::get<0>(search->second);
		s << "(%rbp)";
		s << "\n";
	}
	else yyerror("movl: Variable invalid or missing type");	
}

int var2reg(char *var, int reg)
{
	if(tbl.hasKey(var))
	{
		if(strcmp(tbl.getData(var), "nil") != 0)
		{
			s << "mov ";
			s << std::get<0>(tbl.getElement(var));
			s << "(%rbp) --> ";
			s << "%r";
			s << reg;
			s << "\n";
			return reg;
		}
		yyerror("Null Pointer Exception");
	}
	else 
	{
	yyerror("var2reg: Variable invalid or missing type");	
	return -1;
	}
}

int num2reg(int num, int reg)
{
	s << "mov $";
	s << num;
	s << " --> %r";
	s << reg;
	s << "\n";
	return reg;
}	

void reg2var(int reg, char *var)
{
	if(tbl.hasKey(var))
	{
		tbl.setData(var,"notnil");
		s << "mov ";
		s << "%r";
		s << reg;
		s << " --> ";
		s << std::get<0>(tbl.getElement(var));
		s << "(%rbp)";
		s << "\n";
	}
	else yyerror("reg2var: Variable invalid or missing type");
}

int reg2reg(int reg1, int reg2)
{
	s << "mov %r";
	s << reg1;
	s << " --> %r";
	s << reg2;
	s << "\n";
	return reg2;
}

int add(int reg1, int reg2)
{
	s << "add %r";
	s << reg1;
	s << " --> %r";
	s << reg2;
	s << "\n";
	return reg2;
}

int sub(int reg1, int reg2)
{
        s << "sub %r";
        s << reg1;
        s << " --> %r";
        s << reg2;
        s << "\n";
	return reg2;
}

int mult(int reg1, int reg2)
{
        s << "mul %r";
        s << reg1;
        s << " --> %r";
        s << reg2;
        s << "\n";
	return reg2;
}

int divz(int reg1, int reg2)
{
        s << "div %r";
        s << reg1;
        s << " --> %r";
        s << reg2;
        s << "\n";
	return reg2;
}

int inc(int reg)
{
	s << "add $1 --> %r";
	s << reg;
	s << "\n";
	return reg;
}

int dec(int reg)
{
	s << "sub $1 --> %r";
	s << reg;
	s << "\n";
	return reg;
}

void loadVar(char *str, int r)
{
/*
	s << "load ";
	s << "r";
	s << r;
	s << " ";
	s << str;
	s << "\n";
*/
}

void loadOp(char *result, char *opr, int r2, int r0, int r1)
{
/*
	char *operand = "null";
	if(*opr == '+') operand = "add";
	else if(*opr == '-') operand = "sub";
	else if(*opr == '*') operand = "mult";
	else if(*opr == '/') operand = "div";
	else yyerror("Invalid operand");
	
	s << operand;
	s << " r";
	s << r2;
	s << " r";
	s << r0;
	s << " r";
	s << r1;
	s << "\n";

	s << "save ";
	s << result;
	s << " r";
	s << r2;
	s << "\n";
*/
}

void yyerror(const char *s) {
	std::cout << "Error: " << s << std::endl;
	exit(-1);
}



























