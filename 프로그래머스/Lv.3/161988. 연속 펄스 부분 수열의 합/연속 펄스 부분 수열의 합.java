import java.util.*;
    
/*
    dp[현재 값을 사용하는 연속된 수의 최대값] = Math.max(dp[이전 값을 사용하는 연속된 수의 최대값]+현재값 , 현재값)
*/
class Solution {
    public long solution(int[] sequence) {
        //1,-1,1.. 대입하여 가장 큰 부분 합 수열 구하기
        long[] tempPlus = new long[sequence.length];
        long[] tempMinus = new long[sequence.length];
        
        for(int i=0;i<tempMinus.length;i++){
            tempPlus[i] = sequence[i]*(long)Math.pow(-1,i+1);
            tempMinus[i] = sequence[i]*(long)Math.pow(-1,i+2);
        }
        
        long[] dpPlus = new long[tempPlus.length+1];
        long[] dpMinus = new long[tempMinus.length+1];
        long max = 0;
        
        for(int i=1;i<dpPlus.length;i++){
            dpPlus[i] = Math.max(dpPlus[i-1] + tempPlus[i-1] , tempPlus[i-1]);
            dpMinus[i] = Math.max(dpMinus[i-1] + tempMinus[i-1] , tempMinus[i-1]);
            
            max = Math.max(max,dpPlus[i]);
            max = Math.max(max,dpMinus[i]);
        }
        
        return max;
    }
}

