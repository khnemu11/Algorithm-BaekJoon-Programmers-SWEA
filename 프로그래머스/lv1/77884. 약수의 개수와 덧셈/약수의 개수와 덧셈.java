class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        for(int i = left; i<=right;i++){
            int divisor = 1;
            for(int j=2;j<=i;j++){
                if(i%j==0) divisor++;
            }
            if(divisor%2 ==1) answer+=i*(-1);
            else    answer+=i;
        }
        
        System.out.println(answer);
        
        return answer;
    }
}