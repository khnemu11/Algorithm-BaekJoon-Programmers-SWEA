class Solution{
    public int solution(String s){
        int answer = 1;

        //홀수 길이 중 최대값 탐색
        
        for(int mid =0;mid < s.length();mid++){
           int i=0;
            while(mid - i >=0 && mid + i < s.length()){
                if(s.charAt(mid-i) != s.charAt(mid+i)){
                    break;
                }
                answer = Math.max(answer, 2*i+1);
                i++;
            }
            
        }
        
        //짝수 길이 중 최대값 탐색
        for(int mid =1;mid < s.length();mid++){
            int i=0;
            
            while(mid + i < s.length() && mid -1 - i  >=0){
                if(s.charAt(mid+i) != s.charAt(mid -1 - i)){
                    break;
                }
                answer = Math.max(answer, 2*i+2);
                i++;
            }
        }

        return answer;
    }
}