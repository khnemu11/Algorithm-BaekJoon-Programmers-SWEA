#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main(){
  char input[1000000];
  int alpha[26];
  int i=0,max=0;
  char max_alpha = '?';
  char upper;

  memset(alpha,0,sizeof(alpha));

  scanf("%s", input);

  while(1){
    if(input[i]=='\0')  break;

    upper = toupper(input[i]);

    alpha[(int)upper -65]++;

    if(max<alpha[(int)upper -65]){
      max = alpha[(int)upper -65];
      max_alpha = upper;
    }
    else if(max == alpha[(int)upper - 65]) {
      max_alpha='?';
    }
    i++;
  }

  printf("%c\n",max_alpha);
  
}