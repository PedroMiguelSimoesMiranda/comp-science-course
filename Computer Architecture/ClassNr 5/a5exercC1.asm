;aula 05 exercicio C1

; exportar label de inicio do programa
;exercicio C
global _start
section .data    ; seccao data - dados inicializados
; variaveis de 32 bits
x:    dd   2
y:    dd   2
res:  dd   0
res2: dd   0
z:    dd   1
section .text    ; seccao text - codigo
_start:   nop            ;  dummy
; x = (x+y)+(x+y) - 1   -> 2 + 2 + 2+2 -1 
     mov  eax, [x]     ;  COLOCAR BREAKPOINT AQUI
     add  eax, [y]
     mov  [res], eax
     mov  eax, [x]
     add  eax, [y]
     mov  [res2], eax
     mov  eax, [res]
     add  eax, [res2]
     mov  [x], eax
     sub  eax, [z]
     mov [x], eax
     
     
; chamada ao sistema para terminar o programa
     mov  eax, 1         ; codigo de "exit"
     mov  ebx, 0         ; valor retornado ao sistema
     int  0x80           ; chamada ao sistema

	 

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
