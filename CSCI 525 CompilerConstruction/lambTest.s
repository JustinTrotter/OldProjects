	.file	"lambTest.c"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	$0, -8(%rbp)
	movl	$1, -4(%rbp)
	cmpl	$2, -4(%rbp)
	jne	.L2
	movl	-4(%rbp), %eax
	addl	$1, %eax
	movl	%eax, -8(%rbp)
	jmp	.L4
.L2:
	movl	-8(%rbp), %eax
	addl	$2, %eax
	movl	%eax, -4(%rbp)
.L4:
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 4.8.2-19ubuntu1) 4.8.2"
	.section	.note.GNU-stack,"",@progbits
