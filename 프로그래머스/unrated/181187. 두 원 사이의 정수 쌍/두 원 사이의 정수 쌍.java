/*
    4-2+1, 8-4+1 12 -6 + 1
    좌표의 개수 = 변의 길이 * 2 -1 
*/

import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        double powR2 = Math.pow(r2,2);
        double powR1 = Math.pow(r1,2);
        
        long cnt = 0;
        
        for(int x=1;x<r2;x++){
            long r2Cnt = (long)Math.floor(Math.sqrt(powR2 - Math.pow(x,2)));
            if(x <=r1){
                long r1Cnt = (long)Math.floor(Math.sqrt(powR1 - Math.pow(x,2)));
                cnt += r2Cnt - r1Cnt;
                
                if(x!=r1 && Math.sqrt(powR1 - Math.pow(x,2)) == Math.floor(Math.sqrt(powR1 - Math.pow(x,2)))){
                    cnt++;
                }
            }else{
                cnt+=r2Cnt;
            }
        }
        cnt = cnt*4 + (r2-r1+1)*4;
        return cnt;
    }
}