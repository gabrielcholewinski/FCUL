#include <stdio.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <semaphore.h>
#include <unistd.h>
#include <stdlib.h>

int incrementa (int n)
{
	n = n + 1;
	printf ("%d\n", n);
	return n;
}

/**
 * Aloca espaço para um inteiro em memória partilhada.
 */
int *cria ()
{
	int *ptr;
	int ret;

	int fd= shm_open ("/shm", O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);
	if (fd == -1) {
		perror ("shm");
		exit (1);
	}

	ret = ftruncate (fd, sizeof (int));
	if (ret == -1) {
		perror ("shm");
		exit (2);
	}

	ptr = mmap (0, sizeof (int), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	if (ptr == MAP_FAILED) {
		perror ("shm-mmap");
		exit (3);
	}
	return ptr;
}

/**
 * Liberta a memória partilhada.
 */
void destroi (int *ptr)
{
	int ret;
	ret = munmap (ptr, sizeof (int));
	if (ret == -1) {
		perror ("/shm");
		exit (7);
	}
	ret = shm_unlink ("/shm");
	if (ret == -1) {
		perror ("/shm");
		exit (8);
	}
}

int main (int argc, char *argv[])
{
	int *ptr = cria ();
	int numProcessosFilhos, numIncrementos;
	int i;
	if (argc == 3) {
		numProcessosFilhos = atoi (argv [1]);
		numIncrementos = atoi (argv [2]);
	}
	else {
		numProcessosFilhos = 10; 
		numIncrementos = 1;
	}
	*ptr = 0;
	for (i = 0; i < numProcessosFilhos; i++) {
		switch (fork ()) {
		case -1:
			perror ("fork");
			exit (1);
		case 0:
			for (i = 0 ; i < numIncrementos; i++) {
				(*ptr) = incrementa (*ptr);
			}
			exit (0);
		}
	}
	/* esperar pelos processos filhos */
	for (i = 0; i < numProcessosFilhos; i++) {
		wait (NULL);
	}
	printf ("valor final: %d\n", *ptr);
	destroi (ptr);
	return 0;
}
