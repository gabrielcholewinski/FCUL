/*
# GRUPO SO-068
# Carlos Marques, nº 51964
# Gabriel Freitas, nº 51035
# Ruhan Azevedo, nº 51779
*/

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

#include "main.h"
#include "so.h"
#include "memory.h"
#include "prodcons.h"
#include "control.h"
#include "file.h"
#include "sotime.h"
#include "intermediario.h"
#include "cliente.h"
#include "empresa.h"


struct statistics Ind;     		// indicadores do funcionamento do SO_PerPro
struct configuration Config; 	// configuração da execução do SO_PerPro

/* main_cliente recebe como parâmetro o nº de clientes a criar */
void main_cliente(int quant)
{
  //==============================================
  // CRIAR PROCESSOS
  //
  // criar um número de processos cliente igual a quant através de um ciclo com n=0,1,...
  // após a criação de cada processo, o processo filho realiza duas atividades:
  // - chama a função cliente_executar passando como parâmetro o valor da variável de controlo do ciclo n=0,1,...
  // - chama a função exit passando como parâmetro o valor devolvido pela função cliente_executar
  // o processo pai guarda o pid do filho no vetor Ind.pid_clientes[n], com n=0,1,... e termina normalmente a função
  //so_main_cliente(quant);

  for (int n = 0; n < quant; n++){
    int parametro, filho;
    if (fork() == 0){
      parametro = cliente_executar(n);
      filho = getpid();
      exit(parametro);
      Ind.pid_clientes[n] = filho;
    }
  }

}

/* main_intermediario recebe como parâmetro o nº de intermediarios a criar */
void main_intermediario(int quant)
{
  //==============================================
  // CRIAR PROCESSOS
  //
  // criar um número de processos intermediarios igual a quant através de um ciclo com n=0,1,...
  // após a criação de cada processo, o processo filho realiza duas atividades:
  // - chama a função intermediario_executar passando como parâmetro o valor da variável de controlo do ciclo n=0,1,...
  // - chama a função exit passando como parâmetro o valor devolvido pela função intermediario_executar
  // o processo pai guarda o pid do filho no vetor Ind.pid_intermediarios[n], com n=0,1,... e termina normalmente a função
  //so_main_intermediario(quant);
  //==============================================

  for (int n = 0; n < quant; n++){
    int parametro, filho;
    filho = fork();
    if (filho == 0){
      parametro = intermediario_executar(n);
      filho = getpid();
      exit(parametro);
    }
    Ind.pid_intermediarios[n] = filho;
  }

}

/* main_empresas recebe como parâmetro o nº de empresas a criar */
void main_empresas(int quant)
{
  //==============================================
  // CRIAR PROCESSOS
  //
  // criar um número de processos empresas igual a quant através de um ciclo com n=0,1,...
  // após a criação de cada processo, o processo filho realiza duas atividades:
  // - chama a função empresas_executar passando como parâmetro o valor da variável de controlo do ciclo n=0,1,...
  // - chama a função exit passando como parâmetro o valor devolvido pela função empresas_executar
  // o processo pai guarda o pid do filho no vetor Ind.pid_empresas[n], com n=0,1,... e termina normalmente a função
  //so_main_empresas(quant);
  //==============================================

  for (int n = 0; n < quant; n++){
    int parametro, filho;
    if (fork() == 0){
      parametro = empresa_executar(n);
      filho = getpid();
      exit(parametro);
      Ind.pid_empresas[n] = filho;
    }
  }

}

