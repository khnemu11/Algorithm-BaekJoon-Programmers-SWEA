import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Map<String,Integer> strToNumMap = initMap();
        
        StringBuilder sb= new StringBuilder();
        
        //문자열을 탐색하여 1)숫자에 해당되는 문자열인 경우, 2)숫자인 경우 값을 더해주는 반복문
        for(int i=0;i<s.length();i++){
            //숫자인경우
            if(Character.isDigit(s.charAt(i))){
                answer = answer*10 + Character.getNumericValue(s.charAt(i));
            }
            //문자인경우
            else{
                sb.append(s.charAt(i));
                if(strToNumMap.get(sb.toString())!=null){
                    answer = answer*10 + strToNumMap.get(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        
        return answer;
    }
    //문자열 -> 숫자로 변경하는 맵을 초기화하는 메소드
    public Map<String,Integer> initMap(){
        Map<String,Integer> strToNumMap = new HashMap<>();
        
        strToNumMap.put("zero",0);
        strToNumMap.put("one",1);
        strToNumMap.put("two",2);
        strToNumMap.put("three",3);
        strToNumMap.put("four",4);
        strToNumMap.put("five",5);
        strToNumMap.put("six",6);
        strToNumMap.put("seven",7);
        strToNumMap.put("eight",8);
        strToNumMap.put("nine",9);
        
        return strToNumMap;
    }
}