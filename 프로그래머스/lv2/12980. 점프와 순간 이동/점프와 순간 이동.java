import java.util.*;
/*
    무조건 *2를 하는것이 이득
    하지 못하는 경우에 1칸만 움직이면 짝수가 되서 /2가 가능하므로 /2할 때 홀수인 경우만 센다
    
    걸린시간 : 5분 32초
*/
public class Solution {
    public int solution(int n) {
        int ans = 0;
        
        while(n>0){
            if(n %2!=0){
                n = n-1;
                ans++;                
            }
            n = n/2;
        }    
    
        return ans;
    }
}