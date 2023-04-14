/*
    모든 노드 통행 가능 -> MST
    경로를 제공 -> 크루스칼 사용
*/

import java.util.*;

class Solution {
    int parents[];
    public int solution(int n, int[][] costs) {
        PriorityQueue<Path> pq = new PriorityQueue<>();
        
        parents = new int[n];
        for(int i=0;i<n;i++){
            parents[i]=i;
        }
        
        for(int i=0;i<costs.length;i++){
            pq.add(new Path(costs[i][0],costs[i][1],costs[i][2]));
        }
        
        int totalCost=0;
        
        while(!pq.isEmpty()){
            Path path = pq.poll();
            
            if(union(path.from,path.to)){
                totalCost += path.cost;
            }
        }
        
        return totalCost;
    }
    public int getParent(int child){
        if(parents[child]==child){
            return child;
        }
        parents[child] = getParent(parents[child]);
        
        return parents[child];
    }
    public boolean union(int a, int b){
        int pa = getParent(a);
        int pb = getParent(b);
        
        if(pa==pb){
            return false;
        }
        else if(pa<pb){
            parents[pb]=pa;
        }else{
            parents[pa]=pb;
        }
        
        return true;
    }
}


class Path implements Comparable<Path>{
    int from;
    int to;
    int cost;
    
    public Path(int from, int to, int cost){
        this.from = from;
        this.to=to;
        this.cost=cost;
    }
    
    public int compareTo(Path o){
        return this.cost -o.cost;
    }
}