import java.util.*;
/*
    풀이 과정
    
    cards 배열의 사이클을 형성하는 그룹을 나누어 가장 높은 그룹 2개의 곱을 리턴
    만약 그룹이 1개라면 0 리턴
*/
class Solution {
    public int solution(int[] cards) {
        boolean visited[] = new boolean[cards.length];
        
        //그룹 속 요소의 개수를 내림차순으로 정렬하는 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<cards.length;i++){
            int count = 0;
            int curr = i;
            
            while(!visited[curr]){
                visited[curr]=true;
                count++;
                curr = cards[curr]-1;
            }
            pq.add(count);
        }

        int score = 0;
        
        //크기가 2 이상이면 가장 큰 두 수의 곱을 리턴
        if(pq.size()>1){
            score = pq.poll() * pq.poll();
        }
        
        return score;
    }
}