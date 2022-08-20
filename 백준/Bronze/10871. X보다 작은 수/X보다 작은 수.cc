#include <stdio.h>
#include <stdlib.h>

int main(void) {
  int input[2];
  int*list;

  scanf("%d %d",&input[0],&input[1]);

  list = (int*)malloc(sizeof(int)*input[0]);

  for(int i=0;i<input[0];i++){
    scanf("%d",&list[i]);
  }
  for(int i=0;i<input[0];i++){
    if(list[i]<input[1])  printf("%d ",list[i]);
  }
  printf("\n");
}