global cifra_cesar

section .data  

section .text

cifra_cesar:
     push ebp
     mov ebp,esp
     mov edx, [ebp+8] ; NUMERO 
     mov eax,0
     mov ebx, [ebp+12] ; STRING
     mov cl , [edx];
ciclo:
     mov cl , [edx+eax]
     cmp cl, 0
     je fim
     add cl,bl
     cmp cl, 'z'
     jl  ok
     sub cl, 26
ok:  mov [edx+eax],cl
     inc eax
     jmp ciclo
fim:
     pop ebp
     ret

