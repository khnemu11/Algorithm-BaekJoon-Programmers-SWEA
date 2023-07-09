/*
    숫자의 최대 크기 = 10,000
    => n^2가능
    
    결과 :시간초과
    =>연속된 수의 합이 목표보다 크면 더이상 탐색을 하지 않게 하여 가지치기
*/

class Solution {
    public int solution(int n) {
        int count = 0;
        
        for(int l=1;l<=n;l++){
            int sum =0;
            for(int r=l;r<=n;r++){
                sum +=r;
                if(sum==n){
                    count++;
                    break;
                }else if(sum > n){
                    break;
                }
            }   
        }
        
        return count;
    }
}