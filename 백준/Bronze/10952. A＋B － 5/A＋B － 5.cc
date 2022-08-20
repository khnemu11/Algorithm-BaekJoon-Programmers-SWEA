#include <stdio.h>
#include <stdlib.h>

int main(void) {
  int input[2];

  while(1){
    scanf("%d %d",&input[0],&input[1]);
    if(input[0]==0 && input[1]==0)  break;

    printf("%d\n",input[0]+input[1]);
  }
}