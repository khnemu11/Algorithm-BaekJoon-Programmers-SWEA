#include <stdio.h>
#include <stdlib.h>

int main(void) {
  int size,max,min;
  int *list;

  scanf("%d",&size);

  list=(int*)malloc(sizeof(int)*size);

  for(int i=0;i<size;i++){
    scanf("%d",&list[i]);
  }

  max=list[0];
  min=list[0];

  for(int i=1;i<size;i++){
    if(list[i]>max) max = list[i];
    if(list[i]<min) min = list[i];
  }

  printf("%d %d\n",min,max);
}