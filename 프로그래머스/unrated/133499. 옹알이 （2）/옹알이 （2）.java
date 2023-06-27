import java.util.*;

class Solution {
    String basics[] = {"aya","ye","woo","ma"};
    Set<String> set = new HashSet<>();
    
    public int solution(String[] babblings) {
        int count = 0;
        
        makeStr("","");
        
        //가능한 조합이 존재하는지 탐색
        for(String babbling : babblings){
            if(set.contains(babbling)){
                count++;
            }
        }
        
        
        return count;
    }
    //가능한 모든 조합을 구하는 메소드
    public void makeStr(String str,String before){
        if(str.length()>30){
            return;
        }
        set.add(str);
        
        for(String basic : basics){
            if(basic.equals(before)){
                continue;
            }    
            makeStr(str+basic,basic);
        }
    }
}