#include <stdio.h>

int main(void) {
  int input[2];
  int hundred, ten, one;

  scanf("%d %d",&input[0],&input[1]);

  one = (input[1]%10);
  ten = ((input[1]%100)-one)/10;
  hundred = (input[1] - one - ten)/100;

  printf("%d\n",one*input[0]);
  printf("%d\n",ten*input[0]);
  printf("%d\n",hundred*input[0]);  
  printf("%d\n",input[1]*input[0]);
  
  return 0;
}