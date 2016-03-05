#include <stdio.h>
#include <stdlib.h>

#define STACK_SIZE   20000
#define CODE_SIZE	20000
#define HEAP_SIZE	20000

typedef unsigned Word;
typedef unsigned Addr;
int free;
Word data[HEAP_SIZE+STACK_SIZE];
Word code[CODE_SIZE];

Addr SP = HEAP_SIZE+STACK_SIZE; // Stack pointer
Addr FP = HEAP_SIZE+STACK_SIZE; // Frame pointer
Addr PC = 0; // Program counter

#define PUSH(v)		(data[--SP] = (Word)(v))
#define POP()		(data[SP++])
#define OFFSET(a,o)	((a)-(o))

#define DL(fp)		(data[fp])
#define SL(fp)		(data[fp-1])
#define PC(fp)		(data[fp-2])

#define X_NOP  0x0000
#define X_HALT 0x00FF
#define X_ADD  0x0001
#define X_LD   0x0002  // One extra argument in the code segment
#define X_BR   0x0003
#define X_SUB  0x0004
#define X_MUL  0x0005
#define X_DIV  0x0006
#define X_AND  0x0007
#define X_OR   0x0008
#define X_EQ   0x0009
#define X_GT   0x000A
#define X_NOT  0x000B
#define X_BRF  0x000C  // One extra argument in the code segment
#define X_CAL  0x000D  // One extra arguments in the code segment
#define X_RET  0x000E
#define X_LOC  0x000F
#define X_STV  0x0011
#define X_LDV  0x0010
#define X_LDA  0x0012
#define X_ALL  0x0013
#define X_FRE  0x0014

void PushConst(Word c) {
	PUSH(c); // push a constant into the stack
}

void Add(void) {
	Word arg1 = POP(); // pop the first argument
	Word arg2 = POP(); // pop the second argument
	PUSH(arg1 + arg2); // push the result
}

void LoadVarAddr(int nesting, int offset) {
	Addr addr;
	for (addr = FP; nesting--; addr = (Addr) SL(addr))
		; // find the frame of the var
	PUSH(OFFSET(addr,offset)); // push the var address
}

void Value() {
	Addr pt = POP(); // pop the var address
	PUSH(data[pt]); // push the value of the variable
}

void Assign() {
	Addr pt = POP(); // pop the var address
	Addr value = POP(); // pop the value to store
	data[pt] = value; // do the assignement
}

