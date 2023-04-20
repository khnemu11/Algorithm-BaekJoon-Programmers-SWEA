/*
    목표 숫자를 먼저 도달한 것을 실행 -> bfs 와 dp
    
    걸린시간 : 5분
*/

import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int minCost[] = new int[y+1];
        int INF = 2_000_000;
        Arrays.fill(minCost,INF);
        minCost[x]=0;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        
        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr==y){
                break;
            }
            
            if(curr+n <minCost.length && minCost[curr+n] > minCost[curr]+1){
                minCost[curr+n] =minCost[curr]+1;
                q.add(curr+n);
            }if(curr*2 <minCost.length && minCost[curr*2] > minCost[curr]+1){
                minCost[curr*2] =minCost[curr]+1;
                q.add(curr*2);
            }if(curr*3 <minCost.length && minCost[curr*3] > minCost[curr]+1){
                minCost[curr*3] =minCost[curr]+1;
                q.add(curr*3);
            }
        }
        
        if(minCost[y]==INF){
            return -1;
        }
         
        return minCost[y];
    }
}