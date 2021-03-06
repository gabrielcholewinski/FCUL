;Desenvolvido por: Ruhan Victor[51779] & Tiago Carvalho[51034]
;Este programa calcula a adição ou subtração de números de 0-9 contidos no display de Segmento, e devolve o valor resultado da operação no mesmo.
display: resb 4
v_display: db 00111111b, 00000110b, 01011011b, 01001111b, 01100110b, 01101101b, 01111101b, 00000111b, 01111111b, 01101111b
mov cl, byte [display]
mov edx, -1
ciclo1: ;1°Operando
	inc edx 
	cmp [edx+v_display], cl 
	jne ciclo1 
	mov eax, edx
xor edx,edx
mov edx, -1
mov cl, byte [display+2]
ciclo2: ;2°Operando
	inc edx 
	cmp [edx+v_display], cl 
	jne ciclo2 
	mov ebx, edx
xor edx, edx
mov cl, byte [display+1]
jmp signal
;loop de comparação do calculo
;ciclo3: ;resultado
	;inc edx
	;cmp eax, edx
	;jne ciclo3
	;mov ecx, [edx+v_display]
	;mov [0x0], ecx
	;xor edx, edx
	;jmp fim
ciclo3: 	
	mov cl, [eax*1+v_display]
	mov [0x0], cl
	jmp fim
signal:
	cmp cl, 0
	je soma
	neg eax
soma:
	add eax, ebx
	mov edi, eax
	cmp eax, 0
	jg compare
	neg eax
compare:
	mov byte [0x2], 0 ;apaga o 2operando
	cmp eax, 10
	jge dezenas
	mov byte [0x1], 10111111b
	jmp sinal
	
	
dezenas:
	mov byte [0x1], 00000110b
	sub eax, 10
	jmp sinal
sinal:
	cmp eax,0
	jl negativo
positivo:
	cmp edi,0
	jl negativo
	mov byte [0x3], 01110011b
	jmp ciclo3
negativo:
	mov byte [0x3], 11010100b
	jmp ciclo3
fim:                                     