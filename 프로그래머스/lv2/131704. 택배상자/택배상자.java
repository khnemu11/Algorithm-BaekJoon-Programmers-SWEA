import java.util.*;

class Solution {
    public int solution(int[] order) {
        int seq=1;
        int answer=0;
        Stack<Integer>stack = new Stack<>();
        
        for(int i=0;i<order.length;i++){
            while(stack.isEmpty() || stack.peek() < order[i]){
                stack.add(seq++);
            }
            
            if(!stack.isEmpty() && stack.peek() == order[i]){
                stack.pop();
                answer++;
                continue;
            }
            break;
        }
        return answer;
    }
}