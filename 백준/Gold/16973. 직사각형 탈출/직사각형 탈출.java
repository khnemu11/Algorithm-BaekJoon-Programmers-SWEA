import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][]map = new int[N+1][M+1];
        int[][]preSum = new int[N+1][M+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1;j<=M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++) {
                preSum[i][j] = preSum[i][j-1]+preSum[i-1][j]-preSum[i-1][j-1] + map[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int Sr = Integer.parseInt(st.nextToken());
        int Sc = Integer.parseInt(st.nextToken());
        int Fr = Integer.parseInt(st.nextToken());
        int Fc = Integer.parseInt(st.nextToken());

        Rect S = new Rect(Sr,Sc,Sr+H-1,Sc+W-1,0);

        Queue<Rect> q = new LinkedList<>();
        q.add(S);

        int[]dx = {-1,1,0,0};
        int[]dy = {0,0,-1,1};
        boolean[][] visited = new boolean[N+1][M+1];

        int min = -1;

        while(!q.isEmpty()){
            Rect curr =q.poll();
            visited[curr.leftTopRow][curr.leftTopCol] = true;

            if(curr.leftTopRow == Fr && curr.leftTopCol == Fc){
                min = curr.count;
                break;
            }

            for(int i=0;i<dx.length;i++){
                Rect next = new Rect(curr.leftTopRow + dx[i]
                        ,curr.leftTopCol + dy[i]
                        ,curr.rightBottomRow + dx[i]
                        ,curr.rightBottomCol + dy[i]
                        ,curr.count+1);

                if(next.leftTopRow < 1 || next.rightBottomRow > N || next.leftTopCol < 1 || next.rightBottomCol > M){
                    continue;
                }
                if(visited[next.leftTopRow][next.leftTopCol]){
                    continue;
                }
                if(getPreSum(H,W,preSum,next) > 0){
                    continue;
                }

                visited[next.leftTopRow][next.leftTopCol] = true;
                q.add(next);
            }
        }

        System.out.println(min);
    }

    public static int getPreSum(int H ,int W, int[][]preSum, Rect rect){
        return preSum[rect.rightBottomRow][rect.rightBottomCol]
                - preSum[rect.leftTopRow-1][rect.rightBottomCol]
                - preSum[rect.rightBottomRow][rect.leftTopCol-1]
                + preSum[rect.leftTopRow-1][rect.leftTopCol-1];
    }
}

class Rect{
    int leftTopRow;
    int leftTopCol;
    int rightBottomRow;
    int rightBottomCol;
    int count;
    public Rect(int leftTopRow, int leftTopCol,int rightBottomRow,int rightBottomCol,int count){
        this.leftTopRow = leftTopRow;
        this.leftTopCol = leftTopCol;
        this.rightBottomRow = rightBottomRow;
        this.rightBottomCol = rightBottomCol;
        this.count = count;
    }

}