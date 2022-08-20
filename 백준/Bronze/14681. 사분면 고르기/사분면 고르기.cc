#include <stdio.h>

int main(void) {
  int input[2];
  
  scanf("%d",&input[0]);
  scanf("%d",&input[1]);

  if(input[0]>0 && input[1]>0) printf("1\n");
  else if(input[0]<0 && input[1]>0) printf("2\n");
  else if(input[0]<0 && input[1]<0) printf("3\n");
  else printf("4\n");
}