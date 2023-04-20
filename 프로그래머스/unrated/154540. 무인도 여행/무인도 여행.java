import java.util.*;

class Solution {
    boolean visited[][]; 
    int sum=0;
    public int[] solution(String[] maps) {
        visited = new boolean[maps.length][maps[0].length()]; 
        PriorityQueue<Integer>pq = new PriorityQueue<>();
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                if(maps[i].charAt(j)=='X'){
                    continue;
                }
                if(visited[i][j]){
                    continue;
                }
                int cost =getCost(new Coordinate(i,j),maps);
                pq.add(cost);
            }   
        }
        
        int arr[]=null;
        
        if(pq.size()==0){
            arr = new int[1];
            arr[0]=-1;
        }else{
            arr = new int[pq.size()];
        
            for(int i=0;i<arr.length;i++){
                arr[i] = pq.poll();
            }
        }
        
        return arr;
    }
    
    public int getCost(Coordinate coord,String[] board){
        int sum = 0;
        
        int dx[] = {-1,1,0,0};
        int dy[] = {0,0,-1,1};
        
        Queue<Coordinate>q = new LinkedList<>();
        q.add(coord);
        
        while(!q.isEmpty()){
            Coordinate curr = q.poll();
            
            visited[curr.row][curr.col]=true;
            sum+=board[curr.row].charAt(curr.col)-'0';
            for(int k=0;k<dx.length;k++){
                Coordinate next = new Coordinate(curr.row+dx[k],curr.col+dy[k]);

                if(next.row<0 || next.col<0 || next.row>=board.length || next.col>=board[0].length()){
                    continue;
                }
                if(visited[next.row][next.col]){
                    continue;
                }
                if(board[next.row].charAt(next.col)=='X'){
                    continue;
                }
                visited[next.row][next.col]=true;
                q.add(next);
            }
        }
        return sum;
    }
}

class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row= row;
        this.col = col;
    }
}
