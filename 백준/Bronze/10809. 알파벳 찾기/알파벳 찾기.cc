#include <stdio.h>
#include <string.h>

int main(){
  char input[100];
  int result[26];
  int index;

  memset(input,0,sizeof(input));
  memset(result,-1,sizeof(result));

  scanf("%s",input);
  
  for(int i=0;i<100;i++){
    if(input[i] =='0') continue;
    index = (int)input[i] - 97;
    if(result[index]!=-1) continue;
    result[index] = i;
  }
  for(int i=0;i<26;i++) printf("%d ",result[i]);
}