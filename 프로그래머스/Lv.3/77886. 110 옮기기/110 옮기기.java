import java.util.*;
/*
    사전으로 가장 작은 숫자 -> 가장 작은 숫자
    하나의 인덱스로는 하나의 110만 만들어짐
    그러므로 110을 만드는 경우의 인덱스 조합을 한번에 뽑아낼 수 있음
    
    일단 가능한 110은 모두 빼고 남은 숫자에 110을 넣어서 최소 숫자를 만들자
    이때 110앞에 최대한 많은 0을 두는 것이 가장 작은 숫자이므로 가장 마지막에 나온 0 뒤에 놓으면 된다.
    
    한번에 선택이 서로에게 영향을 끼치지 않는다면 한번에 경우의 수를 만들어 보자
*/
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i=0;i<s.length;i++){
            answer[i] = makeSmallestString(s[i]);  
        }  
        
        return answer;
    }
    
    public String makeSmallestString(String str){
        StringBuilder sb = new StringBuilder();
        
        int count = 0;
        
        //110 추출
        for(int i=0;i<str.length();i++){
            sb.append(str.charAt(i));
            
             if(sb.length()>=3){
                String tail = sb.substring(sb.length()-3,sb.length());
                if(tail.equals("110")){
                    count++;
                    
                    sb.delete(sb.length()-3,sb.length());
                }
            }   
        }
        while(count-->0){
            int zeroIdx = sb.lastIndexOf("0");
            if(zeroIdx == -1){
                sb = sb.insert(0,110);
            }
            else if(zeroIdx == sb.length()-1){
                sb.append(110);
            }else{
                sb = sb.insert(zeroIdx+1,110);
            }
        }
        
        return sb.toString();
    }
}
