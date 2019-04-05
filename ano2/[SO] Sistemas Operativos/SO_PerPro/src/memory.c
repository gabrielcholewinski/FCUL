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
#include "scheduler.h"

//==============================================
// DECLARAR ACESSO A DADOS EXTERNOS
//
extern struct configuration Config;
//==============================================

struct response_p BProposta;  	// buffer empresa-cliente
struct request_b BOrcamento; 	// buffer intermediario-empresa
struct request_d BDescricao; 	// buffer cliente-intermediario
struct scheduler Schedule;    //FALTA ESTA ESTRUTURA CRL!!! ACRESCENTEI ISTO!!!
struct statistics Ind;        //tive que criar estah estrutura para o ultimo metodo!!!
//******************************************
// CRIAR ZONAS DE MEMORIA
//
void * memory_create(char * name, int size) {

    int *ptr;
    int fd, ret;
    char name_uid[100];

    //getuid() retorna o id do usuario real do processo de chamada
    sprintf(name_uid, "/%s_%d", name, getuid());

    //a função unlink usamos para libertar memoria
    shm_unlink(name_uid);

    //criar zona de memoria partilhada
    //name_uid vai ser o nome do nosso arquivo em memoria
    fd = shm_open(name_uid, O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);
    if(fd == -1){
        strcat(name_uid, "-create");
        perror(name_uid);
        exit(1);
    }

    //aqui estamos a definir o tamanho do segmento em memoria
    ret = ftruncate(fd, size);
    if(ret == -1){
        strcat(name_uid, "-ftruncate");
        perror(name_uid);
        exit(2);
    }

    //projecao numa zona de memoria partilhada(neste caso na zona de memoria que definimos)
    ptr = mmap(0, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if(ptr == MAP_FAILED){
        strcat(name_uid, "-mmap");
        perror(name_uid);
        exit(3);
    }

    close (fd);
    return ptr;
        //==============================================
	// FUNÇÃO GENÉRICA DE CRIAÇÃO DE MEMÓRIA PARTILHADA
	//
	// usar getuid() para gerar nome unico na forma:
	// sprintf(name_uid,"/%s_%d", name, getuid())
	// usar name_uid em shm_open
	// usar tambem: ftruncate e mmap
	//return so_memory_create(name, size);
	//==============================================
}
void memory_create_capacidade_servicos() {

	Config.capacidade_servico = (int*) memory_create(STR_SHM_STOCK, Config.SERVICOS * sizeof(int));

	//==============================================
	// CRIAR ZONA DE MEMÓRIA PARA A CAPACIDADE DE SERVICOS
	//
	// utilizar a função genérica memory_create(char *,int)
	// para criar ponteiro que se guarda em Config.capacidade_servicos
	// que deve ter capacidade para um vetor unidimensional
	// com a dimensao [SERVICOS] para inteiro
	//so_memory_create_capacidade_servicos();
	//==============================================
}
void memory_create_buffers() {

	//PROPOSTAS
    BProposta.ptr = (int*) memory_create(STR_SHM_RECEIPT_PTR, Config.BUFFER_PROPOSTA * sizeof(int));
    BProposta.buffer = memory_create(STR_SHM_RECEIPT_BUFFER, Config.BUFFER_PROPOSTA * sizeof(BProposta.buffer));

    //ORCAMENTO
    BOrcamento.ptr = (int*) memory_create(STR_SHM_ORDER_PTR, Config.BUFFER_ORCAMENTO * sizeof(int));
    BOrcamento.buffer = memory_create(STR_SHM_ORDER_BUFFER, Config.BUFFER_ORCAMENTO * sizeof(BOrcamento.buffer));

    //DESCRICAO
    BDescricao.ptr = memory_create(STR_SHM_CURRENCY_PTR, Config.BUFFER_DESCRICAO * sizeof(int));
    BDescricao.buffer = memory_create(STR_SHM_CURRENCY_BUFFER, Config.BUFFER_DESCRICAO * sizeof(BDescricao.buffer));

	//==============================================
	// CRIAR ZONAS DE MEMÓRIA PARA OS BUFFERS: DESCRICAO, ORCAMENTO e PROPOSTAS
	//
	// utilizar a função genérica memory_create(char *,int)
	//
	// para criar ponteiro que se guarda em BProposta.ptr
	// que deve ter capacidade para um vetor unidimensional
	// de inteiros com a dimensao [BUFFER_PROPOSTA]
	// para criar ponteiro que se guarda em BProposta.buffer
	// que deve ter capacidade para um vetor unidimensional
	// com a dimensao [BUFFER_PROPOSTA] para struct service
	//
	// para criar ponteiro que se guarda em BOrcamento.ptr
	// que deve ter capacidade para um vetor unidimensional
	// de inteiros com a dimensao [BUFFER_ORCAMENTO]
	// para criar ponteiro que se guarda em BOrcamento.buffer
	// que deve ter capacidade para um vetor unidimensional
	// com a dimensao [BUFFER_ORCAMENTO] para struct service
	//
	// para criar ponteiro que se guarda em BDescricao.ptr
	// que deve ter capacidade para struct pointers
	// para criar ponteiro que se guarda em BDescricao.buffer
	// que deve ter capacidade para um vetor unidimensional
	// com a dimensao [BUFFER_DESCRICAO] para struct service
	//so_memory_create_buffers();
	//==============================================
}
void memory_create_scheduler() {

	Schedule.ptr = (int*) memory_create(STR_SHM_SCHEDULER, sizeof(int[Config.SERVICOS][Config.EMPRESAS]));

	//==============================================
	// CRIAR ZONA DE MEMÓRIA PARA O MAPA DE ESCALONAMENTO
	//
	// utilizar a função genérica memory_create(char *,int)
	// para criar ponteiro que se guarda em Schedule.ptr
	// que deve ter capacidade para um vetor bidimensional
	// com a dimensao [SERVICOS,EMPRESAS] para inteiro
	//so_memory_create_scheduler();
	//==============================================
}

void memory_destroy(char * name, void * ptr, int size) {

	char name_uid[100];
    	sprintf(name_uid, "/%s_%d", name, getuid()); //pq armazenar o nome da zona de memoria que 		vai ser destruída se vai ser destruída?
    	if(!munmap(ptr,size)){ //se nao retornar -1 (sucesso)
      		(shm_unlink(name_uid));
    	}else {
      		perror(name);
      		exit(8);
    	}

	//==============================================
	// FUNÇÃO GENÉRICA DE DESTRUIÇÃO DE MEMÓRIA PARTILHADA
	//
	// usar getuid() para gerar nome unico na forma:
	// sprintf(name_uid,"/%s_%d", name, getuid())
	// usar name_uid em shm_unlink
	// usar tambem: munmap
	//so_memory_destroy(name, ptr, size);
	//==============================================
}

//******************************************
// MEMORIA_DESTRUIR
//
void memory_destroy_all() {

    memory_destroy(STR_SHM_STOCK, Config.capacidade_servico, Config.SERVICOS * sizeof(int));

    memory_destroy(STR_SHM_RECEIPT_PTR, BProposta.ptr, Config.BUFFER_PROPOSTA * sizeof(int));
    memory_destroy(STR_SHM_RECEIPT_BUFFER, BProposta.buffer, Config.BUFFER_PROPOSTA * sizeof(BProposta.buffer));

    memory_destroy(STR_SHM_RECEIPT_PTR, BOrcamento.ptr, Config.BUFFER_ORCAMENTO * sizeof(int));
    memory_destroy(STR_SHM_RECEIPT_BUFFER, BOrcamento.buffer, Config.BUFFER_ORCAMENTO * sizeof(BOrcamento.buffer));

    memory_destroy(STR_SHM_RECEIPT_PTR, BDescricao.ptr, Config.BUFFER_DESCRICAO * sizeof(int));
    memory_destroy(STR_SHM_RECEIPT_BUFFER, BDescricao.buffer, Config.BUFFER_DESCRICAO * sizeof(BDescricao.buffer));

    memory_destroy(STR_SHM_SCHEDULER, Schedule.ptr, sizeof(int[Config.SERVICOS][Config.EMPRESAS]));

    memory_destroy(STR_SHM_PRODEXCHANGES, Ind.propostas_entregues_por_empresas, sizeof(int) * Config.EMPRESAS * Config.SERVICOS);

    //eh necessario libertar a memohria que estah na struct statistics porque eh ela que guarda todos
    //os registos de funcionamento do sopepro
    free(Ind.capacidade_inicial_servicos);
    free(Ind.pid_clientes);
    free(Ind.pid_intermediarios);
    free(Ind.pid_empresas);
    free(Ind.clientes_servidos_por_intermediarios);
    free(Ind.clientes_servidos_por_empresas);
    free(Ind.servicos_recebidos_por_clientes);

	//==============================================
	// DESTRUIR MAPEAMENTO E NOME DE PÁGINAS DE MEMÓRIA
	//
	// utilizar a função genérica memory_destroy(char *,void *,int)
	//so_memory_destroy_all();
	//==============================================
}

//******************************************
// MEMORIA_REQUEST_D_ESCREVE
//
void memory_request_d_write(int id, struct service *pProduto) {
	prodcons_request_d_produce_begin();

	// registar hora do pedido de servico
	time_register(&pProduto->time_descricao);

	    BDescricao.buffer[BDescricao.ptr->in].id = pProduto->id; //escreve o id de pProduto no buffer de BDescricoes na posicao BDescricoes.ptr->in
    BDescricao.buffer[BDescricao.ptr->in].cliente = pProduto->cliente;
    BDescricao.buffer[BDescricao.ptr->in].time_descricao = pProduto->time_descricao;
    BDescricao.buffer[BDescricao.ptr->in].disponibilidade = 1;
    BDescricao.ptr->in=(BDescricao.ptr->in+1)%Config.BUFFER_DESCRICAO;
    //BDescricao.ptr->in = id; //atualizar o BDescricoes.ptr->in

	//==============================================
	// ESCREVER DESCRICAO DE PROJETO PESSOAL NO BUFFER DESCRICOES
	//
	// copiar conteudo relevante da estrutura pProduto para
	// a posicao BDescricoes.ptr->in do buffer BDescricoes
	// conteudo: cliente, id, time_descricao
	// colocar disponibilidade = 1 nessa posicao do BDescricoes
	// e atualizar BDescricoes.ptr->in
	//so_memory_request_d_write(id, pProduto);
	//==============================================

	prodcons_request_d_produce_end();

	// informar INTERMEDIARIO de pedido de SERVICO
	control_cliente_submete_descricao(id);

	// log
	file_write_log_file(1, id);
}
//******************************************
// MEMORIA_REQUEST_D_LE
//
int memory_request_d_read(int id, struct service *pProduto) {
	// testar se existem clientes e se o SO_PERPRO esta open
	if (control_intermediario_esperapor_descricao(id) == 0)
		return 0;

	prodcons_request_d_consume_begin();

	pProduto->cliente = BDescricao.buffer[BDescricao.ptr->out].cliente;
        pProduto->id = BDescricao.buffer[BDescricao.ptr->out].id;
        pProduto->time_descricao = BDescricao.buffer[BDescricao.ptr->out].time_descricao;
        BDescricao.buffer[BDescricao.ptr->out].disponibilidade = 0;
        BDescricao.ptr->out=(BDescricao.ptr->out+1)%Config.BUFFER_DESCRICAO;
        //BDescricao.ptr->out = id;

	//==============================================
	// LER DESCRICAO DO BUFFER DE DESCRICOES
	//
	// copiar conteudo relevante da posicao BDescricao.ptr->out
	// do buffer BDescricao para a estrutura pProduto
	// conteudo: cliente, id, time_descricao
	// colocar available = 0 nessa posicao do BDescricao
	// e atualizar BDescricao.ptr->out
	//so_memory_request_d_read(id, pProduto);
	//==============================================

	// testar se existe capacidade de servico para servir cliente
	if (prodcons_update_capacidade_servico(pProduto->id) == 0) {
		pProduto->disponibilidade = 0;
		prodcons_request_d_consume_end();
		return 2;
	} else
		pProduto->disponibilidade = 1;

	prodcons_request_d_consume_end();

	// log
	file_write_log_file(2, id);

	return 1;
}

//******************************************
// MEMORIA_REQUEST_B_ESCREVE
//
void memory_request_b_write(int id, struct service *pProduto) {
	int pos, empresa;

	prodcons_request_b_produce_begin();

	// decidir a que empresa se destina
	empresa = scheduler_get_empresa(id, pProduto->id);

	 for(int i = 0; i < Config.BUFFER_ORCAMENTO; i++){
	 	if(BOrcamento.ptr[i] == 0){
	           pos = i;
		   BOrcamento.buffer[pos].cliente = pProduto->cliente;
                   BOrcamento.buffer[pos].id = pProduto->id;
                   BOrcamento.buffer[pos].disponibilidade = pProduto->disponibilidade;
                   BOrcamento.buffer[pos].intermediario = pProduto->intermediario;
                   BOrcamento.buffer[pos].empresa = empresa;
                   BOrcamento.buffer[pos].time_descricao = pProduto->time_descricao;

         	   BOrcamento.ptr[pos] = 1;
		   break;
		}
         }

	//==============================================
	// ESCREVER ORCAMENTO NO BUFFER DE PEDIDOS DE ORCAMENTOS
	//
	// procurar posicao vazia no buffer BOrcamento
	// copiar conteudo relevante da estrutura pProduto para
	// a posicao BOrcamento.ptr do buffer BOrcamento
	// conteudo: cliente, id, disponibilidade, intermediario, empresa, time_descricao
	// colocar BOrcamento.ptr[n] = 1 na posicao respetiva
	//pos = so_memory_request_b_write(id, pProduto, empresa);
	//==============================================

	prodcons_request_b_produce_end();

	// informar empresa respetiva de pedido de orcamento
	control_intermediario_submete_orcamento(empresa);

	// registar hora pedido (orcamento)
	time_register(&BProposta.buffer[pos].time_orcamento);

	// log
	file_write_log_file(3, id);
}
//******************************************
// MEMORIA_REQUEST_B_LE
//
int memory_request_b_read(int id, struct service *pProduto) {
	// testar se existem pedidos e se o SO_PerPro esta open
	if (control_empresa_esperapor_orcamento(id) == 0)
		return 0;

	prodcons_request_b_consume_begin();

	for(int i = 0; i < Config.BUFFER_ORCAMENTO; i++){
      		if(BOrcamento.ptr[i] == 1){
        		pProduto->cliente = BOrcamento.buffer[i].cliente;
        		pProduto->id = BOrcamento.buffer[i].id;
        		pProduto->disponibilidade = BOrcamento.buffer[i].disponibilidade;
        		pProduto->time_descricao = BOrcamento.buffer[i].time_descricao;
        		pProduto->time_orcamento = BOrcamento.buffer[i].time_orcamento;

        		BOrcamento.ptr[i] = 0;
			break;
      }
    }

	//==============================================
	// LER PEDIDO DO BUFFER DE PEDIDOS DE ORCAMENTOS
	//
	// procurar pedido de orcamento para a empresa no buffer BOrcamento
	// copiar conteudo relevante da posicao encontrada
	// no buffer BOrcamento para pProduto
	// conteudo: cliente, id, disponibilidade, intermediario, time_descricao, time_orcamento
	// colocar BOrcamento.ptr[n] = 0 na posicao respetiva
	//so_memory_request_b_read(id, pProduto);
	//==============================================

	prodcons_request_b_consume_end();

	// log
	file_write_log_file(4, id);

	return 1;
}

//******************************************
// MEMORIA_RESPONSE_P_ESCREVE
//
void memory_response_p_write(int id, struct service *pProduto) {
	int pos;

	prodcons_response_p_produce_begin();

	    for(int i = 0; i < Config.BUFFER_PROPOSTA; i++){
      		if(BProposta.ptr[i] == 0){
		   pos = i;
        	   BProposta.buffer[pos].cliente = pProduto->cliente;
        	   BProposta.buffer[pos].id = pProduto->id;
		   BProposta.buffer[pos].disponibilidade = pProduto->disponibilidade;
        	   BProposta.buffer[pos].intermediario = pProduto->intermediario;
        	   BProposta.buffer[pos].empresa = pProduto->empresa;
        	   BProposta.buffer[pos].time_descricao = pProduto->time_descricao;
        	   BProposta.buffer[pos].time_orcamento = pProduto->time_orcamento;

        	   BProposta.ptr[pos] = 1;
	           break;
                }
            }

	//==============================================
	// ESCREVER PROPOSTA NO BUFFER DE PROPOSTAS
	//
	// procurar posicao vazia no buffer BProposta
	// copiar conteudo relevante da estrutura pProduto para
	// a posicao BProposta.ptr do buffer BProposta
	// conteudo: cliente, id, disponibilidade, intermediario, empresa, time_descricao, time_orcamento
	// colocar BProposta.ptr[n] = 1 na posicao respetiva
	//pos = so_memory_response_p_write(id, pProduto);
	//==============================================

	prodcons_response_p_produce_end();

	// informar cliente de que a proposta esta pronta
	control_empresa_submete_proposta(pProduto->cliente);

	// registar hora pronta (proposta)
	time_register(&BProposta.buffer[pos].time_proposta);

	// log
	file_write_log_file(5, id);
}
//******************************************
// MEMORIA_RESPONSE_P_LE
//
void memory_response_p_read(int id, struct service *pProduto) {
	// aguardar proposta
	control_cliente_esperapor_proposta(pProduto->cliente);

	prodcons_response_p_consume_begin();

	for(int i = 0; i < Config.BUFFER_PROPOSTA; i++){
      		if(BProposta.ptr[i] == 1){
        	   pProduto->cliente = BProposta.buffer[i].cliente;
        	   pProduto->disponibilidade = BProposta.buffer[i].disponibilidade;
        	   pProduto->intermediario = BProposta.buffer[i].intermediario;
        	   pProduto->empresa = BProposta.buffer[i].empresa;
        	   pProduto->time_descricao = BProposta.buffer[i].time_descricao;
        	   pProduto->time_orcamento = BProposta.buffer[i].time_orcamento;
        	   pProduto->time_proposta = BProposta.buffer[i].time_proposta;

        	   BProposta.ptr[i] = 0;
		   break;
      		}
        }

	//==============================================
	// LER PROPOSTA DO BUFFER DE PROPOSTAS
	//
	// procurar proposta para o cliente no buffer BProposta
	// copiar conteudo relevante da posicao encontrada
	// no buffer BProposta para pProduto
	// conteudo: cliente, disponibilidade, intermediario, empresa, time_descricao, time_orcamento, time_proposta
	// colocar BProposta.ptr[n] = 0 na posicao respetiva
	//so_memory_response_p_read(id, pProduto);
	//==============================================

	prodcons_response_p_consume_end();

	// log
	file_write_log_file(6, id);
}

//******************************************
// MEMORIA_CRIAR_INDICADORES
//
void memory_create_statistics() {

	Ind.capacidade_inicial_servicos = (int*) calloc(Config.SERVICOS, sizeof(int));
	Ind.pid_clientes = (int*) calloc (Config.CLIENTES, sizeof(int));
	Ind.pid_intermediarios = (int*) calloc (Config.INTERMEDIARIO, sizeof(int));
	Ind.pid_empresas = (int*) calloc (Config.EMPRESAS, sizeof(int));
	Ind.clientes_servidos_por_intermediarios = (int*) calloc(Config.INTERMEDIARIO, sizeof(int));
	Ind.clientes_servidos_por_empresas = (int*) calloc(Config.EMPRESAS, sizeof(int));
	Ind.servicos_recebidos_por_clientes = (int*) calloc(Config.SERVICOS, sizeof(int));
	Ind.propostas_entregues_por_empresas = (int*) memory_create(STR_SHM_PRODEXCHANGES, sizeof(int) * Config.EMPRESAS);

  	memcpy(Ind.capacidade_inicial_servicos, Config.capacidade_servico, sizeof(int) * Config.SERVICOS);

	//==============================================
	// CRIAR ZONAS DE MEMÓRIA PARA OS INDICADORES
	//
	// criação dinâmica de memória
	// para cada campo da estrutura indicadores
	//so_memory_create_statistics();
	// iniciar indicadores relevantes:
	// - Ind.capacidade_inicial_servicos (c/ Config.capacidade_servico respetivo)
	// - Ind.clientes_servidos_por_intermediarios (c/ 0)
	// - Ind.clientes_servidos_por_empresas (c/ 0)
	// - Ind.serviços_recebidos_por_clientes (c/ 0)
	so_memory_prepare_statistics();
	//==============================================
}
