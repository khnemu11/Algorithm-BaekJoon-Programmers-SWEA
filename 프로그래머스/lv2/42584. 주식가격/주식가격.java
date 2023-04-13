/*
    증가 부분의 시작 값을 저장

*/

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Node> stack = new Stack<>();
        int [] answer = new int[prices.length];
        stack.add(new Node(answer[answer.length-1],0));
        
        for(int i=answer.length-2;i>=0;i--){
            if(stack.peek().val < prices[i]){
                answer[i]=1;
                stack.add(new Node(prices[i],1));
            }else{
                Node curr = stack.pop();
                curr.length++;
                curr.val = prices[i];
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