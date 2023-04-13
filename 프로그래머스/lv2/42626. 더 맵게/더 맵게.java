/*

    k<==1,000,000,000 -> int 범위
    스코빌 <= 1,000,000 ->nlogn 알고리즘 필요
    
    작은 것부터 연산 -> 정렬 -> 우선순위 큐
*/

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> scovillePQ = new PriorityQueue<>();
        
        for(int val : scoville){
            scovillePQ.add(val);
        }
        
        while(scovillePQ.size()>1){
            int first = scovillePQ.poll();
            int second = scovillePQ.poll();
            
            if(first >=K){
                break;
            }
            
            int sum = first + second*2;
            scovillePQ.add(sum);
            
            answer++;
        }
        if(scovillePQ.size()==1 && scovillePQ.peek() < K){
            return -1;
        }
        
        return answer;
    }
}