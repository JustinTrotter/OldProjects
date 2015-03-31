loadLamb: lamb.l
	bison -d lamb.y
	flex lamb.l
	g++ -std=c++11 lamb.tab.c lex.yy.c -lfl -o lamb -w
	./lamb
