;aula 05 exercicio B1
;apontadores

; exportar label de inicio do programa
global _start
section .data    ; seccao data - dados inicializados
; variaveis de 32 bits
x:   dd   2
y:   dd   2
res: dd   0

section .text    ; seccao text - codigo
_start:   nop            ;  dummy
; res = *ptrx + *ptry
     mov  eax, x    ;    eax=&x    COLOCAR BREAKPOINT AQUI
     mov ebx, [eax]   ; ebx=&x
     mov  eax, y   ; eax=&y
     add ebx, [eax]  ; ebx= &y+&x
     mov  [res],ebx  ; res = ebx

 
; chamada ao sistema para terminar o programa
     mov  eax, 1         ; codigo de "exit"
     mov  ebx, 0         ; valor retornado ao sistema
     int  0x80           ; chamada ao sistema

