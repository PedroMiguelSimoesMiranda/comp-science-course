global cifra_cesar
section .text

cifra_cesar:
     push ebp
     mov ebp,esp
     mov ebx, [ebp+8];numero
     mov eax,0;contador
     mov edx, [ebp+12];string
     mov cl , [edx]
ciclo: mov cl , [edx+eax]
    cmp cl, 0
	je fim 
	add [cl],ebx
	cmp cl,'z'
	jg ok
	sub [cl],26
	
ok: mov[edx+eax],cl
	inc eax
	jmp ciclo
fim:pop ebp
	ret
	 
	
