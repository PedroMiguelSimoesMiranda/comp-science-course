#include <stdio.h>

char mystrdup(char *s)
{
int n=0;
char *p=*s;
while((*p)!='\0')
{
p++;
n++;
}
char *new_s = malloc(n+1);
while((*s)!='\0')
{
*new_s=*s;
new_s++;
s++;
}
new_s=(new_s)-n;
return new_s;
}


int main()
{
int temp;
char string[100];
printf("Introduza a string: \n");
scanf("%s",string);
mystrdup(string);
printf("%p",mystrdup(string));
scanf("%d",&temp);
return 0;
}



