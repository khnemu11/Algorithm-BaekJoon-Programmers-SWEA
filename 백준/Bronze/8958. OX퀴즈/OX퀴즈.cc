#include <stdio.h>
#include <string.h>

int main(void) {
  char OX[80];
  int size,score=0,index=0,temp;

  memset(OX,0,sizeof(OX));

  scanf("%d",&size);

  for(int i=0;i<size;i++){
    scanf("%s",OX);

  while(1){
    if(OX[index]==0)  break;

    if(OX[index]=='O'){
      score++;

      temp=index-1;
      
      while(1){
        if(temp<0 || OX[temp]=='X')  break;
        
        temp--;
        score++;
      }
    }
    index++;
  }
   
    printf("%d\n",score);
    score=0;
    index=0;
    memset(OX,0,sizeof(OX));
  }
}