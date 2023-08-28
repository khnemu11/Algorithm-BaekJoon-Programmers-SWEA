import java.util.*;
/*
    [[1, 1, 1, 0], [1, 1, 1, 0], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1]]
    
    1110
    1110
    0001
    0001
    0001
    0001
*/
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int dx[] = {-1,1,0,0};
        int dy[] = {0,0,-1,1};
        
        boolean[][] visited = new boolean[picture.length][picture[0].length];
        
        for(int row =0;row<picture.length;row++){
            for(int col =0;col<picture[0].length;col++){
                if(visited[row][col]){
                    continue;
                }
                if(picture[row][col] == 0){
                    continue;
                }
                numberOfArea++;
                // System.out.println("영역 개수 : "+numberOfArea);
                Coordinate coord = new Coordinate(row, col);
                
                Queue<Coordinate> q = new LinkedList<>();
                q.add(coord);
                int areaSize = 0;
                // System.out.println("시작 "+coord.row+" , "+coord.col);
                while(!q.isEmpty()){
                    Coordinate curr = q.poll();
                    
                    visited[curr.row][curr.col]=true;
                    areaSize++;
                    
                    for(int k=0;k<dx.length;k++){
                        Coordinate next = new Coordinate(curr.row + dx[k],curr.col + dy[k]);
                        if(next.row<0 || next.row>=picture.length || next.col<0 || next.col>=picture[0].length){
                            continue;
                        }
                        if(picture[next.row][next.col] != picture[coord.row][coord.col]){
                            continue;
                        }
                        if(visited[next.row][next.col]){
                            continue;
                        }
                        // System.out.println(next.row+" , "+next.col +" val : "+picture[next.row][next.col]);
                        visited[next.row][next.col]=true;
                        q.add(next);
                    }
                }
                
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea,areaSize);
                // System.out.println("maxSizeOfOneArea: "+maxSizeOfOneArea);
            }
        }        
        // System.out.println(numberOfArea+" "+maxSizeOfOneArea);
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}
class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }
}