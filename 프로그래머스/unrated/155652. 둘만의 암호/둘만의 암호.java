import java.util.*;
/*
    정규식을 이용해서 skip에 포함되는 알파벳이면 한번 더 가도록 설정
*/
class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        String regex = "[" + skip +"]"; //skip을 정규화식으로 변경
            
        //문자를 하나씩 꺼내서 index만큼 뒤로 한칸씩 옮기는데 skip에 포함되면 한번 더 옮기는 부분
        for(int i=0;i<s.length();i++){
            int cnt=0;
            char alpha = s.charAt(i);
            while(cnt < index){
                //z을 넘어서면 a로 설정
                alpha = (char)(alpha + 1) > 'z' ? 'a' : (char)(alpha + 1) ; 
                //skip에 포함되는 알파벳이면 한번 더
                if(String.valueOf(alpha).matches(regex)){
                    continue;
                }
                cnt++;
            }
            sb.append(alpha);
        }
        
        return sb.toString();
    }
}