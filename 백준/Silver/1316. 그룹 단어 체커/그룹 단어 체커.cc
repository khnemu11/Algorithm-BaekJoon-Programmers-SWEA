#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
  int input,result=0;
  char**word;
  char buff[101];
  char alpha[26];
  int alphacount=0;
  int groupOk=0;

  memset(alpha,0,sizeof(alpha));

  scanf("%d",&input);
  word=(char**)malloc(sizeof(char*)*input);

  for(int i=0;i<input;i++){
    scanf("%s",buff);
    word[i]=(char*)malloc(sizeof(char)*(strlen(buff)+1));
    strcpy(word[i],buff);
    for(int j=0;j<strlen(word[i]);j++){
      if(j>0 && strchr(alpha,word[i][j])!=NULL && word[i][j]!=word[i][j-1]){
        groupOk=1;
        break;
      }
      if(strchr(alpha,word[i][j])==NULL){
        alpha[alphacount]=word[i][j];
        alphacount++;
      }

    }
    if(groupOk==0)  result++;
    groupOk=0;
    memset(alpha,'\0',sizeof(alpha));
    alphacount=0;
  }
  printf("%d\n",result);
}