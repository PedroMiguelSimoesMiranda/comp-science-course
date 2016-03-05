;aula 05 exercicio C3

; exportar label de inicio do programa
global _start
section .data    ; seccao data - dados inicializados
; variaveis de 32 bits
x:   dd   2
y:   dd   2
z:   dd   3
res: dd   0
section .text    ; seccao text - codigo
_start:   nop            ;  dummy
; x += x * y + 3;
mov eax,[x]	;  COLOCAR BREAKPOINT AQUI
mov ebx,[y]
mul ebx
add eax,[z]
mov [x],eax
; chamada ao sistema para terminar o programa
     mov  eax, 1         ; codigo de "exit"
     mov  ebx, 0         ; valor retornado ao sistema
     int  0x80           ; chamada ao sistema

