#include <stdio.h>
#include<string.h>

int hansu(int num){
  int result=0;
  int digit[4];
  int i=0;

  memset(digit,0,sizeof(digit));

  while(1){
    if(num==0)  break;
    digit[i]=num%10;
    num=num/10;
    i++;
  }
  

  if(digit[3]==1){
    if (((digit[3]-digit[2])==(digit[2]-digit[1])) && ((digit[2]-digit[1]) == (digit[1]-digit[0]))){
        result = 1;
    }
  }
  else{
    if((digit[2]-digit[1])==(digit[1]-digit[0]))  result=1;
  }
  return result;
}


int main(void) {
  int num,count=0;

  scanf("%d",&num);

  for(int i=1;i<=num;i++){
    if(i<100) count++;
    else if(hansu(i)==1) count++;
  }

  printf("%d\n",count);
}