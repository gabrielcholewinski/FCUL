/*/1
#include <stdio.h>
int conta_vogais (char str []){
    int i, count = 0;
    for (; str[i] != '\0'; i++)
        if (str[i] == 'a' ||
            str[i] == 'e' ||
            str[i] == 'i' ||
            str[i] == 'o' ||
            str[i] == 'u')
            count++;
    return count;
}

int main () {
    char msg [21];
    printf ("Qual a string?\n");
    scanf ("%20s", msg);
    printf ("A letra a ocorre %d vezes na string.\n", conta_vogais(msg));
    return 0;
}*/

/*/2
#include <stdio.h>

int max(int size, int array[]){
    int argm = array[0]; //arg max
    for(int i = 1 ; i<=size ; i++){
        if(array[i] > argm){
            argm = array[i];
        }
    }
        return argm;
}

int main(){
    int vInt[] = {1,2,3,7,5,6};
    int size = sizeof(vInt);
    //printf("%d\n", size);
    printf("O valor maximo desse array é: %d \n", max(6, vInt));
}*/

/*/3
#include <stdio.h>
int calcNeg(int v[], int sizeV){
    int value = 0;
    printf("%d\n", value);
    for(int i=0 ; i<=sizeV ; i++)
        if(v[i] < 0){
            value += 1;
        }
        return value;
}

int main(){
    int array [4] = {-1,-2,2,1};
    calcNeg(array,4);
    printf("resultado = %d\n", calcNeg(array,4));
    //problemas com imprimir, ele recebe valores diferentes dos que eu coloquei, por que?
}
*/

/*/4
#include <stdio.h>
int size(char string[]){
    int count,i = 0;
    for(; string[i]!='\0' ; i++){
        count = i+1;
    }
    return count;
}

int main(){
    char string[4] = {'a','b','c','\0'};
    printf("%d\n", size(string));
}
*/

/*/5
#include <stdio.h>
#include <stdlib.h>
int freqK (int k, int array[], int sizeArray){
	int count = 0;
	for(int i=0 ; i<sizeArray ; i++)
		if(array[i] == k)
			count++;
	return count;
}

int * frequencia (int array[], int sizeArray){
	int *result = (int*) malloc (sizeArray);
	for(int i=0 ; i<sizeArray ; i++){
		result[i] = freqK(array[i], array, sizeArray);
	}
	return result;
}

int main(){
	int v [] = {1,1,2,3,1,4};
	int length = sizeof(v)/sizeof(int);
	
	for(int i=0 ; i<length ; i++){
		if(i == 0)
			printf("[%d,", frequencia(v,length)[i]);
		else if(i+1 == length)
			printf("%d]", frequencia(v,length)[i]);
		else 
			printf("%d,", frequencia(v,length)[i]);
	}
	printf("\n");
}*///como imprimir os valores que o ponteiro esta apontando?(nesse caso um array)


//6 só escrever no terminal './a.out > saida.txt'

///7
#include <stdio.h>
#include <math.h>
char ehIdentidade(int matriz[], int size);


int main(){
	int matriz[] = {0,1,
					1,0,};
	int length = sizeof(matriz)/sizeof(int);
	printf("%c\n", ehIdentidade(matriz,length));
}

char ehIdentidade (int matriz[], int size){
	char result = 's';
	for(int i=0 ; i<size ; i++){
		int j = i+sqrt(size)*i;
		if((matriz[j]!=1 && j<size) || (i!=j && matriz[i] != 0 && j<size))
			return 'n';
	}
		return result;
}

/*/8
#include <stdio.h>
struct aluno{
	char* nome;
	int numero;
	char* email;
	int classificacao;
};

struct avaliacao{
	int notaTrabalho;
	int notaExame;
	int presenca;
};


#include <stdio.h>
#include <stdlib.h>
int main(){

	char nome[30]; //char * nome;
	printf("Digite seu nome: ");
	scanf("%s",nome);

	int numero;
	printf("Digite seu número: ");
	scanf("%d", &numero);

	char email[20];
	printf("Digite seu email: ");
	scanf("%s", email);

	int notaTrabalho;
	printf("Digite nota que obteve no Trabalho [0-20]: ");
	scanf("%d", &notaTrabalho);

	int notaExame;
	printf("Digite nota que obteve no Exame [0-20]: ");
	scanf("%d", &notaExame);

	int presenca;
	printf("Digite seu número de presenças [0-20]: ");
	scanf("%d", &presenca);

	int final = (notaExame*0.6)+(notaTrabalho*0.25)+(presenca*0.15);

	struct aluno al1 = {nome,numero,email,final};
	struct avaliacao av1 = {notaTrabalho, notaExame, presenca};
	
	printf("\n  ----- ALUNO -----\n");
	printf(" |Nome = %s\n ", al1.nome);
	printf("|Número = %d\n ", al1.numero);
	printf("|Email = %s\n ", al1.email);
	printf("|Nota do Trabalho = %d\n ", av1.notaTrabalho);
	printf("|Nota do Exame = %d\n ", av1.notaExame);
	printf("|Número de presenças = %d\n", av1.presenca);
	printf(" |Classificação final = %d", final);
	printf("\n  -----------------\n");
}
*/
/*
//9
	#!  bin/bash
	gcc -Wall $1 -o program
	./program
*/
