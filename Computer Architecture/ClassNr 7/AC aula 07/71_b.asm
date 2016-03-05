global _start
section .data  

n:   db   'aviao',0
res: dd   0
section .text    ; seccao text - codigo
_start:   nop            ;  dummy

push dword n
call mystrlen
mov [res],eax

	mov  eax, 1         ; codigo de "exit"
    mov  ebx, 0         ; valor retornado ao sistema
    int  0x80           ; chamada ao sistema


mystrlen:
     push ebp
     mov ebp,esp
     mov edx, [ebp+8]
     mov eax,0
     mov cl , [edx]
ciclo:
     mov cl , [edx+eax]
     cmp cl, 0
     je fim
     inc eax
     jmp ciclo
fim:
     pop ebp
     ret

