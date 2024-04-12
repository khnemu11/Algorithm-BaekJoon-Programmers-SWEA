import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][][]dp;
    static int[] card;
    static int[] sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int testcase=1;testcase<=T;testcase++){
            int N = Integer.parseInt(br.readLine());

            card = new int[N+1];
            dp = new int[2][N+1][N+1];
            sum = new int[N+1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i=1;i<=N;i++){
                card[i] = Integer.parseInt(st.nextToken());
                sum[i] = card[i] + sum[i-1];
            }
//            System.out.println(Arrays.toString(sum));
            int max = getMax(0,1,N);
//            System.out.println(Arrays.deepToString(dp));
            System.out.println(max);
        }
    }

    public static int getMax(int people, int start, int end){

        if (dp[people][start][end] > 0){
//            System.out.println(people+" 의 "+start+" ~ "+end);
//            System.out.println(dp[people][start][end]);
            return dp[people][start][end];
        }else if(start == end){
//            System.out.println(people+" 의 "+start+" ~ "+end);
            dp[people][start][end] = card[start];
//            System.out.println(dp[people][start][end]);
            return dp[people][start][end];
        }
        dp[people][start][end] = Math.max(sum[end] - sum[start] - getMax((people+1)%2,start+1,end) + card[start]
                ,sum[end-1] - sum[start-1]  - getMax((people+1)%2,start,end-1) + card[end]);
//        System.out.println(people+" 의 "+start+" ~ "+end);
//        System.out.println(dp[people][start][end]);
        return dp[people][start][end];
    }
}
/*
*
*
* 1 2 5 2
* 최대 점수[근우][1][4] = max(총합 - 최대 점수[명우][2][4] + num[1],총합 - 최대 점수[명우][1][3] + num[4]
*
*
* 최대 점수[근우][1][] = 최대 점수[근우][왼쪽을 고른 경우][카드 번호]
* 최대 점수[근우][왼쪽을 고른 경우][카드 번호] = 최대 점수[근우][왼쪽을 고른 경우][카드 번호]
*
* 근우의 1~N 최대값 = max(근우가 2~N 최대값, 근우가 1~N-1중 왼쪽을 골랐을때 최대값
*
* */