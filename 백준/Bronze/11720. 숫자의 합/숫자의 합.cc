#include <stdio.h>
#include <stdlib.h>

int main(){
  int size,result=0;
  char* number;


  scanf("%d",&size);

  number = (char*)malloc(sizeof(int)*size);

  scanf("%s",number);
  
  for(int i=0;i<size;i++){
    result=result+
    number[i]-48;
  }
  printf("%d\n",result);
}