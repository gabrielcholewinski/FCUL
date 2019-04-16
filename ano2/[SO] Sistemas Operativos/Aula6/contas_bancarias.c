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

#define MEM_NAME "/banco"
int *ptr;

void memoria_criar() {
  int fd, ret;
  char buf[100];

  shm_unlink(MEM_NAME); // Safety check
  fd = shm_open(MEM_NAME, O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);
  if (fd == -1) {
    strcpy(buf, MEM_NAME);
    strcat(buf, "-create");
    perror(buf);
    exit(1);
  }
  ret = ftruncate(fd, sizeof(int));
  if (ret == -1) {
    strcpy(buf, MEM_NAME);
    strcat(buf, "-ftruncate");
    perror(buf);
    exit(2);
  }
  ptr = mmap(0, sizeof(int), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
  if (ptr == MAP_FAILED) {
    strcpy(buf, MEM_NAME);
    strcat(buf, "-mmap");
    perror(buf);
    exit(3);
  }
  close(fd);
}

void memoria_libertar(){
  if(!munmap(ptr,sizeof(int)))
    shm_unlink(MEM_NAME);
}

int levantamento(int quantia) {
  int saldo;
  saldo = *ptr;
  saldo -= quantia;
  usleep(1);
  *ptr = saldo;
  return saldo;
}

int deposito(int quantia) {
  int saldo;
  saldo = *ptr;
  saldo += quantia;
  usleep(1);
  *ptr = saldo;
  return saldo;
}

int operacao_filho() {
  int i;
  for (i = 0; i < 500; i++)
    levantamento(100);
  return 0;
}

int operacao_pai() {
  int i;
  for (i = 0; i < 500; i++)
    deposito(100);
  return 0;
}

int main() {
  int pid;
  memoria_criar();
  *ptr = 1000;
  pid = fork();
  if (pid == 0) {
    operacao_filho();
    usleep(1);
    exit(0);
  } else {
    operacao_pai();
    wait(NULL);
    printf("Saldo final = %d\n", *ptr);
  }
  memoria_libertar();
  return 0;
}
