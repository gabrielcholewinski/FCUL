# GRUPO SO-068
# Carlos Marques, nº 51964
# Gabriel Freitas, nº 51035
# Ruhan Azevedo, nº 51779


BASE_dir = SO_PerPro
OBJ_dir = obj
SRC_dir = src
INC_dir = include
BIN_dir = bin
TSTOUT_dir = tests/out
DUO_inc = $(INC_dir)/main.h $(INC_dir)/memory.h
TRIO_inc = $(DUO_inc) $(INC_dir)/sotime.h
SO_PROD_inc = $(INC_dir)/so.h $(INC_dir)/prodcons.h
FI_CO_inc = $(INC_dir)/file.h $(INC_dir)/control.h
MAIN_SO_inc = $(INC_dir)/main.h $(INC_dir)/so.h
CLIENTE_inc = $(TRIO_inc) $(INC_dir)/cliente.h $(INC_dir)/file.h
CONTROL_inc = $(INC_dir)/main.h $(INC_dir)/memory.h $(INC_dir)/control.h $(SO_PROD_inc)
EMPRESA_inc = $(TRIO_inc) $(INC_dir)/empresa.h
INTER_inc = $(DUO_inc) $(INC_dir)/intermediario.h
USERS_inc = $(INC_dir)/cliente.h $(INC_dir)/intermediario.h $(INC_dir)/empresa.h
MAIN_inc = $(TRIO_inc) $(USERS_inc) $(SO_PROD_inc) $(FI_CO_inc)
MEMORY_inc = $(TRIO_inc) $(SO_PROD_inc) $(FI_CO_inc) $(INC_dir)/scheduler.h
PROD_inc = $(INC_dir)/main.h $(SO_PROD_inc) $(INC_dir)/control.h
SCHE_inc = $(MAIN_SO_inc) $(INC_dir)/scheduler.h
TIME_inc = $(MAIN_SO_inc) $(INC_dir)/sotime.h
COMPILAR = $(OBJ_dir)/cliente.o $(OBJ_dir)/control.o $(OBJ_dir)/empresa.o $(OBJ_dir)/file.o $(OBJ_dir)/intermediario.o $(OBJ_dir)/main.o $(OBJ_dir)/memory.o $(OBJ_dir)/prodcons.o $(OBJ_dir)/scheduler.o $(OBJ_dir)/time.o
OBJETOS = $(COMPILAR) $(OBJ_dir)/so.o
LIBS = -lrt -lpthread

soperpro: $(OBJETOS)
		gcc $(OBJETOS) -o $(BIN_dir)/soperpro $(LIBS)

$(OBJ_dir)/cliente.o: $(SRC_dir)/cliente.c $(CLIENTE_inc)
		gcc -c $(SRC_dir)/cliente.c -I$(INC_dir) -o $(OBJ_dir)/cliente.o

$(OBJ_dir)/control.o: $(SRC_dir)/control.c $(CONTROL_inc)
		gcc -c $(SRC_dir)/control.c -I$(INC_dir) -o $(OBJ_dir)/control.o

$(OBJ_dir)/empresa.o: $(SRC_dir)/empresa.c $(EMPRESA_inc)
		gcc -c $(SRC_dir)/empresa.c -I$(INC_dir) -o $(OBJ_dir)/empresa.o

$(OBJ_dir)/file.o: $(SRC_dir)/file.c $(TRES_inc) $(SO_PROD_inc) $(INC_dir)/file.h $(INC_dir)/scheduler.h
		gcc -c $(SRC_dir)/file.c -I$(INC_dir) -o $(OBJ_dir)/file.o

$(OBJ_dir)/intermediario.o: $(SRC_dir)/intermediario.c $(INTER_inc)
		gcc -c $(SRC_dir)/intermediario.c -I$(INC_dir) -o $(OBJ_dir)/intermediario.o

$(OBJ_dir)/main.o: $(SRC_dir)/main.c $(MAIN_inc)
		gcc -c $(SRC_dir)/main.c -I$(INC_dir) -o $(OBJ_dir)/main.o

$(OBJ_dir)/memory.o: $(SRC_dir)/memory.c $(MEMORY_inc)
		gcc -c $(SRC_dir)/memory.c -I$(INC_dir) -o $(OBJ_dir)/memory.o

$(OBJ_dir)/prodcons.o: $(SRC_dir)/prodcons.c $(PROD_inc)
		gcc -c $(SRC_dir)/prodcons.c -I$(INC_dir) -o $(OBJ_dir)/prodcons.o

$(OBJ_dir)/scheduler.o: $(SRC_dir)/scheduler.c $(SCHE_inc)
		gcc -c $(SRC_dir)/scheduler.c -I$(INC_dir) -o $(OBJ_dir)/scheduler.o

$(OBJ_dir)/time.o: $(SRC_dir)/time.c $(TIME_inc)
		gcc -c $(SRC_dir)/time.c -I$(INC_dir) -o $(OBJ_dir)/time.o

clean:
		rm -f $(COMPILAR) $(BIN_dir)/soperpro

#fazer make testar ficheiroEntrada=[nome ficheiro] ficheiroSaida=[nome saida]
#ambos os ficheiros tem de estar nos tests/out
testar:
	  ./script.sh $(TSTOUT_dir)/$(ficheiroEntrada) $(TSTOUT_dir)/$(ficheiroSaida)
