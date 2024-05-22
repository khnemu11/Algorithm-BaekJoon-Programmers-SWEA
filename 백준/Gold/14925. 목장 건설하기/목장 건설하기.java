import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] dp;
    static int max;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new int[M+1][N+1];
        dp = new int[M+1][N+1];
        visited = new boolean[M+1][N+1];

        for(int row=1;row<=M;row++){
            st = new StringTokenizer(br.readLine());

            for(int col=1;col<=N;col++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for(int row=1;row<=M;row++){
            for(int col=1;col<=N;col++){
                getMax(row,col);
            }
        }

        System.out.println(max);
    }

    public static int getMax(int row, int col){
        if(visited[row][col]){
            return dp[row][col];
        }

        visited[row][col] = true;

        if(map[row][col]!=0){
            dp[row][col] = 0;
        }
        else if(row == 1 || col == 1){
            dp[row][col] = 1;
        }
        else{
            int available = Math.min(getMax(row-1,col-1),getMax(row,col-1));
            available = Math.min(available,getMax(row-1,col));
            dp[row][col] = available + 1;
        }

        max = Math.max(max,dp[row][col]);

        return dp[row][col];
    }
}