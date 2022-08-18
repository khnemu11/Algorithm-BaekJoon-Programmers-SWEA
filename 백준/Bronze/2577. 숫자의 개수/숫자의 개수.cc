#include <stdio.h>
#include <string.h>

int main(void) {
  int input[3];
  int result=0;
  int countlist[10];
  int index=10;
  int parse;

  memset(countlist,0,sizeof(countlist));

  for(int i=0;i<3;i++){
    scanf("%d",&input[i]);
  }

  result=input[0]*input[1]*input[2];

  while(1){
    parse = result % index;
    result = result/10;

    countlist[parse]=countlist[parse]+1;  
    
    if(result==0)  break;
  }
  for(int i=0;i<10;i++){
    printf("%d\n",countlist[i]);
  }
}