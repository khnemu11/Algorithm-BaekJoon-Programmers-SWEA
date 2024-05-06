import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer> graph[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];

        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }

        int[]parent = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i=1;i<parent.length;i++){
            graph[parent[i]].add(i);
        }

        int totalTime = getTotalCallTime(0);

        System.out.println(totalTime);
    }

    public static int getTotalCallTime(int curr){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int callTime = 0;

        if(graph[curr].isEmpty()){
            return 0;
        }

        for(int next : graph[curr]){
            pq.add(getTotalCallTime(next)+1);
        }

        int addTime = 0;

        while(!pq.isEmpty()){
            callTime = Math.max(callTime,pq.poll() + addTime);
            addTime++;
        }

        return callTime;
    }
}