import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map;


/*
*
* i+1 ~ j합 구하기
*
* sum[j] - sum[i] = K ( j > i )
*
*
* */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[]num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long count = 0;  //K인 부분합 개수
        int sum = 0; //누적합


        Map<Integer,Integer> subTotalCountmap = new HashMap<>();
        subTotalCountmap.put(0,1); // 처음부터 끝까지의 누적합인 경우의 sum[i]

        for(int i=0;i<num.length;i++){
            sum += num[i];
            count += subTotalCountmap.getOrDefault(sum - K,0);

            subTotalCountmap.put(sum,subTotalCountmap.getOrDefault(sum,0)+1);
        }

        System.out.println(count);
    }
}