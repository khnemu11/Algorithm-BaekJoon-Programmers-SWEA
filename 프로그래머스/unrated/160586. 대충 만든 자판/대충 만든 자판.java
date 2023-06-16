import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        //<목표 알파벳,최소 횟수>를 저장하는 맵
        Map<Character,Integer> minCntByAlphaMap = new HashMap<>();
        
        //가장 빠르게 도달한 알파벳을 저장
        for(int i=0;i<keymap.length;i++){
            for(int j=0;j<keymap[i].length();j++){
                int minCnt = Math.min(minCntByAlphaMap.getOrDefault(keymap[i].charAt(j),Integer.MAX_VALUE),j+1); //최소 도달 횟수
                minCntByAlphaMap.put(keymap[i].charAt(j),minCnt);
            }
        }
        //결과를 저장하는 변수
        List<Integer> result = new ArrayList<>();
        //각 목표 단어의 알파벳의 최소 거리 횟수를 더하는 부분
        for(String target:targets){
            int count=0;
            for(int i=0;i<target.length();i++){
                //목표 단어를 만들 수 없다면 -1 저장
                if(minCntByAlphaMap.get(target.charAt(i))==null){
                    count=-1;
                    break;
                }
                count += minCntByAlphaMap.get(target.charAt(i));
            }
            result.add(count);
        }
        
        return result.stream().mapToInt(x->x).toArray();
    }
}