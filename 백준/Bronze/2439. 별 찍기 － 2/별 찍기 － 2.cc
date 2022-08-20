#include <stdio.h>

int main(void) {
  int input[3];
  
  scanf("%d",&input[0]);

  for(int i=1;i<=input[0];i++){
    for(int k=0;k<input[0]-i;k++)
    printf(" ");
    for(int j=0;j<i;j++)
    printf("*");
    printf("\n");
  }
  
}