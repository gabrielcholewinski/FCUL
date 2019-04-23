CC = gcc

CFLAGS = -Wall

LDLIBS = -lrt -lpthread

all: produtor consumidor

produtor: produtor.o buffer.o

consumidor: consumidor.o buffer.o

produtor.o: produtor.c buffer.h

consumidor.o: consumidor.c buffer.h

buffer.o: buffer.h buffer.c

clean:
	rm -f *~ *.o

cleanall: clean
	rm -f produtor consumidor
