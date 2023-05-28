class Solution {
    public int solution(int n) {
        int answer = 0;
    
        String oriStr = Integer.toBinaryString(n);
        int oriCnt = cntOne(n);
        
        int target = n+1;
        
        while(cntOne(target)!=oriCnt){
            target++;
        }
        
        return target;
    }
    
    public int cntOne(int n){
        int cnt = 0;
        String bitStr = Integer.toBinaryString(n);
        for(int i=0;i<bitStr.length();i++){
            if(bitStr.charAt(i)=='1'){
                cnt++;
            }   
        }
        
        return cnt;
    }
}