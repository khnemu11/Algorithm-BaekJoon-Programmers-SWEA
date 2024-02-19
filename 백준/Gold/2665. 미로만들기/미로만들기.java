import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    final static int INF = Integer.MAX_VALUE;
    final static int BLACK = 0;
    final static int WHITE = 1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][]board = new int[N][N];

        for(int i=0;i<N;i++){
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        boolean[][][] visited = new boolean[N*N][N][N];

        PriorityQueue<Case> q = new PriorityQueue<>();
        q.add(new Case(0,0,0));

        int minChange = N*N-2;
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        while(!q.isEmpty()){
            Case curr = q.poll();
            visited[curr.change][curr.row][curr.col] = true;

            if(curr.row == N-1 && curr.col == N-1){
                minChange = Math.min(minChange,curr.change);
                break;
            }

            for(int k=0;k<dx.length;k++){
                Case next = new Case(curr.row+dx[k],curr.col+dy[k],curr.change);

                if(next.row <0 || next.col<0 || next.row>=N || next.col>=N){
                    continue;
                }
                if(visited[next.change][next.row][next.col]){
                    continue;
                }
                if(board[next.row][next.col] == WHITE){
                    visited[next.change][next.row][next.col] = true;
                    q.add(next);
                }
                else if(board[next.row][next.col] == BLACK && next.change+1 < visited.length && !visited[next.change+1][next.row][next.col]){
                    visited[next.change+1][next.row][next.col] = true;
                    q.add(new Case(next.row,next.col,next.change+1));
                }
            }
        }

        System.out.println(minChange);
    }
}
class Case implements Comparable<Case>{
    int row;
    int col;
    int change;

    public Case(int row, int col, int change){
        this.row = row;
        this.col = col;
        this.change = change;
    }

    @Override
    public String toString() {
        return "Case{" +
                "row=" + row +
                ", col=" + col +
                ", change=" + change +
                '}';
    }

    @Override
    public int compareTo(Case o) {
        return this.change - o.change;
    }
}