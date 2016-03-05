#include <stdio.h>
#include <stdlib.h>
#include "Machine.h"

#define STACK_SIZE   20000

Word code[CODE_SIZE];
static Word data[STACK_SIZE];

static Addr SP = STACK_SIZE; // Stack pointer
static Addr FP = STACK_SIZE; // Frame pointer
static Addr PC = 0; // Program counter

#define PUSH(v)      (data[--SP] = (Word)(v))
#define POP()        (data[SP++])
#define OFFSET(a,o)  ((a)-(o))

#define DL(fp)     (data[fp])
#define SL(fp)     (data[fp-1])
#define PC(fp)     (data[fp-2])

static void LoadVarAddr(int nesting, int offset) {
	Addr addr;
	for (addr = FP; nesting--; addr = (Addr) SL(addr))
		; // find the frame of the var
	PUSH(OFFSET(addr,offset)); // push the var address
}

static void Call(int nesting, Addr addr) {
	PUSH(FP); // push dynamic link
	if (nesting == -1)
		PUSH(FP); // push static link
	else {
		Addr pt;
		for (pt = FP; nesting--; pt = (Addr) SL(pt))
			; // find the frame of the called function
		PUSH(SL(pt)); // push static link
	}
	PUSH(PC); // push return address
	PC = addr; // jump to the function
}

static void Enter(int locSpace) {
	FP = SP + 2; // FP points to the dynamic link inside the frame
	while(locSpace--) PUSH(0);
}

static void Exit(int argSpace) {
	Word res = POP(); // save the result here
	SP = FP + argSpace + 1; // discard the entire frame, including the arguments
	PC = PC(FP); // restore the program counter
	FP = DL(FP); // restore the previous frame pointer
	PUSH(res); // push the result
}

#define POP_TWO()	a = POP(); b = POP()
#define POP_ONE()	a = POP()
#define GET_ONE()	v = code[PC++]

void Run(void) {
	Word a, b, v;
	PC = 0;
    SP = STACK_SIZE;
    FP = SP-1;
	for (;;) {

	//printf("Processing %d -> %u\n",PC,code[PC]);

	  switch (code[PC++]) {
		case X_NOP:	break;
		case X_HALT: 	return;
		case X_ADD:	POP_TWO(); PUSH(a + b); break;
		case X_LD:	GET_ONE(); PUSH(v); break;
		case X_BR:	PC = code[PC]; break;
		case X_SUB:	POP_TWO(); PUSH(a - b); break;
		case X_MUL: 	POP_TWO(); PUSH(a * b); break;
		case X_DIV: 	POP_TWO(); PUSH(a / b); break;
		case X_AND: 	POP_TWO(); PUSH(a && b); break;
		case X_OR:	POP_TWO(); PUSH(a || b); break;
		case X_EQ:	POP_TWO(); PUSH(a == b); break;
		case X_GT:	POP_TWO(); PUSH(a >= b); break;
		case X_NOT:	POP_ONE(); PUSH(!a); break;
		case X_BRF:	POP_ONE(); if( a == 0 ) PC = code[PC]; else PC++ ; break;
        	case X_CAL:	GET_ONE(); POP_ONE(); Call(a,v); break;
        	case X_RET:	GET_ONE(); Exit(v); break;
		case X_LOC:	GET_ONE(); Enter(v); break;
      		case X_STV:	POP_TWO(); data[a] = b; break;
		case X_LDA:	POP_TWO(); LoadVarAddr(b,a); break;
        	case X_LDV:	POP_ONE(); PUSH(data[a]); break;
       	 	case X_WR:	POP_ONE(); printf("%u\n", a) ; break;
       		case X_RD:	printf("> "); scanf("%u", &v); PUSH(v); break;
		default: printf("Not implemented! %u\n",code[PC-1]); return;
	  }
	}
}
