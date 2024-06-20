import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Task> pq = new PriorityQueue<>((a,b)-> b.deadine - a.deadine);

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int deadline = Integer.parseInt(st.nextToken());

            pq.add(new Task(time, deadline));
        }


        Task last = pq.poll();
        int currentTime = last.deadine - last.time;

        while(!pq.isEmpty()){
            Task task = pq.poll();

            currentTime = Math.min(currentTime,task.deadine) - task.time;
        }

        currentTime = currentTime < 0 ? -1 : currentTime;

        System.out.println(currentTime);
    }
}
class Task{
    int time;
    int deadine;

    public Task(int time, int deadine){
        this.time = time;
        this.deadine = deadine;
    }
}