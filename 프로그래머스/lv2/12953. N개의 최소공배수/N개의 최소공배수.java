import java.math.*;

class Solution {
    public int solution(int[] arr) {
        BigInteger curr = new BigInteger(String.valueOf(arr[0]));

        for(int i=1;i<arr.length;i++){
          BigInteger next = new BigInteger(String.valueOf(arr[i]));  
          curr = curr.multiply(next).divide(curr.gcd(next));
        }
        
        return curr.intValue();
    }
}