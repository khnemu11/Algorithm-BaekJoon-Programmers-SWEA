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