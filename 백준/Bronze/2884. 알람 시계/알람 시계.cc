#include <stdio.h>

int main(void) {
  int input[2];
  int result;

  scanf("%d",&input[0]);
  scanf("%d",&input[1]);

  input[1] = input[1] -45;

  if(input[1] < 0) {
    input[0]--;
    input[1] = input[1] + 60;
  }
  if(input[0]<0)  input[0] = 23;

  printf("%d %d\n",input[0],input[1]);

}