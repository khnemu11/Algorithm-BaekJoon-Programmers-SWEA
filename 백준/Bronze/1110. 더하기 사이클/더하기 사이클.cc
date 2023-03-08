#include <stdio.h>
#include <stdlib.h>

int main(void) {
  int cycle,one,ten,sum,sum_one,num,original;

  cycle=0;

  scanf("%d",&original);
  num=original;

  while(1){
    if(cycle>0 && num==original)  break;
    one = num%10;
    ten = (num - one)/10;
    sum = one + ten;
    sum_one = sum % 10;
    num =one*10 + sum_one;
    cycle++;
  }
  printf("%d\n",cycle);
}