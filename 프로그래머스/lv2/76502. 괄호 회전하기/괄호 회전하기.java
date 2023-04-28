import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        HashMap<Character,Character>pair = new HashMap<>();
        
        pair.put('}','{');
        pair.put(')','(');
        pair.put(']','[');
        
        for(int i=0;i<s.length();i++){
            String rotated = s.substring(1,s.length()) + s.charAt(0);
            Stack<Character>stack = new Stack<>();
            boolean valid=false;
            for(int j=0;j<rotated.length();j++){
                if(pair.get(rotated.charAt(j))==null){
                    stack.add(rotated.charAt(j));
                    continue;
                }

                if(stack.isEmpty() || stack.pop() != pair.get(rotated.charAt(j))){
                    valid=false;
                    break;
                }
                valid=true;
            }
            if(stack.isEmpty() && valid){
                answer++;
            }
            s = rotated;
        }
 
        return answer;
    }
    
}