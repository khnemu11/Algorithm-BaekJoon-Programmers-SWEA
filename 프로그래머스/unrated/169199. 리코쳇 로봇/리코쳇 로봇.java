import java.util.*;

class Solution {
    int min = Integer.MAX_VALUE;
    int visited[][];
    int dx[] ={-1,1,0,0};
    int dy[] = {0,0,-1,1};
    
    public int solution(String[] board) {
        visited = new int[board.length][board[0].length()];
        for(int i=0;i<visited.length;i++){
            Arrays.fill(visited[i],Integer.MAX_VALUE);
        }
        boolean find = false;
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length();j++){
                if(board[i].charAt(j) == 'R'){
                    goNext(new Coordinate(i,j),board,0);
                    find=true;
                    break;
                }
            }   
            if(find){
                break;
            }
        }
        if(min == Integer.MAX_VALUE){
            return -1;
        }
        return min;
    }
    
    public void goNext(Coordinate curr, String board[],int cnt){
        // System.out.println(curr.row+" , "+curr.col+" cnt : "+cnt);
        if(board[curr.row].charAt(curr.col)=='G'){
            // System.out.println("cnt : "+cnt);
            min = Math.min(cnt,min);
            return;
        }else if(cnt >= min){
            return ;
        }else{
            visited[curr.row][curr.col]=cnt;
            for(int k=0;k<dx.length;k++){
                Coordinate next = new Coordinate(curr.row,curr.col);
                if(outOfArray(board,next)){
                    continue;
                }

                while(true){
                    Coordinate nextnext = new Coordinate(next.row+dx[k],next.col+dy[k]);
                    if(outOfArray(board,nextnext) ||board[nextnext.row].charAt(nextnext.col) =='D'){
                        break;
                    }
                    next = nextnext;
                }

                if(visited[next.row][next.col] < cnt+1){
                    continue;
                }
                // System.out.println("move : "+k);
                goNext(next,board,cnt+1);       
            }
        }
    }
    public boolean outOfArray(String[] board, Coordinate coord){
    if(coord.row<0 || coord.col<0 || coord.row>=board.length|| coord.col>=board[0].length()){
        return true;
    }
    
    return false;
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