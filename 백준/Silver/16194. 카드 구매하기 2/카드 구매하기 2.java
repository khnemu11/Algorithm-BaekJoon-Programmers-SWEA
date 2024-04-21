import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] cards = new int[N+1];
        int[][] minPrice = new int[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1;i<=N;i++){
            minPrice[1][i] = minPrice[1][i-1] + cards[1];
        }

        for(int i=2;i<=N;i++){
            for(int j=1;j<i;j++){
                minPrice[i][j] = minPrice[i-1][j];
            }
            for(int j=i;j<=N;j++){
                minPrice[i][j] = Math.min(Math.min(minPrice[i][j-i],minPrice[i-1][j-i]) + cards[i], minPrice[i-1][j]);
            }
        }
        System.out.println(minPrice[N][N]);
    }
}
