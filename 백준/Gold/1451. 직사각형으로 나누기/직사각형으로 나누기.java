import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static long max =0;
    static int[][] map;
    static int[][] preSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        preSum = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < input.length(); j++) {
                map[i + 1][j + 1] = input.charAt(j) - '0';
                preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + map[i + 1][j + 1];
            }
        }

        //세로 3개
        for (int col1 = 1; col1 <= M - 2; col1++) {
            for (int col2 = col1 + 1; col2 <= M - 1; col2++) {
                Rect rect1 = new Rect(1, 1, N, col1);
                Rect rect2 = new Rect(1, col1 + 1, N, col2);
                Rect rect3 = new Rect(1, col2 + 1, N, M);

                max = Math.max(max, getPreSum(rect1) * getPreSum(rect2) * getPreSum(rect3));
            }
        }

        //가로 3개
        for (int row1 = 1; row1 <= N - 2; row1++) {
            for (int row2 = row1 + 1; row2 <= N - 1; row2++) {
                Rect rect1 = new Rect(1, 1, row1, M);
                Rect rect2 = new Rect(row1 + 1, 1, row2, M);
                Rect rect3 = new Rect(row2 + 1, 1, N, M);

                max = Math.max(max, getPreSum(rect1) * getPreSum(rect2) * getPreSum(rect3));
            }
        }
        //왼1 오2
        for (int row = 1; row < N; row++) {
            for (int col = 1; col < M; col++) {
                //왼1 오2
                Rect rect1 = new Rect(1, 1, N, col);
                Rect rect2 = new Rect(row + 1, col + 1, N, M);
                Rect rect3 = new Rect(1, col + 1, row, M);

                max = Math.max(max, getPreSum(rect1) * getPreSum(rect2) * getPreSum(rect3));

                //왼2 오1
                 rect1 = new Rect(1, 1, row, col);
                 rect2 = new Rect(row + 1, 1, N, col);
                 rect3 = new Rect(1, col + 1, N, M);

                max = Math.max(max, getPreSum(rect1) * getPreSum(rect2) * getPreSum(rect3));

                //위1 아2
                 rect1 = new Rect(1, 1, row, M);
                 rect2 = new Rect(row + 1, 1, N, col);
                 rect3 = new Rect(row + 1, col + 1, N, M);

                max = Math.max(max, getPreSum(rect1) * getPreSum(rect2) * getPreSum(rect3));

                //위2 아1
                 rect1 = new Rect(1, 1, row, col);
                 rect2 = new Rect(1, col + 1, row, M);
                 rect3 = new Rect(row + 1, 1, N, M);

                max = Math.max(max, getPreSum(rect1) * getPreSum(rect2) * getPreSum(rect3));
            }
        }

        System.out.println(max);
    }

    public static long getPreSum(Rect rect){
        return preSum[rect.rightBottomRow][rect.rightBottomCol]
                - preSum[rect.rightBottomRow][rect.leftTopCol-1]
                - preSum[rect.leftTopRow-1][rect.rightBottomCol]
                + preSum[rect.leftTopRow -1][rect.leftTopCol-1];
    }
}
class Rect{
    int leftTopRow;
    int leftTopCol;
    int rightBottomRow;
    int rightBottomCol;

    public Rect(int leftTopRow,int leftTopCol,int rightBottomRow,int rightBottomCol){
        this.leftTopRow = leftTopRow;
        this.leftTopCol = leftTopCol;
        this.rightBottomRow = rightBottomRow;
        this.rightBottomCol = rightBottomCol;
    }
}