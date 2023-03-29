import java.util.*;

class Solution {
    public String solution(String p) {
        return divide(0,p);
    }
    
    public String divide(int start, String str){
        if(start>=str.length()){
            return "";
        }
        
        Stack<Character> stack = new Stack<>();
        
        int open=0;
        int close =0 ;
        int idx =start;
        
        while(open==0 || open != close){
            if(str.charAt(idx) == '('){
                open++;
            }else {
                close++;
            }
            
            if(!stack.isEmpty() && stack.peek() == '(' && str.charAt(idx) == ')' ){
                stack.pop();
            }else{
                stack.push(str.charAt(idx));
            }
            idx++;
        }
        
        if(stack.isEmpty()){ //균형잡힌 괄호가 올바른 괄호 문자열인 경우
             String u = str.substring(start,idx) ;
             String v = divide(idx,str);
             return u + v;
        }else{ //아닌 경우
            String v = divide(idx,str);
            StringBuilder sb = new StringBuilder();

            for(int i = start+1;i<idx-1;i++){
                if(str.charAt(i) == '('){
                    sb.append(')');
                }else{
                    sb.append('(');
                }
            }
            return "(" + v+")" + sb.toString();
        }
    }
}
