;aula 05 exercicio C2



; exportar label de inicio do programa
global _start
section .data    ; seccao data - dados inicializados
; variaveis de 32 bits
x:    dd   150
res:  dd   0
z:    dd   1
section .text    ; seccao text - codigo
_start:   nop            ;  dummy

;res = x & ~1

      mov eax, [x]
      not dword [z]
      mov ebx, [z]
      and eax, ebx
      mov  [res], eax
; chamada ao sistema para terminar o programa
     mov  eax, 1         ; codigo de "exit"
     mov  ebx, 0         ; valor retornado ao sistema
     int  0x80           ; chamada ao sistema


