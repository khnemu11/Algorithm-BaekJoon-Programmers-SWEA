#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
  char input[16];
  int result=0;

  scanf("%s",input);

  for(int i=0;i<strlen(input);i++){
    if(input[i]>=65 && input[i]<=67)  result=result+3;
    else if(input[i]>=68 && input[i]<=70)  result=result+4;
    else if(input[i]>=71 && input[i]<=73)  result=result+5;
    else if(input[i]>=74 && input[i]<=76)  result=result+6;
    else if(input[i]>=77 && input[i]<=79)  result=result+7;
    else if(input[i]>=80 && input[i]<=83)  result=result+8;
    else if(input[i]>=84 && input[i]<=86)  result=result+9;
    else if(input[i]>=87 && input[i]<=90)  result=result+10;
  }

  printf("%d\n",result);
}