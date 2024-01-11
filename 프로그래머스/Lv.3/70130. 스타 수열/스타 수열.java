import java.util.*;

class Solution {
    public int solution(int[] a) {
        if(a.length<2){
            return 0;
        }
        
        int max = 0;
        int dp[] = new int[a.length];
        
        int length = 0;
        Set<Integer> conmbined = new HashSet<>();
        
        //0번부터 시작
        for(int i=0;i<a.length;i+=2){
            //x[0] != x[1] 규칙이 안되는 경우
            if(a[i] == a[i+1]){
                length = 0;
                conmbined = new HashSet<>();
            }else if(length == 0){
                
            }
            
        }
        
        if(a.length<3){
            return max;
        }
        
        if(a[1] != a[2]){
            dp[0] = 2;
        }
        
        
        
        return max;
    }
}