void Call(int nesting, Addr addr) {
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

void Enter(int locSpace) {
	FP = SP + 2; // FP points to the dynamic link inside the frame
	SP -= locSpace;
}

void Exit(int argSpace) {
	Word res = POP(); // save the result here
	SP = FP + argSpace; // discard the entire frame, including the arguments
	PC = PC(FP); // restore the program counter
	FP = DL(FP); // restore the previous frame pointer
	PUSH(res); // push the result
}

void Sub(int args)[{
	Word v1=POP();
	Word v2=POP();
	PUSH(v1-v2);

}

void Mul(){
	Word v1=POP();
	Word v2=POP();
	PUSH(v1*v2);
}

void Div(){
	Word v1=POP();
	Word v2=POP();
	PUSH(v1/v2);
}

void And(){
	Word v1=POP();
	Word v2=POP();
	PUSH(v1&&v2);
}

void Or(){
	Word v1=POP();
	Word v2=POP();
	PUSH(v1||v2);
}

void Eq(){
	Word v1=POP();
	Word v2=POP();
	PUSH(v1==v2);
}

void Gt(){
	Word v1=POP();
	Word v2=POP();
	PUSH(v1>v2);
}

void Not(){
	Word v1=POP();
	PUSH(!v1);
}

void Brf(){
	Word v1=POP();
	if(v1==0) 
		PC = code[PC++];//jump
}

void Loc(int e){
	 v1=code[PC++];
	 Enter(v1);
	for(int i = code[PC++];i>0;i--)
		data[SP+i];
}
void setValue(void){
	addr pt=POP();
	data[pt]=POP();
}

void All(void){
	
	int size=POP();
	int bool=1;
	int curr = free;
	while(bool){
		if(data[curr]-size>1){
			bool=0;
			data[curr+1+size]=data[curr]-size;
			free=data[curr];
			data[curr]=size;
		}
	else{
		curr=data[curr+1];
		}
	}

}



void Run() {
	for (;;) {
	  switch (code[PC++]) {
		case X_NOP:
			break;
		case X_ADD:
			Add();
			break;
		case X_LD:
			PushConst(code[PC++]);
			break;
		case X_BR:
			PC = code[PC-
			];
			break;
		case X_HALT:
			return;
		case X_SUB:
			Sub();
			break;
		case X_MUL:
			Mul();
			break;
		case X_DIV:
			Div();
			break;
		case X_AND:
			And();
			break;
		case X_OR:
			Or();
			break;
		case X_EQ:
			Eq();
			break;
		case X_GT:
			Gt();
			break;
		case X_NOT:
			Not();
			break;
		case X_BRF:
			Brf();
			break;
		case X_LDA:
			LoadVarAddr(POP(),POP());
			break;
		case X_LDV:
			Value();
			break;
		case X_STV:
			setValue();
			break;
		case X_CAL:
			Call(POP(),POP());
			break;
		case X_LOC:
			Loc();
			break;
		case X_RET:
			Exit(SP-FP-3);
			break;
		case X_ALL:
			All();
		default:
			printf("Not implemented! %u\n",code[PC-1]);
			return;
		}
	}
}

void test1() {
	code[0x00] = X_LD;
	code[0x01] = 2u;
	code[0x02] = X_LD;
	code[0x03] = 2u;
	code[0x04] = X_LD;
	code[0x05] = 3u;
	code[0x06] = X_ADD;
	code[0x07] = X_ADD;
	code[0x08] = X_HALT;
	PC = 0;
	SP = STACK_SIZE+HEAP_SIZE;
	FP = SP-1;
	Run();
	printf("%u\n",data[SP]);
}

void test2() {
	code[0x00] = X_BR;
	code[0x01] = 0x0B;
	code[0x02] = X_LD;
	code[0x03] = 2u;
	code[0x04] = X_LD;
	code[0x05] = 2u;
	code[0x06] = X_LD;
	code[0x07] = 4u;
	code[0x08] = X_ADD;
	code[0x09] = X_ADD;
	code[0x0A] = X_HALT;
	code[0x0B] = X_BR;
	code[0x0C] = 0x02;
	PC = 0;
	SP = STACK_SIZE+HEAP_SIZE;
	FP = SP-1;
	Run();
	printf("%u\n",data[SP]);
}

void test3() {
	// THIS EXAMPLES STARTS RUNNING AT 0x0015
	code[0x0001] = X_LOC;
	code[0x0002] = 1u;
	code[0x0003] = X_LD; // load global variable
	code[0x0004] = 1u;
	code[0x0005] = X_LD;
	code[0x0006] = 3u;
	code[0x0007] = X_LDA;
	code[0x0008] = X_LDV;
	code[0x0009] = X_LD; // load constant 1
	code[0x000A] = 1u;
	code[0x000B] = X_LD; // load local variable
	code[0x000C] = 0u;
	code[0x000D] = X_LD;
	code[0x000E] = 3u;
	code[0x000F] = X_LDA;
	code[0x0010] = X_LDV;
	code[0x0011] = X_ADD; // add local variable value to 1
	code[0x0012] = X_ADD; // add the result to global variable value
	code[0x0013] = X_RET;
	code[0x0014] = 1u;	// number of arguments to take out of the stack
	code[0x0015] = X_LD; // global stackframe
	code[0x0016] = 0;
	code[0x0017] = X_LD;
	code[0x0018] = 0;
	code[0x0019] = X_LD;
	code[0x001A] = 0;
	code[0x001B] = X_LOC; // init 1 local variable
	code[0x001C] = 1u;
	code[0x001D] = X_LD; // load 5
	code[0x001E] = 5u;
	code[0x001F] = X_LD; // load global variable address
	code[0x0020] = 0u;
	code[0x0021] = X_LD;
	code[0x0022] = 3u;
	code[0x0023] = X_LDA; // load address of global variable
	code[0x0024] = X_STV; // store 5 in global variable
	code[0x0025] = X_LD; // load global variable
	code[0x0026] = 0u;
	code[0x0027] = X_LD;
	code[0x0028] = 3u;
	code[0x0029] = X_LDA; // load address of global variable
	code[0x002A] = X_LDV; // load the value
	code[0x002B] = X_LD;  // load 1
	code[0x002C] = 1u;
	code[0x002D] = X_ADD;
	code[0x002E] = X_LD; // call function > nesting
	code[0x002F] = -1;
	code[0x0030] = X_CAL;
	code[0x0031] = 0x00; // function address
	code[0x0032] = X_HALT;
	PC = 0x0015;
	SP = STACK_SIZE+HEAP_SIZE;
	FP = SP-1;
	Run();
	printf("%u\n",data[SP]);
}

int main() {
	test2();
	return 0;
}

// Curiosidade a partir daqui:

#define LABEL(l) labels[l] = PC
#define ENTRYPOINT LABEL(0)
#define REF(l) (refs[r++] = PC, l)

#define START_CODE(l) void load_##l() { \
	int labels[100], refs[100], r = 0; PC = 0; BR REF(0)

#define END_CODE \
	PC = 0; \
	SP = STACK_SIZE+HEAP_SIZE;\
	FP = SP-1;\
	int i; \
	for( i = 0; i < r; i++) \
		code[refs[i]] = labels[code[refs[i]]]; \
}

#define LD code[PC++] = X_LD; code[PC++] =
#define ADD code[PC++] = X_ADD
#define HALT code[PC++] = X_HALT
#define BR code[PC++] = X_BR; code[PC++] =
#define RUN(l) load_##l(); Run(); printf("%u\n",data[SP])

#define START_FUNCTION(l)
#define END_FUNCTION


START_CODE(test3);
LABEL(1);
	LD 2u;
	ADD;
	BR REF(2);
ENTRYPOINT;
	LD 2u;
	LD 2u;
	LD 2u;
	ADD;
	ADD;
	BR REF(1);
LABEL(2);
	HALT;
END_CODE;

START_CODE(test4);
ENTRYPOINT;
	LD 2u;
	LD 2u;
	LD 2u;
	ADD;
	ADD;
	HALT;
END_CODE;

/*
int main() {
	test2();
	//RUN(test1);
	return 0;
}
*/
