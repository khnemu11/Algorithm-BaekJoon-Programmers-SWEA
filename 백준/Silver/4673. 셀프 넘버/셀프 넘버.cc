#include <stdio.h>
#include <string.h>

int selfnum(int num){
  int result,temp;
  
  result=num;

  while(1){
    if(num<1) break;

    temp=num;
    temp=temp%10;

    result=result+temp;

    num=num/10;
  }

  return result;
}


int main(void) {
  int num[10001];
  int cycle=0;
  
  memset(num,0,sizeof(num));

  for(int i=1;i<10001;i++){
    cycle=i;
    while(1){
      cycle=selfnum(cycle);
      if(cycle>10000) break;

      num[cycle]=1;
    }
  }

  for(int i=1;i<10001;i++){
    if(num[i]==1) continue;
    
    printf("%d\n",i);
  }

  return 0;
}