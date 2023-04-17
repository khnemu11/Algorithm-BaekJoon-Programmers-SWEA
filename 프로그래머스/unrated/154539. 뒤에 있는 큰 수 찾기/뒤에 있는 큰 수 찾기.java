import java.util.*;

/*
    오른쪽 중 가장 가까이에 있는 수 중 자신보다 큰수
    가장 가까이 -> 스택 or 큐
    오른쪽(뒤)에서 찾으므로 스택사용
    
    자신보다 작은 값이 오른쪽에 있으면 이후에도 큰 수가 될 가능성이 없으므로 pop으로 제거
    자신보다 큰 값이 있으면 자신도 큰 수가될 가능성이 있으므로 스택에 넣기
    
    풀이 시간 : 3분
*/

class Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[numbers.length];    
        
        for(int i = numbers.length-1; i>=0;i--){
            //자신보다 큰 수 찾기
            while(!stack.isEmpty()){
                if(stack.peek() > numbers[i]){
                    break;
                }
                //자신보다 작은 수 제거
                stack.pop();
            }
            
            //자신보다 큰 수가 없는 경우
            if(stack.isEmpty()){
                answer[i] = -1;
            }
            //자신보다 큰 수가 있는 경우
            else{
                answer[i] = stack.peek();   
            }
            stack.add(numbers[i]);
        }

        return answer;
    }
}