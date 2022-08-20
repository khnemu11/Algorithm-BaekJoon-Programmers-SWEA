#include <stdio.h>
#include <string.h>

int main(){
  int row,col,k=0;
  char input[20];

  scanf("%d",&row);
  
  memset(input,0,sizeof(input));

  for(int i=0;i<row;i++){
    scanf("%d %s",&col,input); 
    while(1){
      if(input[k] == '\0' ) break;
      for(int j=0;j<col;j++)  printf("%c",input[k]);
      k++;
    }
    printf("\n");
    k=0;
    memset(input,0,sizeof(input));
  }
}