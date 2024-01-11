import java.util.*;

/*
    
*/

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] count = new int[e+1];
        int[] maxCountNum = new int[e+1];
        
        for(int i=1;i<=e;i++){
            // for(int j=1;j<=Math.sqrt(i);j++){
            //     if(Math.pow(j,2) == i){
            //         count[i]++;
            //     }
            //     else if(i % j == 0){
            //         count[i]+=2;
            //     }
            // }
            
            for(int j=i;j<=e;j+=i){
                count[j]++;
            }
        }
        
        maxCountNum[e] = e; 
        
        for(int i=e-1;i>=1;i--){
            if(count[maxCountNum[i+1]] > count[i]){
                 maxCountNum[i] = maxCountNum[i+1];
            }else{
                 maxCountNum[i] = i;
            }
        }
   
        for(int i=0;i<starts.length;i++){
            answer[i]= maxCountNum[starts[i]];
        }
        
        return answer;
    }
    
    public int getCount(int num) {
        int count = 0;
        for(int i=1;i<=Math.sqrt(num);i++){
            if(Math.pow(i,2) == num){
                count++;
            }
            else if(num % i == 0){
                count+=2;
            }
        }
        
        return count;
    }
}