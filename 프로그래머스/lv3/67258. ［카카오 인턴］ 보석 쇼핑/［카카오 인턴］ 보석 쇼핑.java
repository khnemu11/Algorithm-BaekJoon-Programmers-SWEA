import java.util.*;
/*
    범위 -> 투포인터, 슬라이드 윈도우
    슬라이드 크기 불분명 -> 투포인터 사용
*/
class Solution {
    public int[] solution(String[] gems) {
        Map<String,Integer> gemMap = new HashMap<>();
        Set<String> gemSet = new HashSet<>();
        
        //보석 종류 개수를 위해 중복 제거
        for(int i=0;i<gems.length;i++){
            gemSet.add(gems[i]);
        }
        
        int l = 0;
        int r = 0;
        int range[] = new int[2]; //결과 범위
        int distance = gems.length+1; //최소 길이
        
        //투포인터로 보석 범위 탐색 시작
        while(true){
            //보석이 모든 종류가 있다면 먼저 구매한 보석을 제거
           if(gemMap.size() == gemSet.size()){
               gemMap.put(gems[l],gemMap.get(gems[l])-1);
                
               if(gemMap.get(gems[l])==0){
                   gemMap.remove(gems[l]);
               }
               l++;
           }else if(r == gems.length){
               break;
           }
            //모든 보석 종류가 없다면 
            else{
               gemMap.put(gems[r],gemMap.getOrDefault(gems[r],0)+1);
               r++;
           }
            //최소거리 등록
            if(gemMap.size() == gemSet.size() && distance > r - l){
                range[0]=l+1;
                range[1]=r;
                distance = r-l;
            }
        }
        return range;
    }
}