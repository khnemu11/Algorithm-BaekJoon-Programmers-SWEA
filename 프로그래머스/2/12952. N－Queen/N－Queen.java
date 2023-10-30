/*
    걸린 시간 : 12분 12초
*/

class Solution {
    boolean[][] visited;
    int[][] board;
    int[] dx = {-1,-1,-1,0,0,1,1,1};
    int[] dy = {-1,0,1,-1,1,-1,0,1};
    int count = 0;
    
    public int solution(int n) {
        board = new int[n][n];
        visited = new boolean[n][n];
        
        pickQueen(0);
        
        return count;
    }
      
    public void pickQueen(int row){
        if(row == board.length){
            count++;      
            return;
        }
        
        for(int col=0;col<board[row].length;col++){
            Coordinate curr = new Coordinate(row,col);
           
            //현재 좌표가 놓을 수 없는 좌표면 패스
            if(!isValid(curr)){
                continue;
            }
            
            visited[curr.row][curr.col]=true;
            
            //다음 행 선택
            pickQueen(row+1);
            
            visited[curr.row][curr.col]=false;
        }
    }
    public boolean isValid(Coordinate coord){
        for(int k=0;k<dx.length;k++){
            Coordinate next = new Coordinate(
                coord.row + dx[k],
                coord.col + dy[k]);
            
            while(!isOutOfBoard(next)){
                if(visited[next.row][next.col]){
                    return false;
                }
                
                next = new Coordinate(
                    next.row + dx[k],
                    next.col + dy[k]);
            }
        }
        
        return true;
    }
    
    public boolean isOutOfBoard(Coordinate coord){
        if(coord.row <0 || coord.col <0 || coord.row >=board.length || coord.col>=board[0].length){
            return true;
        }
        return false;
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