/*
    시작-> 레버, 레버->도착까지 최단거리 구하기
    
    길이가 짧아 bfs가능
    
    걸린시간 : 9분
*/

import java.util.*;

class Solution {
    public int solution(String[] maps) {
        Coordinate start = null;
        Coordinate lever = null;
        Coordinate end = null;
        
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                if(maps[i].charAt(j)=='S'){
                    start = new Coordinate(i,j);
                }else if(maps[i].charAt(j)=='L'){
                    lever = new Coordinate(i,j);
                }else if(maps[i].charAt(j)=='E'){
                    end = new Coordinate(i,j);
                }
            }   
        }

        int startToLever = getMinCost(start,lever,maps);
        int leverToEnd = getMinCost(lever,end,maps);

        if(startToLever==Integer.MAX_VALUE || leverToEnd==Integer.MAX_VALUE){
            return -1;
        }
        
        return startToLever+leverToEnd;
    }
    
    public int getMinCost(Coordinate start, Coordinate end,String[] maps){
        int dx[]={-1,1,0,0};
        int dy[]={0,0,-1,1};
        int visited[][] =new int [maps.length][maps[0].length()];

        for(int i=0;i<visited.length;i++){
            Arrays.fill(visited[i],Integer.MAX_VALUE);
        }
        visited[start.row][start.col]=0;
        
        Queue<Coordinate> q = new LinkedList<>();
        q.add(start);
        
        while(!q.isEmpty()){
            Coordinate curr = q.poll();
            
            if(curr.row == end.row && curr.col == end.col){
                break;
            }
            
            for(int k=0;k<dx.length;k++){
                Coordinate next = new Coordinate(curr.row+dx[k],curr.col+dy[k]);
                
                if(next.row<0 || next.col<0 || next.row>=maps.length || next.col>=maps[0].length()){
                    continue;
                }
                if(maps[next.row].charAt(next.col)=='X'){
                    continue;
                }
                if(visited[next.row][next.col] <= visited[curr.row][curr.col]+1){
                    continue;
                }
                
                visited[next.row][next.col] = visited[curr.row][curr.col]+1;
                q.add(next);
            }
        }
        return visited[end.row][end.col];
    }
}
class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row=row;
        this.col=col;
    }
}