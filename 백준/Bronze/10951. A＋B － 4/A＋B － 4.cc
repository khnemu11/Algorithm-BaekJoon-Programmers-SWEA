#include <stdio.h>
#include <stdlib.h>

int main(void) {
  int input[2],z;

  while(z!=EOF){
    z=scanf("%d %d",&input[0],&input[1]);
    if(z!=EOF) printf("%d\n",input[0]+input[1]);
  }
}