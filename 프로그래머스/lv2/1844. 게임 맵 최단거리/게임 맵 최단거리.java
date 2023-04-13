/*
    목표위치로 갈 때 최소시간을 구하는 4방향 BFS
*/

import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int dx [] = {-1,1,0,0};
        int dy [] = {0,0,-1,1};
        int time=-1;
        boolean visited [][] = new boolean[maps.length][maps[0].length];
        
        PriorityQueue<Robot> pq = new PriorityQueue<>();
        pq.add(new Robot(0,0,0));
        
        while(!pq.isEmpty()){
            Robot curr = pq.poll();
            
            //목표 위치에 다다른 경우
            if(curr.row == maps.length-1 && curr.col == maps[0].length-1){
                time = curr.time+1;
                break;
            }
            
            for(int k=0;k<dx.length;k++){
                Robot next = new Robot(curr.row + dx[k], curr.col + dy[k], curr.time+1);
               
                //배열 밖으로 벗어나는 경우
                if(next.row<0 || next.col<0 || next.row>=maps.length || next.col>=maps[0].length){
                    continue;
                }
                //벽인 경우
                if(maps[next.row][next.col]==0){
  
                    continue;
                }
                //이미 방문한 경우
                if(visited[next.row][next.col]){ 
                    continue;
                }
                visited[next.row][next.col] = true;
                pq.add(next);
            }
        }
            
        return time;
    }
}
class Robot implements Comparable<Robot>{
    int row;
    int col;
    int time;
    
    public Robot(int row,int col, int time){
        this.row=row;
        this.col=col;
        this.time=time;
    }
    
    @Override
    public int compareTo(Robot o){
        return this.time - o.time;
    }
}