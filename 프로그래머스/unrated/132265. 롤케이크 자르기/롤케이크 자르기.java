import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer,Integer> left = new HashMap<>();
        Map<Integer,Integer> right = new HashMap<>();
         int answer = 0;
        
        for(int i=0;i<topping.length;i++){
            if(i==0){
                left.put(topping[0],1);
                for(int j=1;j<topping.length;j++){
                    right.put(topping[j],right.getOrDefault(topping[j],0)+1);
                }
            }else{
                left.put(topping[i],left.getOrDefault(topping[i],0)+1);
                right.put(topping[i],right.get(topping[i])-1);
                
                if(right.get(topping[i])==0){
                    right.remove(topping[i]);
                }
                
            }
            if(left.keySet().size() == right.keySet().size()){
                answer++;
            }
        }
        
        return answer;
    }
}