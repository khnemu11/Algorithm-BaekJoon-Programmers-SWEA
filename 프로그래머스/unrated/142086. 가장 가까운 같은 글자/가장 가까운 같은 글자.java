/*
    알파벳 별 가장 최근에 나온 인덱스를 저장하여 길이 계산
*/

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int alphaIndexes[] = new int[26];
        int distances[] = new int[s.length()];
        Arrays.fill(alphaIndexes,-1);
        
        for(int i=0;i<s.length();i++){
            int distance = alphaIndexes[s.charAt(i)-'a'] == -1 ? -1 : i - alphaIndexes[s.charAt(i)-'a'];
            distances[i] = distance;
            alphaIndexes[s.charAt(i)-'a'] = i;
        }
        
        return distances;
    }
}