#include <stdio.h>
#include <string.h>

// CIFRA CESAR

// AULA 02 EXERCICIO 06


void cifra_cesar( char s[], int n ) {
	int i;
	for ( i = 0; i < strlen(s); i++ ){
		if ( s[i] >= 'a' && s[i] <= 'z' ) {
			s[i] += n;
			if ( s[i] > 'z' ) {
				s[i] -= 26; // 26 = 'z'–'a'
			}
		}
	}
}
int main() {
	char s[100];
	int flag = 1;
	while(flag) {
		printf("Introduza a string para cifrar ou fim\n");
		scanf("%s",s); // reparar que não se faz &s
		if(strcmp(s, "fim") == 0)
			flag = 0;
		else {
			cifra_cesar(s, 3);
			printf("%s\n", s);
		}
	}
return 0;
}