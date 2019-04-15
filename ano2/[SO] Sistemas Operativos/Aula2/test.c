
#include <stdio.h>
struct tipo_data {
	int dia;
	int mes;
	int ano;
	char* nome_mes ;
};
int main () {
	int dia;
	printf("dia: ");
	scanf("%d",&dia);

	char * nome_mes;
	printf("mes: ");
	scanf("%s",nome_mes);

	int ano;
	printf("ano: ");
	scanf("%d",&ano);

	struct tipo_data d = {dia, 6, ano, nome_mes};
	printf ("Faço anos dia %d de %s de %d\n", d.dia, d.nome_mes, d.ano);
}