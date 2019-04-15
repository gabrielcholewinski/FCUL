#include <math.h>
/*
Função calcula média
*/
double media(double n1, double n2){
	return floor((n1+n2)/2);
}

#include <ctype.h>
char asciiConverter(char c1){
	//printf("%d",&c1); como retornar o printf pois dessa forma ja converteria automaticamente
	return toascii(c1);
}
