; aula 06 exercicio 2

; exportar label de inicio do programa
global _start
section .data    ; seccao data - dados inicializados
; variaveis de 32 bits
x:   dd   3
y:   dd   2
section .text    ; seccao text - codigo
_start:   nop            ;  dummy

; x^y

mov eax, [x] ; 		 COLOCAR BREAKPOINT AQUI
mov ebx, [y]
mov edx, 1    ; int i = 1 no ciclo

;if y == 0 THEN X = 1
cmp edx,ebx
jg tag
;if y==1 THEN X = X
;cmp edx, 1
je fim
ciclo_for: cmp edx, ebx ; for( i =1 ; i <= y ; i++)
	   jge fim
	   mul eax 
	   inc edx ;i++
	   jmp ciclo_for

tag: xor eax, eax ; eax<-0
	add eax, 1;eax<-1
	jmp fim		
fim: mov [res], eax


; chamada ao sistema para terminar o programa

     mov  eax, 1         ; codigo de "exit"
     mov  ebx, 0         ; valor retornado ao sistema
     int  0x80           ; chamada ao sistema

