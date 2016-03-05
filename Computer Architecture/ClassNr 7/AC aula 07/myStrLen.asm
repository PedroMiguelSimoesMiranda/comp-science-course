; aula 07 alinea B exercicios MYSTRLEN ASM aline b
; exportar label de inicio do programa
global _start

section .data    ; seccao data - dados inicializados
; variaveis de 32 bits
s: dd 'O' 'L' 'A' 
section .text
_start:   nop 
;Programe uma subrotina que calcula o comprimento de uma
;string, com os parâmetros a serem passados pelo stack.
push dword n
call mystrlen
mov [res],eax

; chamada ao sistema para terminar o programa
     mov  eax, 1         ; codigo de "exit"
     mov  ebx, 0         ; valor retornado ao sistema
     int  0x80           ; chamada ao sistema

;eax vai ser o contador


myStrLen:
	push ebp  ; string ta nos argumentos da pilha
	mov ebp,esp
	xor eax,eax
	mov ebx,[esp+8];;?????????????

for_my: mov al, [ebx]
	cmp al, 0 	; comparar se chegou ao fim
	je fim
	inc ebx
	inc eax
	jmp for_my
 
fim: 	pop ebx    ; repor ebx
	mov esp,ebp ; repor pointers
	pop ebp	 ; repor ebp
	ret

