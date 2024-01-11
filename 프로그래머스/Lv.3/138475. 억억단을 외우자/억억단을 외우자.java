/*
1   2   3   4   5   6   7   8
2   4   6   8   10  12  14  16
3   6   9   12  15  18  21  24
4   8   12  16  20  24  28  32
5   10  15  20  25  30  35  40
6   12  18  24  30  36  42  48
7   14  21  28  35  42  49  56
8   16  24  32  40  48  56  64
    
1 2 3 4 5 6 7 8
1
1 2
1 3
1 2 4
1 5
1 2 3 6
1 7
1 2 4 8
*/
import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] count = new int[e+1];
        int[] maxCountNum = new int[e+1];
        
        for(int i=1;i<=e;i++){
            // count[i] = getCount(i);
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