#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <semaphore.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "buffer.h"

static int *buffer;

static sem_t *sem_full, *sem_empty;

void iniciaBuffer ()
{
	int ret, fd;
	fd = shm_open ("/shm", O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);
	if (-1 == fd) {
		perror ("/shm");
		exit (1);
	}
	ret = ftruncate (fd, sizeof (int));
	if (-1 == ret) {
		perror("/shm");
		exit(2);
	}
	buffer = mmap(0, sizeof (int), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	if (MAP_FAILED == buffer) {
		perror("shm-mmap");
		exit(3);
	}
	sem_full = sem_open("/full", O_CREAT, 0xFFFFFFFF, 0);
	if (SEM_FAILED == sem_full) {
		perror("full");
		exit(4);
	}
	sem_empty = sem_open("/empty", O_CREAT, 0xFFFFFFFF, 1);
	if(SEM_FAILED == sem_empty) {
		perror("empty");
		exit(5);
	}
}

void destroiBuffer ()
{
	int ret;
	ret = munmap (buffer, sizeof (int));
	if (-1 == ret) {
		perror ("shm-munmap");
		exit (1);
	}
	ret = shm_unlink("/shm");
	if (-1 == ret) {
		perror ("shm-unlink");
		exit (1);
	}
	ret = sem_close(sem_full);
	if (-1 == ret) {
		perror ("sem_full");
		exit (1);
	}
	ret = sem_unlink("/full");
	if (-1 == ret) {
		perror ("sem_full");
		exit (1);
	}
	ret = sem_close(sem_empty);
	if (-1 == ret) {
		perror ("sem_empty");
		exit (1);
	}
	ret = sem_unlink("/empty");
	if (-1 == ret) {
		perror("sem_empty");
		exit (1);
	}
}

void escreveBuffer (int valor)
{
	int ret;
	ret = sem_wait (sem_empty);
	if (-1 == ret) {
		perror ("sem wait");
		return ;
	}
	*buffer = valor;
	ret = sem_post (sem_full);
	if (-1 == ret) {
		perror ("sem_full");
		return ;
	}
}

int leBuffer ()
{
	int valor = 0;
	/* completar */
	return valor;
}
