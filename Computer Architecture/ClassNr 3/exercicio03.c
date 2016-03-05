// MY MEMORY COPY

// AULA 03 EXERCICIO 03

void memcpy(char *m1, char *m2, int n){
int i;
for(i=0;i<n;i++)
*m2=*m1;/*qd e igualar os apontadores dar lhes valores mete * */
m1++;m2++; /*incrementar apontador sem o * */
}
