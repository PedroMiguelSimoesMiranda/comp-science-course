global cifra_cesar
section .text

cifra_cesar:
     push ebp
     mov ebp,esp
     mov edx, [ebp+8] ; string
     mov eax,0
     mov ebx, [ebp+12] ; deslocamento
ciclo:
     mov cl , [edx+eax]
     cmp cl, 0
     je fim

     cmp cl, 'A'
     jl  notransform

     cmp cl, 'Z'
     jle transform

     cmp cl, 'a'
     jl  notransform

     cmp cl, 'z'
     jle transform
     jmp notransform

transform:
     add cl,bl
     cmp cl,'Z'
     jle notransform

     cmp cl, 'a'
     jl correct

     cmp cl, 'z'
     jle  notransform

correct:     
     sub cl, 26 

notransform: 
     mov [edx+eax],cl
     inc eax
     jmp ciclo

fim:
     pop ebp
     ret

