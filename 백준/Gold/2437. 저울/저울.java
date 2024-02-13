import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set<Integer> visited = new HashSet<>();

        Arrays.sort(weights);

        int min = 1;
        int sum = 0;
        for(int weight : weights){
            //남은 가장 작은 추가 현재 측정 불가 무게보다 크면 탐색 불필요
            if(min < weight){
                break;
            }

            sum = sum + weight;
            min = sum + 1;
        }

        System.out.println(min);
    }
}