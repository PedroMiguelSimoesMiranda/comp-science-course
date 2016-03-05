; AULA 8
global _start
section .text

; variaveis de 8 bits
s: dd 1
t: dd 2

; seccao text - codigo
section .text

_start:    nop          ;  dummy
    push dword [s]
    push dword [t] 
    call troca
    add esp, 8
    mov  eax, 1        	; codigo de "exit"
    mov  ebx, 0        	; valor retornado ao sistema
    int  0x80           ; chamada ao sistema

troca:
     push ebp			
     mov ebp,esp		
     mov eax, [ebp+8]		;   poe o endereço do x no eax
     mov ebx, eax		;   mov o valor x e move para um registo ebx
     mov edx, [ebp+12]		;   poe o endereço do y no edx
     mov ecx, edx		;   mov o valor y e move para um registo ecx
     mov edx, ebx		;   poe o x no sitio onde esta o y 
     mov eax, ecx		;   poe o y no sitio onde esta o x
     pop ebp
     ret


