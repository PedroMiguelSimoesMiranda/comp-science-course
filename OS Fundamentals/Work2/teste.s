	.file	"teste.c"
	.text
.globl sum
	.type	sum, @function
sum:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$16, %esp
	movl	$0, -4(%ebp)
	jmp	.L2
.L3:
	movl	num, %eax
	addl	$1, %eax
	movl	%eax, num
	addl	$1, -4(%ebp)
.L2:
	movl	niter, %eax
	cmpl	%eax, -4(%ebp)
	jb	.L3
	movl	8(%ebp), %eax
	leave
	ret
	.size	sum, .-sum
	.section	.rodata
.LC0:
	.string	"Usage: %s N_ITER \n"
.LC1:
	.string	"pthread_create"
.LC2:
	.string	"%lu\n"
	.text
.globl main
	.type	main, @function
main:
	leal	4(%esp), %ecx
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ecx
	subl	$36, %esp
	movl	%ecx, -24(%ebp)
	movl	-24(%ebp), %eax
	cmpl	$2, (%eax)
	je	.L7
	movl	-24(%ebp), %edx
	movl	4(%edx), %eax
	movl	(%eax), %eax
	movl	%eax, 4(%esp)
	movl	$.LC0, (%esp)
	call	printf
	movl	$1, (%esp)
	call	exit
.L7:
	movl	-24(%ebp), %edx
	movl	4(%edx), %eax
	addl	$4, %eax
	movl	(%eax), %eax
	movl	$10, 8(%esp)
	movl	$0, 4(%esp)
	movl	%eax, (%esp)
	call	strtoul
	movl	%eax, niter
	movl	$0, num
	movl	$0, 12(%esp)
	movl	$sum, 8(%esp)
	movl	$0, 4(%esp)
	leal	-8(%ebp), %eax
	movl	%eax, (%esp)
	call	pthread_create
	testl	%eax, %eax
	jns	.L9
	movl	$.LC1, (%esp)
	call	perror
	movl	$0, (%esp)
	call	exit
.L9:
	movl	$0, 12(%esp)
	movl	$sum, 8(%esp)
	movl	$0, 4(%esp)
	leal	-12(%ebp), %eax
	movl	%eax, (%esp)
	call	pthread_create
	testl	%eax, %eax
	jns	.L11
	movl	$.LC1, (%esp)
	call	perror
	movl	$0, (%esp)
	call	exit
.L11:
	movl	-8(%ebp), %eax
	movl	$0, 4(%esp)
	movl	%eax, (%esp)
	call	pthread_join
	movl	-12(%ebp), %eax
	movl	$0, 4(%esp)
	movl	%eax, (%esp)
	call	pthread_join
	movl	num, %eax
	movl	%eax, 4(%esp)
	movl	$.LC2, (%esp)
	call	printf
	movl	$0, %eax
	addl	$36, %esp
	popl	%ecx
	popl	%ebp
	leal	-4(%ecx), %esp
	ret
	.size	main, .-main
	.comm	num,4,4
	.comm	niter,4,4
	.ident	"GCC: (GNU) 4.2.3 (Ubuntu 4.2.3-2ubuntu7)"
	.section	.note.GNU-stack,"",@progbits
