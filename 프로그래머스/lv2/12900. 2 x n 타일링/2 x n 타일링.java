class Solution {
    public int solution(int n) {
        int dp[] = new int[n+1];
        for(int i=1;i<=n;i++){
            if(i==1){
                dp[i]=1;
            }else if(i==2){
                dp[i]=2;
            }else{
                dp[i] = (dp[i-1]+dp[i-2])%1_000_000_007;
            }
        }
        return dp[n];
    }
}