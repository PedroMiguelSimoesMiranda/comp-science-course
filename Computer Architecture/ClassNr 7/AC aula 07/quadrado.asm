; aula 7 exercicio 1 QUADRADO

global quadrado
section .text
quadrado:
     push ebp
     mov ebp,esp
     mov eax, [ebp+8]
     mul eax
     pop ebp
     ret

quadrado: push ebp
		mov ebp,esp
		mov eax,[ebp+8]
		mul eax
		pop ebp