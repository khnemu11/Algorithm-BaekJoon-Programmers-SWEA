import java.util.*;
/*
    순서대로 카드 사용
    -> 가장 먼저 들어온 것을 사용해야 한다.
    -> 큐
*/
class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> cards1Q = new LinkedList<>();
        Queue<String> cards2Q = new LinkedList<>();
        
        //카드1을 사용한 큐 생성
        for(String card : cards1){
            cards1Q.add(card);
        }
        
        //카드2을 사용한 큐 생성
        for(String card : cards2){
            cards2Q.add(card);
        }

        //goal이 가능한지 2개의 큐를 비교하기
        for(int i=0;i<goal.length;i++){
            //카드1를 사용 가능한 경우
            if(!cards1Q.isEmpty() && cards1Q.peek().equals(goal[i])){
                cards1Q.poll();
                continue;
            }
            //카드2를 사용 가능한 경우
            else if(!cards2Q.isEmpty() && cards2Q.peek().equals(goal[i])){
                cards2Q.poll();
                continue;
            }
            //아무것도 사용 못하는 경우
            return "No";
        }
        
        return "Yes";
    }
}