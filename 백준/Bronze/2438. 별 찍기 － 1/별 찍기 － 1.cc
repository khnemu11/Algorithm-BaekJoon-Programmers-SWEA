#include <stdio.h>

int main(void) {
  int input[3];
  
  scanf("%d",&input[0]);

  for(int i=0;i<input[0];i++){
    for(int j=0;j<=i;j++)
    printf("*");
    printf("\n");
  }
  
}