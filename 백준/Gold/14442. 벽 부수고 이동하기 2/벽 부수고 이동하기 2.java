import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {
    static final int IMPOSSIBLE = -1;
    static final int WALL = 1;
    static final int EMPTY = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int[]input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = input[0];
        int M = input[1];
        int K = input[2];

        int[][] map = new int[N][M];
        boolean[][][] visited = new boolean[K+1][N][M];

        for(int i=0;i<N;i++){
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int[]dx= {-1,1,0,0};
        int[]dy ={0,0,-1,1};
        Queue<Move> q = new LinkedList<>();
        q.add(new Move(0,0,0,1));
        int time = IMPOSSIBLE;

        while(!q.isEmpty()){
            Move move = q.poll();
            visited[move.wall][move.row][move.col] = true;

            if(move.row == N-1 && move.col == M-1){
                time = move.time;
                break;
            }
            for(int i=0;i<dx.length;i++){
                Move next = new Move(move.row+dx[i],move.col+dy[i],move.wall, move.time+1);

                if(next.row<0||next.col<0||next.row>=N ||next.col>=M){
                    continue;
                }if(visited[next.wall][next.row][next.col]){
                    continue;
                }else if(map[next.row][next.col] == WALL){
                    if(next.wall <K && !visited[next.wall+1][next.row][next.col]){
                        visited[next.wall+1][next.row][next.col] = true;
                        q.add(new Move(next.row,next.col,next.wall+1,next.time));
                    }else{
                        continue;
                    }
                }else{
                    visited[next.wall][next.row][next.col] = true;
                    q.add(next);
                }
            }
        }

        System.out.println(time);
    }
}
class Move{
    int row;
    int col;
    int wall;
    int time;
    public Move(int row, int col,int wall,int time){
        this.row = row;
        this.col = col;
        this.wall = wall;
        this.time = time;
    }
}
