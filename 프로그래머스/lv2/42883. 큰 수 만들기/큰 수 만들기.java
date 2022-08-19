class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuffer partAnswer = new StringBuffer(number);
        int startIndex = 0;
        for(int i=0;i<k;i++){
             for(int j=startIndex;j<partAnswer.length();j++){
                if(j == partAnswer.length()-1) {
                    partAnswer = partAnswer.deleteCharAt(j);
                    startIndex = j;
                    break;
                }
                if(partAnswer.charAt(j) < partAnswer.charAt(j+1)){
                    partAnswer = partAnswer.deleteCharAt(j);
                    if(j==0) startIndex = 0;
                    else  startIndex = j-1;
                    break;
                }
            }
        }
        answer = partAnswer.toString();
        return answer;
    }
}