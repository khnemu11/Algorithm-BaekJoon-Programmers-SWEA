import java.util.*;

class Solution {
    public String solution(String p) {
        return makeBalancedString(p);
    }
    
    public String makeBalancedString(String str){
        if(str.equals("")){
            return "";
        }
        if(checkRightString(str)){
            return str;
        }
        //균형잡힌 괄호 문자열 찾기
        int leftCount = 0;
        int rightCount = 0;
        String u= "";
        String v= "";
        
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '('){
                leftCount++;
            }else if(str.charAt(i) == ')'){
                rightCount++;
            }
            
            if(leftCount == rightCount){
                u = str.substring(0,i+1);
                if(i+1 < str.length()){
                    v = str.substring(i+1,str.length());
                }
                break;
            }
        }
        String balancedStr = "";
        System.out.println("u : "+u+" v : "+v);
        if(checkRightString(u)){
            balancedStr = u + makeBalancedString(v);
        }else{
            balancedStr = "(";
            balancedStr += makeBalancedString(v);
            balancedStr += ")";
            
            u = u.substring(1,u.length()-1);
            
            for(int i=0;i<u.length();i++){
                if(u.charAt(i) == '('){
                    balancedStr+=')';
                }else if(u.charAt(i) == ')'){
                    balancedStr+='(';
                }
            }
        }
        
        return balancedStr;
    }
    public boolean checkRightString(String str){
        Stack<Character> stack = new Stack();
        
        for(int i=0;i<str.length();i++){
            char blanket =str.charAt(i);
            
            //( 경우
            if(blanket == '('){
                stack.add(blanket);
            }
            //) 경우
            else if(stack.size() !=0 && stack.peek() == '('){
                stack.pop();
            }else{
                stack.add(blanket);
            }
        }
        
        if(stack.size() == 0){
            return true;
        }
        
        return false;
    }
}