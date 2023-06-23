/*
    부분 문자열 탐색 -> substring사용
    숫자길이 18 -> long 사용
*/

import java.util.*;

class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        for(int i=0;i+p.length()-1<t.length();i++){
           String target = t.substring(i,i+p.length());
            if(Long.valueOf(target) <= Long.valueOf(p)){
                answer++;
            }
        }
        
        return answer;
    }
}