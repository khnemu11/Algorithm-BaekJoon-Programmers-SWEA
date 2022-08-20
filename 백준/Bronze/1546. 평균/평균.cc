#include <stdio.h>

int main(void) {
  int size;
  int input;
  int max=0;
  float average=0;

  scanf("%d",&size);

  for(int i=0;i<size;i++){
    scanf("%d",&input);

    average = average + input;
    if(max<input) max=input;
  }

  average=(average/size)/max*100;

  printf("%.2lf\n",average);
}