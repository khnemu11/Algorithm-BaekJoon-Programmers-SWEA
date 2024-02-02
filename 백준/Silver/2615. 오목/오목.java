import java.io.*;

public class Main {
    final static int SIZE = 19;
    final static int EMPTY =0 ;
    final static int BLACK = 1;
    final static int WHITE = 2;

    public static void main(String[] args) throws Exception {
        int[][]board = new int[SIZE][SIZE];

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        for(int i=0;i<SIZE;i++){
            String[] splitInput = br.readLine().split(" ");
            for(int j=0;j<SIZE;j++){
                board[i][j] = Integer.parseInt(splitInput[j]);
            }
        }

        Stone result = findVictory(board);
        System.out.println(result.color);

        if(result.color != EMPTY){
            System.out.println((result.row+1) +" "+(result.col+1));
        }

    }
    public static Stone findVictory(int[][] board){
        int[] dx = {1,1,0,-1};
        int[] dy = {0,1,1,1};
        int[] inverseDx = {-1,-1,0,1};
        int[] inverseDy = {0,-1,-1,-1};

        boolean[][]visited = new boolean[SIZE][SIZE];

        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                visited[i][j] = true;

//                System.out.println(i+" , "+j);
                if(board[i][j] == EMPTY){
                    continue;
                }

                for(int k=0;k<dx.length;k++){
//                    System.out.println(i+" , "+j+" "+k);
                    Stone stone = new Stone(board[i][j],i,j);
                    Stone before = new Stone(EMPTY,i+inverseDx[k],j+inverseDy[k]);
                    
                    //6목 방지
                    if(!outOfArray(before,board) && board[before.row][before.col] == stone.color){
                        continue;
                    }

                    Stone next = new Stone(EMPTY,i+dx[k],j+dy[k]);

                    boolean victory = isVictory(stone,next,dx[k],dy[k],1,board,visited);

                    if(victory){
                        return stone;
                    }
                }
            }
        }

        return new Stone(0,0,0);
    }
    public static boolean isVictory(Stone start,Stone next, int dx, int dy,int length,int[][]board,boolean[][]visited){
//        System.out.println(start.row+" , "+start.col+" -> "+next.row+" , "+next.col);
        if(length == 5){
            if(outOfArray(next,board)){
                return true;
            }

            return board[next.row][next.col] != start.color;
        }if(outOfArray(next,board)){
            return false;
        }if(board[next.row][next.col] != start.color){
            return false;
        }

        visited[next.row][next.col] = true;

        next.row+=dx;
        next.col+=dy;

        return isVictory(start,next,dx,dy,length+1,board,visited);
    }

    public static boolean outOfArray(Stone stone, int[][]arr){
        if(stone.row <0 || stone.col <0 || stone.row >=arr.length || stone.col >=arr[0].length){
            return true;
        }
        return false;
    }
}

class Stone{
    int color;
    int row;
    int col;

    public Stone(int color,int row, int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }
}