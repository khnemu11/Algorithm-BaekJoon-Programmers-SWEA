/*
    가장 먼저 나감 -> 가장 먼저 들어옴 순서로 정렬
    현재 나간 차량이 있는데 아직 나가지 못한 차가 있다면 해당 차는 현재 나간 차량의 위치로 단속이 가능
    더이상 해당 위치로 단속하지 못하면 다음 차량 확인
*/

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        PriorityQueue<Route> pq = new PriorityQueue<>();
        
        for(int i=0;i<routes.length;i++){
            pq.add(new Route(routes[i][0],routes[i][1]));
        }
        
        int cnt=0;
        
        while(!pq.isEmpty()){
            Route curr = pq.poll();
            cnt++;
            while(!pq.isEmpty() && curr.end >=pq.peek().start){
                pq.poll();
            }
        }
        
        return cnt;
    }
}

class Route implements Comparable<Route>{
    int start;
    int end;
    
    public Route(int start, int end){
        this.start = start;
        this.end =end;
    }
    
    public int compareTo(Route o){
        if(o.end == this.end){
            return this.start-o.start;
        }
        return this.end - o.end;
    }
}