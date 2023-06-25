/*
    2명의 선수가 똑같은 양의 음식을 먹어야 하므로 홀수개면 짝수개로 변경(-1)
*/

class Solution {
    public String solution(int[] food) {
        String leftStr = "";
        
        //왼쪽 문자열 생성
        for(int i=1;i<food.length;i++){
            int foodLength = food[i]%2==0 ? food[i] : food[i]-1;
            //음식의 절반만큼 제공
            for(int j=0;j<foodLength/2;j++){
                leftStr += i;   
            }
        }
        
        //오른쪽 문자열 생성
        String rightStr = (new StringBuilder(leftStr)).reverse().toString();
        
        return leftStr + "0"+rightStr;
    }
}

