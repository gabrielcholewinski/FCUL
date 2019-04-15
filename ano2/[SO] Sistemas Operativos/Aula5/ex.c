/*
 1-
#include <stdio.h>
#include <stdlib.h>

char *copia_string(char *original){
    int i, length = 0;
    for(i=0 ; original[i]!='\0' ; i++)
        length++;
    char *a;
    a = malloc(length + sizeof(char));
    for(i=0 ; i<length ; i++){
        a[i] = original[i];
        printf(" o *a tem: %c\n",a[i]);
    }
    printf("%d\n", length);
    printf("%p\n",&a);
    return a;
}

int main(int argc, char *argv[]) {
    char s[4] = {'a','b','c','\0'};
    char *a = s;
    copia_string(a);
    printf("%p\n", &a);
    return 0;
}
*/

/*                          REVER
 2-
#include <stdio.h>
#include <stdlib.h>

struct value {
    int value_i;
    char value_c;
};

int main(){
    //criar
    struct value *a;
    int size = sizeof(struct value);
    //printf("%d\n",length);
    a = malloc(size);
    
    //inicializar
    a[0].value_i = 0;
    a[0].value_c = '0';
    
    //destruir
    free(a);//mesmo existindo valor, esta desalocada
    return 0;
}
*/

/*
 3-
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
struct aluno {
    int numero;
    char *nome;
    int *notas;
    
};

int main(int argc, char *argv[]) {
    struct aluno *cobaia;
    struct aluno *atento;
    
    cobaia = malloc(sizeof(struct aluno));
    atento = malloc(sizeof(struct aluno));
    
    cobaia->notas = malloc(2 * sizeof(int));
    atento->notas = calloc(2, sizeof(int));
    
    cobaia->numero = 51000;
    atento->numero = 52000;
    
    cobaia->nome = "Joaquim Saudade";
    atento->nome = "Manuel Cenoura";
    
    cobaia->notas[0] = 20;
    cobaia->notas[1] = 18;
    
    atento->notas[0] = 17;
    atento->notas[1] = 18;
    
    printf("Aluno: %d - %s\nNota: T1:%d T2:%d\n", cobaia->numero, cobaia->nome, cobaia->notas[0], cobaia->notas[1]);
    printf("Aluno: %d -%s\nNota: T1:%d T2:%d\n", atento->numero, atento->nome, atento->notas[0], atento->notas[1]);
    free(atento);
    free(cobaia);
    return 0;
}
*/

/*
4-a)
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>
int main(){
    int var = 100;
    int ret = fork();
    
    if(ret == -1){
        perror("fork");
        exit(1);
    } else {
        if(ret == 0){
            var = var + 100;            //entra aqui com o processo filho(pid = 0)
            printf("no filho: %d\n", var);
        } else {
            wait(NULL);                 //entra aqui com o processo pai, e so executa quando o filho terminar, logo o var volta a ser 100
            printf("no pai: %d\n", var);
        }
    }
    return 0;
}

b)
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>
#include <sys/mman.h> //shm functions
#include <fcntl.h> //flags
int main(int argc, char*argv[]){
    int var = 100;
    int child = fork();
    //printf("%lu\n",sizeof(var));
    if(child == -1){
        perror("fork");
        exit(1);
    }
    else{
        
        int fd=shm_open("/shm", O_RDWR|O_CREAT, S_IRUSR|S_IWUSR);
        if(fd == -1){ perror("shm");  exit(1); }
        
        int ret = ftruncate(fd, sizeof(var));
        if(ret == -1){ perror("shm"); exit(2); }
        
        int *ptr = mmap(0, sizeof(var), PROT_READ|PROT_WRITE, MAP_SHARED , fd , 0);
        if(ptr == MAP_FAILED){ perror("shm-mmap");  exit(3); }
        
        ret = munmap (ptr, sizeof(var));
        if(ret == -1){ perror("/dev/shm"); exit(7); }
        
        ret = shm_unlink("/shm");
        if(ret == -1){ perror("/dev/shm"); exit(8); }
            
        
        if(child == 0){
            var = var + 100;
            printf("no filho: %d\n", var);
        }
        else{
            wait(NULL);
            printf("no pai: %d\n", var);
        }
        
    }
    return 0;
    
}
*/
    
//5-
