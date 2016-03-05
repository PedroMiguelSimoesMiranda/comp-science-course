#pragma once

#define X_NOP  0x0000
#define X_HALT 0x00FF
#define X_ADD  0x0001
#define X_LD   0x0002  
#define X_BR   0x0003
#define X_SUB  0x0004
#define X_MUL  0x0005
#define X_DIV  0x0006
#define X_AND  0x0007
#define X_OR   0x0008
#define X_EQ   0x0009
#define X_GT   0x000A
#define X_NOT  0x000B
#define X_BRF  0x000C  
#define X_CAL  0x000D
#define X_RET  0x000E
#define X_LOC  0x000F
#define X_STV  0x0011
#define X_LDV  0x0010
#define X_LDA  0x0012
#define X_WR   0x0020
#define X_RD   0x0021

#define CODE_SIZE    20000

typedef unsigned Word;
typedef unsigned Addr;

extern Word code[];

void Run(void);
