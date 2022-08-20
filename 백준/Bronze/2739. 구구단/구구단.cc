#include <stdio.h>

int main(void) {
  int input[1];
  
  scanf("%d",&input[0]);
  

  for(int i=1;i<10;i++){
     printf("%d * %d = %d\n",input[0],i,input[0]*i);
  }
 

}