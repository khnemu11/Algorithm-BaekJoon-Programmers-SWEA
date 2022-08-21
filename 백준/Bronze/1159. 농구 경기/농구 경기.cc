#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
  int num;
  char** name;
  char buff[30];
  int alpha[26];
  int exist=0;
  memset(alpha,0,sizeof(alpha));

  scanf("%d",&num);

  name=(char**)malloc(sizeof(char*)*num);

  for(int i=0;i<num;i++){
    scanf("%s",buff);
    name[i]=(char*)malloc(sizeof(char)*strlen(buff));
    strcpy(name[i],buff);
    alpha[name[i][0]-97]=alpha[name[i][0]-97]+1;
  }

  for(int i=0;i<26;i++){
    if(alpha[i]>4){
      printf("%c",i+97);
      exist=1;
    }
  }

  if(exist==0)  printf("PREDAJA\n");
}
