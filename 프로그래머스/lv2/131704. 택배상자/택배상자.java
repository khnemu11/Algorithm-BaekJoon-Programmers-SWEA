import java.util.*;
/*
    시작 화물 순서 : 1 2 3 4 5....
    보조 컨테이너는 가장 최근 화물만 꺼낼 수 있다 -> 스택
    보조 컨테이너는 오름차순으로 넣어진다.
    현재 넣어야 할 화물이 보조 컨테이너의 가장 최근 화물보다 작다면 보조 컨테이너에 있지만 아래에 있는 경우라 뺄 수 없다.
    -> 이 경우에 탈출
*/
class Solution {
    public int solution(int[] order) {
        int seq=1;
        int answer=0;
        //보조 컨테이너
        Stack<Integer>stack = new Stack<>();
        
        for(int i=0;i<order.length;i++){
            //보조 컨테이너 값이 넣어야할 화물보다 같아질 때 까지 저장
            while(stack.isEmpty() || stack.peek() < order[i]){
                stack.add(seq++);
            }
            
            //넣어야 할 화물이 보조 컨테이너의 맨 위 값과 같은경우 화물을 넣을 수 있다.
            if(!stack.isEmpty() && stack.peek() == order[i]){
                stack.pop();
                answer++;
                continue;
            }
            //넣어야 할 화물이 보조 컨테이너의 맨 위 값과 다를경우 더이상 화물을 넣을 수 없다.
            break;
        }
        return answer;
    }
}