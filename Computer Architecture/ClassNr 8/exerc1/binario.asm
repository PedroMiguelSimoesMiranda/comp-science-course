; AULA 8
global binario
section .text

binario:
     push ebp
     mov ebp,esp
     push ebx
     push ecx
     mov edx, [ebp+8]
     mov eax,0
     mov ebx,1
     mov cl , [edx]
ciclo:
     mov cl , [edx+eax]
     cmp cl, 0
     je fim
     cmp cl ,'0'
     je increm
     cmp cl ,'1'
     je increm
     mov ebx,0
     jmp fim

increm:
    inc eax
    jmp ciclo

fim:
     cmp ebx,1
     je valordecimal
fimf:mov eax , ebx
     pop ebx
     pop ebp
     ret

valordecimal:		
		;ecx = numero
                ;bl = caracter string
		;eax = deslocamento
		;edx= apontador para string
     mov eax, 0
     mov ecx, 0

cicloshift:
     mov bl,[edx+eax]
     cmp bl,0
     je  fimciclo
     shl ecx, 1 ; fazer shift pa esquerda
     cmp bl,'1'
     jne notone
;isone     
     or ecx, 0x0001
notone:
     inc eax
     jmp cicloshift
fimciclo:
     mov eax , ecx
     pop ebx
     pop ecx
     pop ebp
     ret



;1010


;0000001
;0000010
;0000100
;0000101
;0001010


