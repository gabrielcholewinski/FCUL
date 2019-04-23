#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include <time.h>
#include <pthread.h>
#include <errno.h>
#include <sys/mman.h> //mmap
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <signal.h>
#include <limits.h>
#include <stdint.h>
#include <sys/resource.h>

#define MEM_NAME "/banco"
int *ptr;

void memoria_criar()
{
    int fd, ret;
    char buf[100];

    shm_unlink(MEM_NAME);
    fd = shm_open(MEM_NAME, O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);
    if (-1 == fd)
    {
        strcpy(buf, MEM_NAME);
        strcat(buf, "-create");
        perror(buf);
        exit(1);
    }
    ret = ftruncate(fd, sizeof(int));
    if (-1 == ret)
    {
        strcpy(buf, MEM_NAME);
        strcat(buf, "-ftruncate");
        perror(buf);
        exit(2);
    }
    ptr = (int *)mmap(0, sizeof(int), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (MAP_FAILED == ptr)
    {
        strcpy(buf, MEM_NAME);
        strcat(buf, "-mmap");
        perror(buf);
        exit(3);
    }
    close(fd);
}

int levantamento(quantia)
{
    int saldo;
    saldo = *ptr;
    saldo -= quantia;
    usleep(1);
    *ptr = saldo;
    return saldo;
}

int deposito(quantia)
{
    int saldo;
    saldo = *ptr;
    saldo += quantia;
    usleep(1);
    *ptr = saldo;
    return saldo;
}

int operacao_filho()
{
    int i;
    for (i = 0; i < 500; i++)
        levantamento(100);
    return 0;
}

int operacao_pai()
{
    int i;
    for (i = 0; i < 500; i++)
        deposito(100);
    return 0;
}

int main()
{
    int pid;
    memoria_criar();
    *ptr = 1000;
    pid = fork();
    if (pid == 0)
        operacao_filho();
    else
    {
        operacao_pai();
        wait(NULL);
        printf("Saldo final = %d\n", *ptr);
    }
    return 0;
}