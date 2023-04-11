import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        
        Stack<Integer>stack = new Stack<>();
        
        for(int num : arr){
            if(stack.isEmpty()){
                stack.add(num);
            }else if(stack.peek() == num){
                continue;
            }else{
                stack.add(num);
            }
        }
        
        answer = new int[stack.size()];
        
        for(int i=answer.length-1;i>=0;i--){
            answer[i] = stack.pop();
        }
        
        return answer;
    }
}