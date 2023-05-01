class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int COUNT=0;
        int ZERO=1;
        
        while(!s.equals("1")){
            StringBuilder sb= new StringBuilder();
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='0'){
                    answer[ZERO]++;
                }
                else{
                    sb.append(s.charAt(i));
                }
            }
            s =Integer.toBinaryString(sb.toString().length());
            answer[COUNT]++;
        } 
        return answer;
    }
}