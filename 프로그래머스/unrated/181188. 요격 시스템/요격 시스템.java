import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        PriorityQueue<Missile> pq = new PriorityQueue<>();
        
        for(int i=0;i<targets.length;i++){
            pq.add(new Missile(targets[i][0],targets[i][1]));
        }
        int cnt = 1;
        Missile last = pq.poll();
        
        while(!pq.isEmpty()){
            Missile curr = pq.poll();
            if(last.end <= curr.start){
                cnt++;
                last = curr;
            }
        }
        
        return cnt;
    }
}

class Missile implements Comparable<Missile>{
    int start;
    int end;
    
    public Missile(int start, int end){
        this.start =start;
        this.end =end;
    }
    
    public int compareTo(Missile o){
        if(o.end == this.end){
            return this.start - o.start;
        }
        return this.end - o.end;
    }
}