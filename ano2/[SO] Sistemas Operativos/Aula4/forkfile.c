#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(int argc, char *argv[]){
    
    FILE *fd;
    char nome[100];
    
    
    
    int var =getpid();
    printf("getpid = %d\n",var);
    for(int i=1 ; i<=3 ; i++){ //é possível criar uma familia linear de processos(onde n existam processos irmaos?)
        pid_t child = fork();
        printf("child = %d\n",child);
        switch (child) {
            case 0:
                sprintf(nome, "TMP%d", 8);
                break;
                
            default:
                sprintf(nome, "TMP%d", child-var);
                break;
        }
         //cria arquivo
        fd = fopen(nome, "w");
        fclose(fd);
    }
    
    return 0;
}
