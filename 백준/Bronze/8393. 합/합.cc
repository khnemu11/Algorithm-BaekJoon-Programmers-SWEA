#include <stdio.h>

int main(void) {
  int input[1];
  int result=0;
  scanf("%d",&input[0]);

  for(int i=1;i<=input[0];i++){
    result=result+i;
  }

  printf("%d\n",result);
}