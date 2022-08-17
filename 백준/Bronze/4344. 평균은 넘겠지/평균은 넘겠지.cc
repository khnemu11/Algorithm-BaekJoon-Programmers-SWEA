#include <stdio.h>
#include <string.h>

int main(void) {
  int score[1000];
  int row,col,   over_average_num=0;
  double average=0.0;
  double ratio_over_average=0.0;

  memset(score,-1,sizeof(score));

  scanf("%d",&row);
  
  for(int j=0;j<row;j++){
    scanf("%d",&col);

    for(int i=0;i<col;i++){
      scanf("%d",&score[i]);

      average = score[i]+average;
    }
    average = average/(double)col;

    for(int i=0;i<col;i++){
      if(average<score[i]){
        over_average_num++;
      }
    }
    ratio_over_average=(double)over_average_num/(double)col*100;

    printf("%.3lf%%\n",ratio_over_average);

    ratio_over_average=0.0;
    over_average_num=0.0;
    average=0.0;

    memset(score,-1,sizeof(score));
  }

}