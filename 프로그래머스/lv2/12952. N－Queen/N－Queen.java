/*
    N-Queen => 백트래킹 => dfs
    
    걸린시간 : 13분 26초
*/
class Solution {
    int count = 0; //퀸을 놓을 수 있는 개수
    int[][] board;  //체스판
    int[] dx = {-1,-1,-1,0,0,1,1,1};
    int[] dy = {-1,0,1,-1,1,-1,0,1};
    
    public int solution(int n) {
        board = new int[n][n];
        pickQueen(0);
        
        return count;
    }
    //행마다 재귀하여 열마다 퀸을 놓을 수 있는지 판단하고 퀸을 놓는 메소드
    public void pickQueen(int depth){
        //끝까지 갔다면 가능한 퀸의 경우의 수이므로 개수 추가
        if(depth==board.length){
            count++;
        }
        //열별로 순회하여 퀸 세워보기
        for(int i=0;i<board.length;i++){
            Coordinate queen = new Coordinate(depth,i);
            //퀸을 놓을 수 없다면 다음 퀸으로
            if(!canSetQueen(queen)){
                continue;
            }
            //퀸세우기
            board[queen.row][queen.col]=1;
            pickQueen(depth+1);
            //세운 퀸 지우기
            board[queen.row][queen.col]=0;
        }
    }
    //8방향을 끝까지 가서 퀸이 있는지 확인하는 메소드
    public boolean canSetQueen(Coordinate start){
        for(int i=0;i<dx.length;i++){
            Coordinate curr = new Coordinate(start.row+dx[i],start.col+dy[i]);
            
            while(!isOutOfMap(curr,board)){
                if(board[curr.row][curr.col]>0){
                    return false; 
                }
                curr = new Coordinate(curr.row+dx[i],curr.col+dy[i]);
            }
        }
        return true;
    }
    //좌표가 맵 밖을 나가는지 확인하는 메소드
    public boolean isOutOfMap(Coordinate coord, int[][] map){
        if(coord.row<0 || coord.col<0|| coord.row>=map.length || coord.col>=map[0].length){
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