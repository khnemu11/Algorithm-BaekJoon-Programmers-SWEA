class Solution {
    public int solution(int n) {
        int answer = 0;
        long[] fibonacci = new long[n+1];
        
        fibonacci[1]=1L;
        fibonacci[2]=1L;
        
        for(int i=2;i<=n;i++){
            fibonacci[i] = (fibonacci[i-1]+fibonacci[i-2])%1234567;
        }
        
        
        
        return (int)fibonacci[n];
    }
}