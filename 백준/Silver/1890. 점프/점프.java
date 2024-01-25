import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static long[][] dp;
    static int[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];

        board = new int[N][N];
        dp = new long[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(input, 0, board[i], 0, input.length);
        }

        dp[0][0] = 1;
        visited[0][0] = true;

        System.out.println(getNumberOfCase(new Coordinate(N-1, N-1)));
//        printArr(dp);
    }

    public static void printArr(long[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static long getNumberOfCase(Coordinate curr){
//        System.out.println("확인할 좌표 : "+curr.row+" , "+curr.col);

        if(visited[curr.row][curr.col]){
            return dp[curr.row][curr.col];
        }

        visited[curr.row][curr.col] = true;

        int[] dx = {0, -1};
        int[] dy = {-1, 0};

        for (int i = curr.row-1; i >= 0; i--) {
            Coordinate before = new Coordinate(i, curr.col);

            if(board[before.row][before.col]+before.row == curr.row){
//                System.out.println(board[before.row][before.col]+" + "+before.row+" vs " +curr.row);
//                System.out.println("가능한 좌표 "+before.row+" , "+before.col);
                dp[curr.row][curr.col] += getNumberOfCase(before);
            }
        }

        for (int i = curr.col-1; i >= 0; i--) {
            Coordinate before = new Coordinate(curr.row, i);

            if(board[before.row][before.col]+before.col == curr.col){
//                System.out.println(board[before.row][before.col]+" + "+before.col+" vs " +curr.col);
//                System.out.println("가능한 좌표 "+before.row+" , "+before.col);
                dp[curr.row][curr.col] += getNumberOfCase(before);
            }
        }
//        System.out.println("확인할 좌표 : "+curr.row+" , "+curr.col+" 의 경우의 수 : "+dp[curr.row][curr.col]);
        return dp[curr.row][curr.col];
    }
}
class Coordinate{
    int row;
    int col;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    public boolean isEqual(Coordinate o){
        if(this.row == o.row && this.col == o.col){
            return true;
        }
        return false;
    }
}