import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testcase =0 ;testcase<T;testcase++){
            int N = Integer.parseInt(br.readLine());
            long[] stocks = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

            long max = 0;
            long sell = 0;
            long buy = 0;
            long count = 0;

            for(int i = stocks.length-1;i>=0;i--){
                if(max < stocks[i]){
                    sell += max*count - buy;
                    buy = 0;
                    count = 0;
                    max = stocks[i];
                }else{
                    buy+=stocks[i];
                    count++;
                }
            }
            if(buy > 0){
                sell +=max*count - buy;
            }
            System.out.println(sell);
        }
    }
}