#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
  char input[21];
  char already;
  int vowchk=0,conchk=0;
  int accept=1,q=0;
  char result[100][21];
  int resultaccept[100];

  while(1){
    scanf("%s",input);
    strcpy(result[q],input);
    if(strcmp(input,"end")==0)  break;

    if(strchr(input,'a')==NULL && strchr(input,'e')==NULL && strchr(input,'i')==NULL&&strchr(input,'o')==NULL && strchr(input,'u')==NULL){
      //printf("<%s> is not acceptable.\n",input);
      accept=1;
      resultaccept[q]=0;
      q++;
      continue;
    }


    if(strlen(input)>=3){
      for(int i=0;i<strlen(input)-2;i++){
        if(input[i] == 'a' || input[i] == 'e' ||input[i] == 'i' ||input[i] == 'o' ||input[i] == 'u')  vowchk++;
        else conchk++;
        if(input[i+1] == 'a' || input[i+1] == 'e' ||input[i+1] == 'i' ||input[i+1] == 'o' ||input[i+1] == 'u')  vowchk++;
        else conchk++;
        if(input[i+2] == 'a' || input[i+2] == 'e' ||input[i+2] == 'i' ||input[i+2] == 'o' ||input[i+2] == 'u')  vowchk++;
        else conchk++;  

        if(vowchk==3 || conchk==3){
          ///printf("<%s> is not acceptable.\n",input);
          accept=0;
          resultaccept[q]=0;
          break;
        }

        conchk=0;
        vowchk=0;
      }
    }

    already = input[0];
    for(int i=1;i<strlen(input);i++){
      if(already==input[i] && already!='e' && already!='o'){
       // printf("<%s> is not acceptable.\n",input);
        accept=0;
        resultaccept[q]=0;
        break;
      }
      already=input[i];
    }

    if(accept==1){
     // printf("<%s> is acceptable.\n",input);
      resultaccept[q]=1;
    } 
    q++;
    accept=1;
  }
  q=0;
  while(1){
    if(strcmp(result[q],"end")==0)  break;
    if(resultaccept[q]==0)  printf("<%s> is not acceptable.\n",result[q]);
    else if(resultaccept[q]==1)  printf("<%s> is acceptable.\n",result[q]);
    q++;
  }
}