int main(int argc, char *argv[])
{
  char *ficEntrada = NULL;
  char *ficSaida = NULL;
  char *ficLog = NULL;
  long intervalo = 0;

  //==============================================
  // TRATAR PARÂMETROS DE ENTRADA
  // parâmetro obrigatório: file_configuracao
  // parâmetros opcionais:
  //   file_resultados
  //   -l file_log
  //   -t intervalo(us)    // us: microsegundos
  //
  // ignorar parâmetros desconhecidos
  // em caso de ausência de parâmetros escrever mensagem de como utilizar o programa e terminar
  // considerar que os parâmetros apenas são introduzidos na ordem indicada pela mensagem
  // considerar que são sempre introduzidos valores válidos para os parâmetros
  //intervalo = so_main_args(argc, argv, &ficEntrada, &ficSaida, &ficLog);
  //==============================================

  if (argc >= 2){
    ficEntrada = argv[1];
  } else {
    printf("%s\n", "Tem que especificar um ficheiro de entrada");
    exit(1);
  }
  if (argc >= 3){
    ficSaida = argv[2];
  }
  if (argc >= 5){
    ficLog = argv[4];
  }
  if (argc >= 7){
    intervalo = atoi(argv[6]);
  }

  printf(
    "\n------------------------------------------------------------------------");
    printf(
      "\n----------------------------- SO_PerPro ------------------------------");
      printf(
        "\n------------------------------------------------------------------------\n");

        // Ler dados de entrada
        file_begin(ficEntrada, ficSaida, ficLog);
        // criar zonas de memória e semáforos
        memory_create_buffers();
        prodcons_create_buffers();
        control_create();

        // Criar estruturas para indicadores e guardar capacidade inicial de servicos
        memory_create_statistics();

        printf("\n\t\t\t*** Open SO_PerPro ***\n\n");
        control_open_soperpro();

        // Registar início de operação e armar alarme
        time_begin(intervalo);

        //
        // Iniciar sistema
        //

        // Criar INTERMEDIARIOS
        main_intermediario(Config.INTERMEDIARIO);
        // Criar EMPRESAS
        main_empresas(Config.EMPRESAS);
        // Criar CLIENTES
        main_cliente(Config.CLIENTES);

        //==============================================
        // ESPERAR PELA TERMINAÇÃO DOS CLIENTES E ATUALIZAR ESTATISTICAS
        //
        // esperar por cada cliente individualmente
        // se o cliente terminou normalmente
        // então testar se o valor do status é igual a SERVICOS
        //   se for igual entao nao atualizar as estatisticas
        //   se for inferior entao incrementar o indicador da respetiva servico obtida
        // Ind.serviços_recebidos_por_clientes[SERVICO], n=0,1,...
        //so_main_wait_clientes();
        //==============================================

        for (int i = 0; i < Config.CLIENTES; i++){
          //printf("LENGTH SERVICOS = %d\n",Config.SERVICOS);
          int resultCliente;
          //pid_t waitpid(pid_t pid, int *status_ptr, int options);
          waitpid(Ind.pid_clientes[i], &resultCliente, 0);

          if (WIFEXITED(resultCliente)){
            int exit = WEXITSTATUS(resultCliente);
            if (exit < Config.SERVICOS){
              printf("%s\n","Entrou");
              Ind.servicos_recebidos_por_clientes[exit]++;
            }
          }
        }

        // Desarmar alarme
        time_destroy(intervalo);

        printf("\n\t\t\t*** Close SO_PerPro ***\n\n");
        control_close_soperpro();

        //==============================================
        // ESPERAR PELA TERMINAÇÃO DOS INTERMEDIARIOS E ATUALIZAR INDICADORES
        //
        // esperar por cada intermediario individualmente
        // se a intermediario terminou normalmente
        // então atualizar o indicador de investors atendidos
        // Ind.clientes_servidos_por_intermediarios[n], n=0,1,...
        // guardando nele o estado devolvido pela terminação do processo
        //so_main_wait_intermediarios();
        //==============================================

        for (int i = 0; i < Config.INTERMEDIARIO; i++){
          int resultInter;
          //pid_t waitpid(pid_t pid, int *status_ptr, int options);
          waitpid(Ind.pid_intermediarios[i], &resultInter, 0);

          //WIFEXITED (int status)
          //This macro returns a nonzero value if the child process terminated normally with exit or _exit.
          if (WIFEXITED(resultInter)){
            //printf("PAAASSOOOOOOOOOOOUUUUUUUUUUUUUUu");
            //WEXITSTATUS (int status)
            //If WIFEXITED is true of status, this macro returns the low-order 8 bits of the exit status value from the child process.
            Ind.clientes_servidos_por_intermediarios[i] = WEXITSTATUS(resultInter);
          }
        }

        //==============================================
        // ESPERAR PELA TERMINAÇÃO DAS EMPRESAS E ATUALIZAR INDICADORES
        //
        // esperar por cada empresa individualmente
        // se a empresa terminou normalmente
        // então atualizar o indicador de investors atendidos
        // Ind.clientes_servidos_por_empresas[n], n=0,1,...
        // guardando nele o estado devolvido pela terminação do processo
        //so_main_wait_empresas();
        //==============================================

        for (int i = 0; i < Config.EMPRESAS; i++){
          int resultEmpresa;
          //pid_t waitpid(pid_t pid, int *status_ptr, int options);
          waitpid(Ind.pid_empresas[i], &resultEmpresa, 0);

          if (WIFEXITED(resultEmpresa)){
            Ind.clientes_servidos_por_empresas[i] = WEXITSTATUS(resultEmpresa);
          }
        }

        printf(
          "------------------------------------------------------------------------\n\n");
          printf("\t\t\t*** Statistics ***\n\n");
          so_write_statistics();
          printf("\t\t\t*******************\n");

          // destruir zonas de memória e semáforos
          file_destroy();
          control_destroy();
          prodcons_destroy();
          memory_destroy_all();

          return 0;
        }
