/*
    A,E,I,O,U를 이용해서 단어 생성 -> O(n) = 2^5
    모든 문자를 정렬 -> nlogn = 2^5 log 2^5
    앞에서부터 탐색하여 word의 위치를 리턴
*/

import java.util.*;

class Solution {
    TreeSet<String> wordList = new TreeSet<>();
    
    public int solution(String word) {
        selectAlpha(0,"AEIOU",new StringBuilder());
        
        int seq=1;
        
        for(String candidate : wordList){
            if(candidate.equals(word)){
                break;
            }
            seq++;
        }
        return seq;
    }
    
    public void selectAlpha(int idx, String alphaList, StringBuilder sb){
        if(idx >= alphaList.length()){
            if(sb.length()==0){
                return ;
            }
            wordList.add(sb.toString());
        }else{
            selectAlpha(idx+1,alphaList,sb);
            for(int i=0;i<alphaList.length();i++){
                  sb.append(alphaList.charAt(i));
                  selectAlpha(idx+1,alphaList,sb);
                  sb.deleteCharAt(sb.length()-1);
            }
        }   
    } 
}