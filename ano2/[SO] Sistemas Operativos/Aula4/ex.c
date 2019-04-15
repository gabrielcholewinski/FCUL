/*    EXERCICIOS FUNDAMENTAIS
1-
  O Pai executa o primeiro printf e depois cria um filho
  O Pai e o filho executam o segundo printf e criam ambos outro filho (3 filho+Pai)
  O Pai e os 3 filhos executam o terceiro printf, ou seja 4 printf
*/

/*
2-
  a) São criados apenas 1 processo filho
  b) var = 1
     var = 1
  c)
*/

/*
3-
#include <stdio.h>
#include <unistd.h>
int main(){
  int n;
  printf("Digite o Valor de n: ");
  scanf("%d",&n);
  for(int i=1 ; i<=n ; i++){
    int pid = fork();
    sleep(1);
    if(pid != 0)
      printf("O processo %d tem PID = %d\n",i,pid);
  }
}
*/

/*
4-
  a) 8, que é 2^n, sendo n o numero de forks
  b) É só tirar um fork, sobrando 2, 2^2 = 4
  c)
*/
//5-
#include <stdio.h>
#include <stdlib.h>            //lib para usar macros EXIT_SUCCESS e EXIT_FAILURE
#include <unistd.h>            //lib para usar fork() exec()
#include <sys/types.h>            //lib para usar tipo pid_t
#include <sys/wait.h>            //lib para usar wait()

int main(){
    
    pid_t child = fork();
    
    if(child == 0){
        execl("/bin/ls", "ls", "teste", NULL);
    }
        printf("Hello World\n");
    return 0;
}
//UMA MENSAGEM APENAS EH CRIADA(PELO PAI), POIS O PROCESSO FILHO NAO RETORNA APOS O EXECL (FICA EM ESPERA)
