import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] statues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(x -> x ==2 ? -1 : 1).toArray();

        int max = statues[0];
        int min = statues[0];
        int result = Math.max(Math.abs(max),Math.abs(min));

        for(int i=1;i<statues.length;i++){
            max = Math.max(max + statues[i], statues[i]);
            min = Math.min(min + statues[i], statues[i]);

            result = Math.max(result, max);
            result = Math.max(result, Math.abs(min));
        }

        System.out.println(result);
    }
}