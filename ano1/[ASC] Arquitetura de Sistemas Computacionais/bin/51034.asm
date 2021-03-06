;Desenvolvido por: Ruhan Victor[51779] & Tiago Carvalho[51034]
;Este programa calcula a adição ou subtração de números de 0-9 contidos na consola, e devolve o valor resultado da operação na mesma.
SECTION .data
buffer: db 'Introduza como parametros dois numeros naturais e a operacao: num1 +/- num2',10 ;mensagem de erro
SECTION .text

global main

extern printinteger

main: 
     xor eax, eax 
     mov ebx, [esp+8]      ; argv 
     mov ecx, [esp+4]      ; argc 
     cmp ecx, 4
     je calc
     dec ecx         ; descartar nome do programa 
     ;MENSAGEM DE ERRO
     mov eax, 4      ;Código da chamada write 
     mov ebx, 1      ;Descritor do standard output 
     mov ecx, buffer ;Endereço do buffer de escrita 
     mov edx, 75      ;Número de caracteres a escrever 
     int 0x80        ;Execução da interrupção de software 
     ret 
     jmp sair
     
calc:
     xor eax, eax ;limpar os registos
     xor ecx, ecx
     xor edx, edx
     
     add ebx, 4 ;descarta o nome do programa
     
     mov eax, [ebx] ;encontra o endereco do 1o operando
     mov cl, [eax]
     
     mov eax, [ebx+4] ;encontra o endereco do sinal
     mov dl, [eax]
     
     mov eax, [ebx+8] ;encontra o endereco do 2o operando
     xor ebx, ebx
     mov bl, [eax]
     xor eax, eax
     
     encontraNum1:
          and cl, 0x0f
          ;cl = valor do primeiro operando 0-9
     encontraNum2:
          and bl, 0x0f
          ;bl: = valor do segundo operando 0-9
     encontraSinal: 
          cmp dl, 43
          jne subtracao
          ;dl: Sinal 43=+  45= -
          
soma:
     add ecx, ebx ;adiciona o 1o operando com o 2o
     jmp dev
subtracao:
     sub ecx, ebx ;subtrai o 1o operando com o 2o
dev:
     mov eax, ecx ;prepara o eax para ser recebido pelo printnumbers
     push eax
call printinteger ;chama funcao printinteger
add esp, 4 ;repoe a pilha
sair: 
     ret 
fim:
              