#include <errno.h>
#include <fcntl.h>
#include <limits.h>
#include <pthread.h>
#include <semaphore.h>
#include <signal.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h> //mmap
#include <sys/resource.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

/*
1-
char *copiar_string(char *original){
	int i, size;
	for(i=0 ; original[i]!='\0' ; i++)
		size++;

	char *res = calloc(size,sizeof(char));
	res = original;
	return res;
}

int main(){
	char *text = copiar_string("taa");
	
	for(int i=0 ; text[i]!='\0' ; i++)
		printf("%c", text[i]);
	printf("\n");
}
*/

/* 2- a)
struct value {
	int value_i;
	char value_c;
};

int main(){
	int i,length;
	printf("Qual a dimensão do vector? ");
	scanf("%d", &length); //se colocar \n ao lado do %d, fica travado no terminal
	struct value *valores;

	valores = calloc(length, sizeof(struct value)); //criar
	int size = length*sizeof(struct value);
	printf("%d\n",size );

	for(i=0 ; i<length ; i++){    // 
		valores->value_i = 0;	  // -> inicializa
		valores->value_c = '0';  // /
	}
	free(valores);					//destrói

	printf("%i\n", valores[2].value_i);
	return 0;
}
	b) Sim, o int tem 4 bits, o char tem 1bit, mas em struct, sempre que há 2 tipos diferentes, o valor do menor tipo eh alterado para o do maior, nesse caso o char valeria 4 também
*/

/*
3-
struct aluno{
	int numero;
	char *nome;
	int *notas;
};

int main(int argc, char *argv[]){
	struct aluno *cobaia;
	struct aluno *atento;

	 cobaia = malloc(sizeof(struct aluno));
	 atento = malloc(sizeof(struct aluno));

	 cobaia->notas= malloc(2* sizeof(int)); 
	 atento->notas = calloc(2, sizeof(int)); 

	 cobaia->numero = 51000;   
	 atento->numero = 52000;

	 cobaia->nome = "Joaquim Saudade";   
	 atento->nome = "Manuel Cenoura";

	 cobaia->notas[0] = 20;   
	 cobaia->notas[1] = 18; 

	 atento->notas[0] = 17;   
	 atento->notas[1] = 18; 

	 printf("Aluno: %d - %s Nota: T1 %d T2 %d\n", 
	 	cobaia->numero, cobaia->nome, 
	 	cobaia->notas[0], cobaia->notas[1]); 

	 printf("Aluno: %d - %s Nota: T1 %d T2 %d\n", 
	 	atento->numero, atento->nome, 
	 	atento->notas[0], atento->notas[1]);   
	
	 free(atento->notas);
	 free(cobaia->notas);
	 free(atento);
	 free(cobaia);
	
	 return 0; 

}
*/

/*4-
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char*argv[]){     
	int var = 100;     
	int ret = fork();     
	if(ret == -1){ 
		perror("fork");
		exit(1); 
	}else{
		printf("ret = %d\n",ret);
		if(ret == 0){  
			var = var + 100;
			printf("no filho: %d\n", var); 
		} else{
			wait(NULL);
			printf("no pai: %d\n", var); 
		}
	}
	return 0; 
}
a) O Processo pai cria o processo filho, e então, entra no else primeiro, pois o pai executa primeiro, executa quando o var é 100, mas usa Wait(NULL) que espera o filho terminar para executar a proxima operação, então o filho executa o var+100 = 200 e printa, depois volta ao else, e executa enquanto o var era 100
b)
*/
/*
int *var;
void partilhar(){
	int ret, fd;
  char buffer[100];

  shm_unlink("/ruhan");
	fd = shm_open("/ruhan", O_RDWR|O_CREAT, S_IRUSR|S_IWUSR);
	if(fd == -1){
		perror(buffer);
		exit(1);
	}

	ret = ftruncate(fd, sizeof(int));
	if(ret == -1){
		perror(buffer);
		exit(2);
	}

	var = mmap(0, sizeof(int), PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
	if(var == MAP_FAILED){
		perror(buffer);
		exit(3);
	}
  close(fd);

}

void memoria_libertar(){
  if(!munmap(var,sizeof(int)))
      shm_unlink("/ruhan");
}

int main(){
  partilhar();
	*var = 100;
	int ret = fork();
	if(ret == -1){
		perror("fork");
		exit(1);
	}else{
		if(ret == 0){
			*var = *var + 100;
			printf("no filho: %d\n", *var);
		} else{
			wait(NULL);
			printf("no pai: %d\n", *var);
		}
	}
  memoria_libertar();
	return 0;
}
*/

//5- professor explicou na aula para mim

//6-
#include "mutex_lib.h"
int *saldo;
void partilhar_memoria(){
	int ret, fd;
  char buffer[100];

  shm_unlink("/ruhan");
	fd = shm_open("/ruhan", O_RDWR|O_CREAT, S_IRUSR|S_IWUSR);
	if(fd == -1){
		perror(buffer);
		exit(1);
	}

	ret = ftruncate(fd, sizeof(int));
	if(ret == -1){
		perror(buffer);
		exit(2);
	}

	saldo = mmap(0, sizeof(int), PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
	if(saldo == MAP_FAILED){
		perror(buffer);
		exit(3);
	}
  close(fd);

}

void memoria_libertar(){
  if(!munmap(saldo,sizeof(int)))
      shm_unlink("/ruhan");
}

int levantamento(quantia){  
	lock();     
	*saldo -= quantia;   
	usleep(1); //escrever saldo na zona de memória
	unlock();
	return *saldo; 
}

int deposito(quantia){  
	lock();     
	*saldo += quantia;   
	usleep(1); 
	//escrever saldo na zona de memória
	unlock();  
	return *saldo;
}

int main(){
	*saldo = 100;
	partilhar_memoria();
	init();
	levantamento(50);
	deposito(100);
	printf("saldo_atual = %d\n",*saldo);
	destroy();
	memoria_libertar();
}

//COMO USAR A CLASSE MUTEX???????????????????

