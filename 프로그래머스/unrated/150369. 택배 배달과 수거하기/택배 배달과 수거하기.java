import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
       long answer = 0;
        
        Stack<Work> d_stack = initStack(deliveries);
        Stack<Work> p_stack = initStack(pickups);
        
        while(!d_stack.isEmpty() && !p_stack.isEmpty()){
            if(d_stack.peek().distance > p_stack.peek().distance){
                answer = answer + d_stack.peek().distance*2;
            }
            else{
                 answer = answer + p_stack.peek().distance*2;
            }
            work(d_stack,cap);
            work(p_stack,cap);
        }
        
        while(!d_stack.isEmpty()){
            answer = answer + d_stack.peek().distance*2;
            work(d_stack,cap);
        }
        while(!p_stack.isEmpty()){
            answer = answer + p_stack.peek().distance*2;
            work(p_stack,cap);
        }
        
        return answer;
    }
    
    public Stack<Work> initStack(int [] works){
        Stack<Work> stack = new Stack<>();
        for(int i=0;i<works.length;i++){
            if(works[i]==0){
                continue;
            }
            stack.add(new Work(i+1,works[i]));
        }
        return stack;
    }
    public void work(Stack<Work> works, int cap){  
        while(!works.isEmpty() && cap > 0){
            works.peek().count -=cap;
            cap=0;
            
            if(works.peek().count<=0){
                cap = works.peek().count*-1;
                works.pop();
            }
        }
    } 
    
}
class Work{
    int distance;
    int count;
    
    public Work(int distance, int count){
        this.distance = distance;
        this.count = count;
    }
    
    @Override
    public String toString(){
        return "distance : " + this.distance + " count : "+this.count;
    }
}