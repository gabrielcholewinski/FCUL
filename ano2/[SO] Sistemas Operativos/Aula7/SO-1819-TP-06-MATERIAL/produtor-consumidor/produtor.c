#include <stdio.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <stdlib.h>

#include "buffer.h"

int produz ()
{
	int n;
	printf ("Indique um numero inteiro:\n");
	scanf ("%d", &n);
	return n;
}

void sair ()
{
	destroiBuffer ();
	printf ("Vou terminar\n");
	exit (0);
}

int main (int argc, char* argv[])
{
	iniciaBuffer ();
	/* 	retirando a chamada a funcao signal */
	/* 	e' preciso fazer rm /dev/shm/\* */
	signal (SIGINT, sair);
	while (1) {
		int valor = produz ();
		escreveBuffer (valor);
		printf ("'A espera de ser consumido: %d\n", valor);
	}
	return 0;
}
