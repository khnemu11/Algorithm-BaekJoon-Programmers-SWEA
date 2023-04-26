/*
    1~100_000의 무게가 존재하는지 판단하는 배열 생성
    이를 이용해서 *2,*3,*4가 존재 하는지 확인
    있다면 페어/없다면 불가
    -> 시간 초과
    
    작은 순서로 정렬
    -> 현재 무게가 가장 큰 무게가 됨
    -> 이전 무게와 비교했을 때 자신이 더 멀리 앉는 경우가 없음
    (현재 무게) *2 /3
    (현재 무게) *1 /2
    (현재 무게) *3 /4
    (현재 무게) == (이전무게)
    4가지 종류가능
*/

import java.util.*;

class Solution {
    public long solution(int[] weights) {
        HashMap<Double,Integer> map = new HashMap<>();
        
        Arrays.sort(weights);
        long cnt=0;
        for(int weight : weights){
            if(map.get(weight*1.0) !=null){
                cnt = cnt + map.get(weight*1.0);
            }if(map.get(weight*1.0/2.0) !=null){
                cnt = cnt + map.get(weight*1.0/2.0);
            }if(map.get(weight*2.0/3.0) !=null){
                cnt = cnt + map.get(weight*2.0/3.0);
            }if(map.get(weight*3.0/4.0) !=null){
                cnt = cnt + map.get(weight*3.0/4.0);
            }
            map.put(weight*1.0,map.getOrDefault(weight*1.0,0)+1);
        }
    
        return cnt;
    }
}