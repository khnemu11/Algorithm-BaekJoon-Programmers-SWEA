import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long l = 0;
        long r = Long.MAX_VALUE;
        while(l<r){
            long mid = (l+r)/2;
            long count = 0;
            
            for(int time : times){
                count += mid / time;
                if(count >=n){
                    break;
                }
                
            }            
            
            if(count >= n){
                answer = mid;
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        
        return answer;
    }
}