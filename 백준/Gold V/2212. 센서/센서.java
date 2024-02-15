import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//구역(연속된 센서의 위치)의 길이의 합이 최소인 것을 탐색
//-> 구역의 사이 길이가 가장 긴 것을 제외

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(sensors);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=1;i<sensors.length;i++){
            pq.add(sensors[i] - sensors[i-1]);
        }

        int distance = 0;
        int count = N - K; //연결해야되는 센서의 개수

        while(count-->0){
            distance+=pq.poll();
        }

        System.out.println(distance);
    }
}