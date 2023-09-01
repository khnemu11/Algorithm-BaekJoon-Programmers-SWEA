import java.math.*;

class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        long sum = 0;
        
        BigInteger total = new BigInteger(String.valueOf(w));
        total = total.multiply(new BigInteger(String.valueOf(h)));
        
        for(int i=1;i<=w;i++){
            sum = sum + (long)(Math.ceil((double)h*i/w) - Math.floor((double)h*(i-1)/w));
        }
        
        return total.subtract(new BigInteger(String.valueOf(sum))).longValue();
    }
}