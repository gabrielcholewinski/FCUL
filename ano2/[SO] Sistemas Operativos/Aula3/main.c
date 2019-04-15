#include <stdio.h>
#include "funcoes.h"

int main()
{
    int num;
    printf("Insira um numero inteiro: ");
    scanf("%d", &num);
    
    if(positivo(num)){
        if (num_par(num))
            printf("O numero eh par positivo.\n");
        else printf("O numero eh impar positivo.\n");
    }
    else{
        if (num_par(num))
            printf("O numero eh par negativo.\n");
        else printf("O numero eh impar negativo.\n");
    }
    return 0;
}
