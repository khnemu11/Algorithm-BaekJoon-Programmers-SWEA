#include <stdio.h>

int main(void) {
  int input[3];
  
  scanf("%d",&input[0]);

  for(int i=0;i<input[0];i++){
    scanf("%d %d",&input[1],&input[2]);
    printf("Case #%d: %d\n",i+1,input[1]+input[2]);
  }
}