class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        int xCnt[] = new int[10];
        int yCnt[] = new int[10];
        
        //X의 숫자 개수
        for(int i=0;i<X.length();i++){
            xCnt[X.charAt(i)-'0']++;
        }
        //Y의 숫자 개수
        for(int i=0;i<Y.length();i++){
            yCnt[Y.charAt(i)-'0']++;
        }
        //결과 스트링 빌더
        StringBuilder sb = new StringBuilder();
        
        //공통 숫자를 큰숫자부터 횟수만큼 넣는 부분
        for(int i=xCnt.length-1;i>=0;i--){
            int commonCnt = Math.min(xCnt[i],yCnt[i]);           
            
            for(int j=0;j<commonCnt;j++){
                sb.append(i);
            }
        }
        
        //짝꿍이 존재하지 않는 경우
        if(sb.length()==0){
            answer="-1";
        }
        //짝꿍이 0만인 경우
        else if(sb.toString().replace("0","").length()==0){
            answer = "0";
        }
        //기본 경우
        else{
            answer = sb.toString();
        }
        
        return answer;
    }
}