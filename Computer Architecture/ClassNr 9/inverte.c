/* (c) AC - 2008-2010 - LEI - DI-FCT/UNL */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "stack.h"  // usa o stack

#define S_SZ    100    // tamanho max. a usar na string


void inverte( char *s )
{
    stack_init();
    int i=0;
    while(s[i]!='\0'){
        stack_push(s[i]);
        i++;
    }
    int j=0;
    while(!stack_empty()){
        s[j]=stack_pop();
        j++;
    }
}

int main(int argc, char *argv[])
{
    char str[S_SZ];
    FILE *fin, *fout;
    fin=fopen(argv[1],"r");
    fout=fopen(argv[2],"w");
    if(fin==NULL){
	printf("Ficheiro inexistente!");
        exit(1);
	}
    while(fgets(str,S_SZ,fin)!=NULL){
	inverte(str);
	fputs(str,fout);
	}
    fclose(fin);
    fclose(fout);

    return 0;
    
}

