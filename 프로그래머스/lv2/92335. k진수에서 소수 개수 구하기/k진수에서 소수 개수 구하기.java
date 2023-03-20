import java.util.*;
class Solution {
    public int solution(int n, int k) {
		String str = Integer.toString(n, k);
        String candidates[] = str.split("0");
        int cnt = 0;
        
        for(String candidate : candidates){
            if(candidate.length()==0 || candidate.equals("1")){
                continue;
            }
            long num = Long.parseLong(candidate);
            
            boolean isPrime=true;
            
            for(int i=2;i<=Math.sqrt(num);i++){
                if(num%i==0){
                    isPrime=false;
                    break;
                }
            }
            
            if(isPrime){
                cnt++;
            }

        }
        
        return cnt;
    }
}