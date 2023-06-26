import java.util.*;

class Solution {
    public int solution(String s) {
        int cnt= 0;
        char x = ' ';
        
        int xCnt = 0;   //x문자 개수
        int notXCnt=0;  //x문자가 아닌 개수
        
        //x의 개수와 아닌 개수가 동일할 때 까지 개수를 늘리는 반복문
        for(int i=0;i<s.length();i++){
            if(x == ' '){
                x = s.charAt(i);
                xCnt++;
            }else if(x !=s.charAt(i)){
                notXCnt++;   
            }else{
                xCnt++;
            }
            
            if(xCnt == notXCnt){
                cnt++;
                x = ' ';
                xCnt = 0;
                notXCnt = 0;
            }
        }
        //나머지 x나 x가 아닌 경우를 더하기
        if(xCnt >0 || notXCnt>0){
             cnt++;
        }
        
        
        return cnt;
    }
}