import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] scores = new int[10];
        int sum = 0;
        int result = 0;

        for(int i=0;i<scores.length;i++){
            int score = Integer.parseInt(br.readLine());
            sum += score;

            if(Math.abs(100 - result) >= Math.abs(100 - sum)){
                result = Math.max(result , sum);
            }
        }

        System.out.println(result);
    }
}