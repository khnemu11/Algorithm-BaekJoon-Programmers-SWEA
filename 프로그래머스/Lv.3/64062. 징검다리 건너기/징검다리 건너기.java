import java.util.*;

/*
    건널 수 있다 -> 해당 범위만큼 움직일 수 있다 -> 슬라이딩 윈도우
    해당 범위에서 최대한 건널 수 있는 횟수 = 범위 내 징검다리의 최대값
    하나의 범위라도 건너지 못하면 모든 징검다리에서 건너지 못함
    => 범위내의 최대값 중 최소값이 모든 사람이 건널 수 있는 최대값이다
*/

class Solution {
    public int solution(int[] stones, int k) {
        int count = 0;
        int max = 0;
        PriorityQueue<Stone> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for(int i=0;i<=stones.length - k;i++){
            if(i == 0){
                for(int j=0;j<k;j++){
                   maxHeap.add(new Stone(j,stones[j]));
                }
            }else{
                maxHeap.add(new Stone(i+k-1,stones[i+k-1]));
            }
            
            while(maxHeap.peek().idx < i){
                maxHeap.poll();
            }
            
            minHeap.add(maxHeap.peek().val);
        }   
        
        return minHeap.poll();
    }
}
class Stone implements Comparable<Stone>{
    int idx;
    int val;
    
    public Stone(int idx,int val){
        this.idx = idx;
        this.val = val;
    }
    
    public int compareTo(Stone o){
        return this.val - o.val;
    }
}