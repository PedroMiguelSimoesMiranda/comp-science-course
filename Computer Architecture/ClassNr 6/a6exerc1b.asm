; aula 06 exercicio 1b
; exportar label de inicio do programa
global _start
section .data    ; seccao data - dados inicializados
; variaveis de 32 bits
n:   dd   0
i:   dd   1
section .text    ; seccao text - codigo
_start:   nop            ;  dummy

; int factorial(int n){
;	int fact=1
;	int i 
;	for(i = 1; i<=n;i++))   while(n>1) fact*=n 
;		fact*=i         n--
;	return fact
;}

;1*2*3*...*n



mov eax,[n]				;  COLOCAR BREAKPOINT AQUI
call factorial				; chamada a subrotina
mov [res],eax

; ecx -> n
; ebx -> i
; eax-> fact

factorial:mov ecx,eax ; ecx <- n
	mov eax, 1      ; eax <- fact
	mov ebx,1        ; ebx <- i
ciclo_for: cmp ebx,ecx   
	jg fim ;if ebx > ecx ( i > n ) jmp fim 
	mul ebx ; fact*=i  mul ebx
	inc ebx ; i++ inc ebx
	jmp ciclo_for 
fim: ret

; chamada ao sistema para terminar o programa

     mov  eax, 1         ; codigo de "exit"
     mov  ebx, 0         ; valor retornado ao sistema
     int  0x80           ; chamada ao sistema

