#include <stdio.h>
#include <stdlib.h>

int main(void) {
  int list[9],max,max_index;

  for(int i=0;i<9;i++){
    scanf("%d",&list[i]);
  }

  max=list[0];
  max_index=0;
  for(int i=1;i<9;i++){
    if(list[i]>max){
    max_index = i;
    max = list[i];
    }
  }

  printf("%d\n",max); 
  printf("%d\n",max_index+1);
}