import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long[] preEven = new long[N/2];
        long[] preOdd = new long[N/2];

        preOdd[0] = cards[0];
        preEven[0] = cards[1];

        for(int i=2;i<N;i+=2){
            preOdd[i/2] = preOdd[i/2-1] + cards[i];
            preEven[i/2] = preEven[i/2-1] + cards[i+1];
        }

        //밑장빼기를 안한경우와 처음부터 밑장빼기를 해서 내가 먹은 경우중 큰 값으로 초기화
        long max = Math.max(preOdd[preOdd.length-1],preEven[preEven.length-1]);
        //밑장빼기를 해서 상대가 먹은 경우를 생각해서 초기화
        max = Math.max(max,cards[0] + preEven[preEven.length-1] - cards[cards.length-1]);

        for(int i=2;i<N;i+=2){
            max = Math.max(max,preOdd[i/2-1] + preEven[preEven.length-1] - preEven[i/2-1]); //밑장을 내가 먹은경우
            max = Math.max(max,preOdd[i/2] + preEven[preEven.length-1] - preEven[i/2-1] - cards[cards.length-1]); //밑장을 상대가 먹은경우
        }

        System.out.println(max);
    }
}