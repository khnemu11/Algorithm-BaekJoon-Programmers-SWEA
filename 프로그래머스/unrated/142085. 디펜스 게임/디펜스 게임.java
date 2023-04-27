/*
    현재 라운드 까지 큰 수로 정렬 -> 우선순위 큐 사용
    
    현재까지 병사 수가 소지하고 있는 병사보다 많은 경우 무적권을 소비하고 가장 큰 병사만큼 회복
    더이상 불가 -> 이전라운드 까지 가능
*/

import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int round = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<enemy.length;i++){
            pq.add(enemy[i]);
            n -=enemy[i];
            //무적권을 안 쓰고 병사를 못막을 때
            while(n<0){
                //무적권이 없는 경우
                if(k==0){
                    break;
                }
                n+=pq.poll();
                k--;
            }
            if(n<0){
                break;
            }
            round++;
        }

        return round;
    }
}