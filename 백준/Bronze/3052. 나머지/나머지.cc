#include <stdio.h>

int main(void) {
  int input[10];
  int result[10];
  int count=0;

  for(int i=0;i<10;i++){
    scanf("%d",&input[i]);
    result[i]=input[i]%42;
    
    for(int j=0;j<i;j++){
      if(result[j]==result[i]){
        count++;
        break;
      }
    }
  }

  printf("%d\n",10-count);
}