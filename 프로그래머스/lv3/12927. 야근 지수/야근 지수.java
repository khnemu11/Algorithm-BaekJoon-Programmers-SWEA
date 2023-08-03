/*
    서로의 차가 가장 작을때 최소값이 된다.
    => 현재 가장 큰 수를 -1하는 것을 n번 반복한다
    => 우선순위 큐
 */  


import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work : works){
            pq.add(work);
        }
        
        for(int i=0;i<n;i++){
            if(pq.isEmpty()){
                break;
            }
            
            int work = pq.poll();
            work--;
            
            if(work !=0){
                pq.add(work);    
            }
            
        }
        
        long fatigueSum = 0;
        
        while(!pq.isEmpty()){
            fatigueSum += Math.pow(pq.poll(),2);    
        }
        
        return fatigueSum;
    }
}