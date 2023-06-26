import java.util.*;

/*
    k크기의 min heap 우선순위 큐를 이용해 크기가 k에 도달하면 가장 작은것은 꺼내고 peek한 값을 저장한다.
*/

class Solution {
    public int[] solution(int k, int[] score) {
        //n일차일 때 가장 작은 점수를 가지고 있는 배열
        int[] minScore = new int[score.length];
        
        //가장 작은 값을 항상 보여주는 min heap 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        //각 일차마다 가장 작은 값을 저장하는 부분
        for(int i=0;i<score.length;i++){
            pq.add(score[i]);
            
            //k일이 넘어서면 k명을 유지하기 위해 가장 낮은 점수 탈락
            if(pq.size() > k){
                pq.poll();   
            }
            
            minScore[i] = pq.peek();
        }
        
        return minScore;
    }
}