import java.util.*;
/*
   
   
    걸린 시간 : 10분
*/
class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        Queue<Character> q = new LinkedList<>(); //현재 말해야 하는 숫자를 담는 큐
            
        int digitNum = 0;   //현재 10진수 숫자
        
        //t개만큼 반복
        while(answer.length() <t){  
            //전체 인원이 말할 수 있을 만큼 숫자를 큐에 담는다. 
            while(q.size()<m){
                String num = Integer.toString(digitNum++,n);    //현재 10진수를 n진수로 변환
                
                //1개씩 큐에 넣기
                for(int i=0;i<num.length();i++){
                    q.add(num.charAt(i));
                }
            }
            //한사람씩 숫자를 말하고 튜브의 순서일 때 해당 숫자를 저장
            for(int i=1;i<=m;i++){
                char currNum = q.poll();
                if(i == p){
                    //소문자 처리
                    if(currNum >='a' && currNum<='z'){
                        answer+=Character.toUpperCase(currNum);
                    }
                    //숫자 처리
                    else{
                        answer+=currNum;
                    }
                }
            }
        }

        return answer;
    }
}