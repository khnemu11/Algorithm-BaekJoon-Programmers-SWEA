/*
    증가하는 주식의 시작 부분이 현재 판단하는 부분보다 작으면 뒤는 볼 필요도 없다.
    ->스택을 이용해서 뒤에서부터 계산
    증가하는 주식의 시작 부분이 현재 판단하는 부분보다 같거나 크면 해당 주식의 길이만큼 더한다.
    증가 부분의 시작 값과 길이를 스택에 저장
    
*/

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Node> stack = new Stack<>();
        int [] answer = new int[prices.length];
        stack.add(new Node(answer[answer.length-1],0)); //맨 뒤의 값은 길이가 0이므로 맨 뒷값으로 초기화
        
        for(int i=answer.length-2;i>=0;i--){
            if(stack.peek().val < prices[i]){ //만약 현재 주식 값보다 다음 주식 값이 크면 감소하는 주식
                answer[i]=1;
                stack.add(new Node(prices[i],1));
            }else{//만약 현재 주식 값보다 다음 주식 값이 같거나 크면 감소하지 않는 주식
                Node curr = stack.pop();
                curr.length++;
                curr.val = prices[i];
                //현재 주식 값보다 다음 주식 시작 값이 크면 감소하는 주식
                while(stack.peek().val >= prices[i] && !stack.isEmpty()){
                    Node next = stack.pop();
                    curr.length+=next.length;
                }
                answer[i] = curr.length;
                stack.add(curr);
            }
        }
        
        return answer;        

    }
}
class Node{
    int val;
    int length;
    
    public Node(int val,int length){
        this.val = val;
        this.length = length;
    }
}
