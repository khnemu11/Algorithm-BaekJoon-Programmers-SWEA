import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<s.length();i++){
            if(stack.isEmpty()){
                if(s.charAt(i)==')'){
                    return false;
                }
                stack.add(s.charAt(i));
            }else if(s.charAt(i) == ')' && stack.peek() =='('){
                stack.pop();
            }else{
                stack.add(s.charAt(i));
            }
        }
        
        if(stack.size()>0){
            return false;
        }else{
            return true;
        }
    }
}