
import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[] dx = {1,0,-1};
        int[] dy = {0,1,-1};
        
        int[][] triangle = new int[n][];
        
        for(int length=1;length<=n;length++){
            triangle[length-1] = new int[length];
        }
        
        int round = 2;
        int target = n*(n+1)/2;
        int direction = 0;
        triangle[0][0] = 1;
        Coordinate curr = new Coordinate(0,0);
        
        while(round <= target){
            Coordinate next = new Coordinate(curr.row + dx[direction],
                                          curr.col + dy[direction]);

            if(outOfRange(next , triangle) || triangle[next.row][next.col]!=0){
                direction = (direction+1) % dx.length;
                next = new Coordinate(curr.row + dx[direction],
                                             curr.col + dy[direction]);
            }
            triangle[next.row][next.col] = round;
            
            curr = next;
            round++;
        }
        int[] answer = new int[target];
        int idx = 0;
        
        for(int i=0;i<triangle.length;i++){
            for(int j=0;j<triangle[i].length;j++){
                answer[idx++] = triangle[i][j];
            }
        }
        return answer;
    }
    
    public boolean outOfRange(Coordinate coord, int[][] map){
        if(coord.row <0 || coord.col<0 || coord.row>=map.length || coord.col>=map[coord.row].length){
            return true;
        }
        return false;
    }
}
class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row =row;
        this.col =col;
    }
}