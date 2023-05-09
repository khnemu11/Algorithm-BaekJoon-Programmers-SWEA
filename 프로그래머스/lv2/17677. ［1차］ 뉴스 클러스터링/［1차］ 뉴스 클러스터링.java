import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int cross=0;
        int combine=0;
        
        HashMap<String,Integer>map1 =  makeMulitSet(str1);
        HashMap<String,Integer>map2 =  makeMulitSet(str2);
        
        for(String key : map1.keySet()){
            if(map2.containsKey(key)){
                cross+=Math.min(map1.get(key),map2.get(key));
                combine+=Math.max(map1.get(key),map2.get(key));
            }else{
                combine+=map1.get(key);
            }
        }
        for(String key : map2.keySet()){
            if(map1.containsKey(key)){
                continue;
            }
            combine+=map2.get(key);
        }
        
        if(combine == 0){
            return 65536;
        }
        
        double answer = (double)cross/combine*65536;
            
        return (int)answer;
    }
    
    public HashMap<String,Integer> makeMulitSet(String str){
        HashMap<String,Integer> multiSet = new HashMap<>();
        
         for(int i=0;i<str.length()-1;i++){
            String a = str.substring(i,i+2).toLowerCase();
            if(!a.matches("[a-zA-Z]{2}")){
                continue;
            }
            multiSet.put(a,multiSet.getOrDefault(a,0)+1);
        }
        
        return multiSet;
    }
}