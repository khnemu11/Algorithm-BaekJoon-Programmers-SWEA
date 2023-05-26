/*
    아래, 오른쪽, 위(왼쪽 위), 왼쪽 이동
    아무데도 가지 못할 때까지 이동
*/

import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[][] arr = new int[n+1][n+1];
        boolean[][] visited = new boolean[n+1][n+1];
        ArrayList<Integer> list = new ArrayList<>();
        
        int dx[] = {1,0,-1,0};
        int dy[] = {0,1,-1,-1};
        
        Coordinate curr = new Coordinate(0,0);
        int direction = 0;
        int end = n*(n+1)/2;
        for(int i=0;i<end;i++){
            visited[curr.row][curr.col]=true;
            arr[curr.row][curr.col]=i+1;
            // System.out.println((i+1)+" : "+curr.row+" , "+curr.col);
            Coordinate next = new Coordinate(curr.row + dx[direction],curr.col + dy[direction]);
            if(next.row<0 || next.row>=n || next.col<0 || next.col>=n){
                direction = (direction + 1) % dx.length;
                next = new Coordinate(curr.row + dx[direction],curr.col + dy[direction]);
            }
            if(visited[next.row][next.col]){
                if(direction == 2){
                    direction = 0;
                    next = new Coordinate(curr.row + 1,curr.col);
                }else{
                    direction = (direction + 1) % dx.length;
                    next = new Coordinate(curr.row + dx[direction],curr.col + dy[direction]);
                }
            } 
            curr = next;
        }
        // System.out.println(Arrays.deepToString(arr));
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j] ==0){
                    break;
                }
                list.add(arr[i][j]);
            }   
        }
        return list.stream().mapToInt(x->Integer.valueOf(x)).toArray();
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