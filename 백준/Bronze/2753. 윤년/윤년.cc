#include <stdio.h>

int main(void) {
  int input[2];
  
  scanf("%d",&input[0]);

  if (((input[0]%4 ==0) && (input[0]%100 != 0)) || ((input[0]%4 ==0) && (input[0]%400 == 0))) printf("1\n");
  else printf("0\n");
}