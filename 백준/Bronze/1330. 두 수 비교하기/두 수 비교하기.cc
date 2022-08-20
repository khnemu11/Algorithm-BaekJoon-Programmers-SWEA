#include <stdio.h>

int main(void) {
  int input[2];
  
  scanf("%d %d",&input[0],&input[1]);

  if(input[0]>input[1]) printf(">\n");
  
  else if(input[0]<input[1]) printf("<\n");

  else printf("==\n");

  return 0;
}