/*
#include <stdio.h>
int main () {
int n1, n2;
printf("Ola mundo!\n");
printf("Digite um numero inteiro:");
scanf("%d", &n1);
printf("Digite outro numero inteiro:");
scanf("%d", &n2);
printf("total = %d\n", n1+n2);
return 0;
}
*/

/*
#include <stdio.h>
int main(){
  int n1,n2;
  printf("Escreva o valor do n1: ");
  scanf("%d", &n1 );
  printf("Escreva o valor do n2: ");
  scanf("%d", &n2 );

//alternativa 1
  if(n1 > n2)
    printf("O maior deles é %d\n",n1);
  else
    printf("O maior deles é %d\n",n2);
//alternativa 2 :
printf("O maior deles é %d\n", n1>n2?n1:n2);
}
*/

/*
#include <stdio.h>
int main(){
  float n1,n2;
  printf("Escreva o valor do n1: ");
  scanf("%f", &n1);
  printf("Escreva o valor do n2: ");
  scanf("%f", &n2);
  printf("A média desses números é:  %.2f\n", (n1+n2)/2);
}
*/

/*
#include <stdio.h>
int main(){
  int n1,n2;
  printf("Escreva o valor do n1: ");
  scanf("%d", &n1);
  printf("Escreva o valor do n2: ");
  scanf("%d", &n2);
  printf("A média desses números é:  %d\n", (n1+n2)/2);
}
*/

/*
#include <stdio.h>
#include <math.h>
int main(){
  float n1,n2;
  printf("Escreva o valor do n1: ");
  scanf("%f", &n1);
  printf("Escreva o valor do n2: ");
  scanf("%f", &n2);
  float media = (n1+n2)/2;
  printf("A média desses números é:  %f\n", floor(media));
  float res;
  if(n1 <= media)
    res = n1;
  else if(n2 <= media)
    res = n2;
  printf("%f\n", res);
}
*/

#include <stdio.h>
#include <math.h>
int main(){
    float n1,n2;
    printf("Escreva o valor do n1: ");
    scanf("%f", &n1);
    printf("Escreva o valor do n2: ");
    scanf("%f", &n2);

    printf("A média desses números é:  %f\n", media(n1,n2));

}

/*
#include <stdio.h>
#include "funcoes.h"
int main(){
	float n1, n2;
	printf("Digite um numero inteiro:");
	scanf("%f", &n1);
	printf("Digite outro numero inteiro:");
	scanf("%f", &n2);
	printf("media = %.2f\n", media(n1,n2));

}
*/

/*
#include <stdio.h>
int main(){
	int integer = 5;
	char character5 = '5';
	char charactera = 'a';
	printf("charactera = %d\n", charactera); //97 que é o mesmo que converter o 'a' pela ascii table
	printf("character5 = %d\n", character5); //53 que é o mesmo que converter o '5' pela ascii table
	printf("integer = %d\n", integer); //5 porque o %d é de decimal
	printf("charactera = %c\n", charactera); //'a' = a
	printf("character5 = %c\n", character5); //'5' = 5
	printf("integer = 53 = %c\n", integer = 53); //5 = 5
	printf("('5' > 5) = %d\n", '5' > 5 ); //1 = True, pois em decimal, ao converter o '5' da 53, que é maior que 5
	printf("('a' > 5) = %d\n", 'a' > 5 ); //1 = True, pois em decimal, ao converter o 'a' da 97, que é maior que 5
	return 0;
}
*/

/*
#include <stdio.h>
#include "funcoes.h"
int main(){
	char c1;
	printf("Escreva o caracter para ser convertido em Ascii: ");
	scanf("%c", &c1);
	int res = asciiConverter(c1);
	printf("%d\n",res);
	
}
*/

//1
#include <stdio.h>
#include "aux.c"
int main(){
	char c1;
	printf("Digite um caracter: ");
	scanf("%c", &c1);
	printf("%d\n", asciiConverter(c1));
}