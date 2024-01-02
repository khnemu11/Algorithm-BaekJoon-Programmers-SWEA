/*
    걸린 시간 : 28분 32초
    
    1원을 가지고 금액을 만들 수 있는 경우의수
    1원을 이용한 경우의 수를 이용해 2원을 가지고 금액을 만들 수 있는 경우의 수
    1원,2원을 이용한 경우의 수를 이용해 5원을 가지고 금액을 만들수 있는 경우의 수
    ...
    
    n=5, money= [1,2,5]
    
        1   2   3   4   5   목표 금액
    1   1   1   1   1   1
    2   1   2   2   3   3
    5   1   2   2   3   4
    잔돈
    
    dp[목표 화폐] = dp[목표 화폐] + dp[목표 화폐-현재 화폐]
    
    시간 복잡도)
    (목표금액)*(화폐 종류의 수) = 100,000 * 100 = 100,000,000
*/

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        int dp[] = new int [n+1];
        int M = 1_000_000_007;
        
        for(int m : money){
            dp[m] = dp[m] + 1;
            for(int curr=m+1; curr<dp.length;curr++){
                dp[curr] = dp[curr] + dp[curr - m];
            }   
        }
        
        return dp[n];
    }
}