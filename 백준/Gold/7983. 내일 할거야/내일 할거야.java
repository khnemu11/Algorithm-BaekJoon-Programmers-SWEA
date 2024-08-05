import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Task> pq = new PriorityQueue<>((a,b) ->(b.t - a.t));

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int today = pq.peek().t;

        while(!pq.isEmpty()){
            Task task = pq.poll();

            today = Math.min(task.t, today) - task.d;
        }

        System.out.println(today);
    }
}

class Task{
    int d;
    int t;

    public Task(int d, int t){
        this.d = d;
        this.t = t;
    }
}
