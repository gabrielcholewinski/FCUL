/**
 * Cria o segmento de memoria partilhada que contem o buffer onde e
 * guardado o valor do produtor para o consumidor.  Esta variavel vai ser
 * partilhada pelos processos produtor e consumidor.
 */
void iniciaBuffer ();

/**
 * Liberta o segmento de memoria partilhada.
 */
void destroiBuffer ();

/**
 * Escreve o valor no buffer. Bloqueia se o buffer estiver cheio.
 */
void escreveBuffer (int valor);

/**
 * Le o valor guardado no buffer. Bloqueia se o buffer estiver vazio.
 */
int leBuffer ();
