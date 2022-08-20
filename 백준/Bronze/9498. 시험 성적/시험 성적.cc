#include <stdio.h>

int main(void) {
  int input[2];
  
  scanf("%d",&input[0]);

  if(input[0]>89) printf("A\n");
  else if(input[0]>79) printf("B\n");
  else if(input[0]>69) printf("C\n");
  else if(input[0]>59) printf("D\n");
  else printf("F\n");
  return 0;
}