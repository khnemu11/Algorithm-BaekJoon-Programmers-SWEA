#include <stdio.h>

int main(void) {
  double a,b;
  double result;

  scanf("%lf %lf",&a,&b);

  result = a/b;

  printf("%.9lf\n",result);

  return 0;
}