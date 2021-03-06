#ifndef PRODCONS_H_GUARD
#define PRODCONS_H_GUARD

// Nomes usados na criacao dos semaforos
#define STR_SEM_STOCK_MUTEX			"sem_stock_mutex"
#define STR_SEM_REQUEST_FULL 		"sem_request_full"
#define STR_SEM_REQUEST_EMPTY 		"sem_request_empty"
#define STR_SEM_REQUEST_MUTEX 		"sem_request_mutex"
#define STR_SEM_ORDER_FULL 			"sem_order_full"
#define STR_SEM_ORDER_EMPTY 		"sem_order_empty"
#define STR_SEM_ORDER_MUTEX 		"sem_order_mutex"
#define STR_SEM_RECEIPT_FULL 		"sem_receipt_full"
#define STR_SEM_RECEIPT_EMPTY 		"sem_receipt_empty"
#define STR_SEM_RECEIPT_MUTEX 		"sem_receipt_mutex"

struct prodcons {
	// semáforos de controlo do acesso ao buffer entre empresas e clientes
	sem_t *receipts_r_full, *receipts_r_empty, *receipts_r_mutex;
	// semáforos de controlo do acesso ao buffer entre intermediarios e empresas
	sem_t *request_e_full, *request_e_empty, *request_e_mutex;
	// semáforos de controlo do acesso ao buffer entre clientes e intermediarios
	sem_t *request_p_full, *request_p_empty, *request_p_mutex;
	// semaforo para exclusao mutua no acesso a capacidade de servicos
	sem_t *stock_mutex;
};

sem_t * semaphore_create(char*, int);
void prodcons_create_stock();
void prodcons_create_buffers();
void prodcons_destroy();
void prodcons_request_d_produce_begin();
void prodcons_request_d_produce_end();
void prodcons_request_d_consume_begin();
void prodcons_request_d_consume_end();
void prodcons_request_b_produce_begin();
void prodcons_request_b_produce_end();
void prodcons_request_b_consume_begin();
void prodcons_request_b_consume_end();
void prodcons_response_p_produce_begin();
void prodcons_response_p_produce_end();
void prodcons_response_p_consume_begin();
void prodcons_response_p_consume_end();
void prodcons_buffers_begin();
void prodcons_buffers_end();
int prodcons_update_capacidade_servico(int);

#endif
