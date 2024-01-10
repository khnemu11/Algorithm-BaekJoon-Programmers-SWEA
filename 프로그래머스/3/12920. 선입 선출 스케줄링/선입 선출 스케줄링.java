import java.util.*;

/*
    효율성이 문제가 발생하면 nlogn -> n -> logn으로 생각
    
    
*/

class Solution {
    public int solution(int n, int[] cores) {
        int left = 0;   //최소 시간
        int right = Arrays.stream(cores).max().getAsInt()*n;    //최대 시간
        int time = 0;
        int workCount = 0;
        
        while(left <= right){
            int mid = (left + right)/2;
            System.out.println(mid);
            
            int count = cores.length;
            
            for(int core : cores){
                count += (mid/core);
            }
           
            if(count >= n){
                time = mid;
                right = mid - 1;
                workCount = count;
            }else{
                left = mid + 1;
            }
        }
        
        workCount -= n;
        
        for(int i=cores.length-1;i>=0;i--){
            if(time % cores[i] == 0){
                if(workCount == 0){
                    return i+1;
                }
                workCount--;  
            }
        }
        
        return right;
    }
}