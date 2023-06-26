import java.util.*;

class Solution {
    Stack<Integer> stack = new Stack<>();
    int sequence[] = {1,3,2,1};
    public int solution(int[] ingredients) {
        int count = 0;
        
        for(int ingredient : ingredients){
            stack.add(ingredient);
            
            while(canPack()){
                for(int i=0;i<4;i++){
                    stack.pop();
                }
                count++;
            }
        }
        
        return count;
    }
    
    public boolean canPack(){
        if(stack.size() < sequence.length){
            return false;
        }
        Stack <Integer>temp = new Stack<>();
        boolean valid = true;
        
        for(int i=0;i<4;i++){
            temp.add(stack.pop());
            if(sequence[i] != temp.peek()){
                valid=false;
                break;
            }
        }
        
        while(!temp.isEmpty()){
            stack.add(temp.pop());
        }
        
        return valid;
    }
}