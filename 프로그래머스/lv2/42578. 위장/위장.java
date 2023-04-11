import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        HashMap<String,Integer> map = new HashMap<>();
        
        for(int i=0;i<clothes.length;i++){
            map.put(clothes[i][1],map.getOrDefault(clothes[i][1],0)+1);
        }
        
        int total = 1;
        
        for(int cnt : map.values()){
            total = total*(cnt+1);
        }
        
        return total-1;
    }
}