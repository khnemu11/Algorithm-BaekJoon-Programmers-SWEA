/*
    캐시 최대 크기 = 30
    최대 도시 수 = 100,000
    도시탐색 시간 = 30 * 100,000 < 1억
    
    가장 사용하지 않은 것을 사용
    -> 캐시에 들어간 시간을 이용하여 정렬 필요
    
    30 * 30log(30) * 100,000 > 1억
    
    이때 도시 탐색을 해쉬맵을 이용해 도시 추가/삭제를 할때 해쉬맵 변경
    
    30*log(30) * 100,000 < 1억
*/

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int excuteTime = 0;
        Map<String,Integer> cacheByName = new HashMap<>();
        
        for(String city : cities){
            city = city.toLowerCase();
            int max = 0;
            String target = "";
            
            if(cacheByName.get(city)==null){
                excuteTime+=5;
            }else{
                excuteTime+=1;
            }
            
            cacheByName.put(city,0);
            
            for(String key : cacheByName.keySet()){
                cacheByName.put(key,cacheByName.get(key)+1);

                if(max < cacheByName.get(key)){
                    max = cacheByName.get(key);
                    target = key;
                }
            }
            
            if(cacheByName.size()>cacheSize){
                cacheByName.remove(target);
            }
        }
        return excuteTime;
    }
}