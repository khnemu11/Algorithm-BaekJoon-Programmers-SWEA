import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Work> pq = new PriorityQueue<>((a,b)->(b.deadline - a.deadline));

        for(int i=0;i<N;i++){
            StringTokenizer st= new StringTokenizer(br.readLine());

            pq.add(new Work(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        Work last = pq.poll();

        int time = last.deadline - last.time;

        while(!pq.isEmpty()){
            Work curr = pq.poll();

            time = Math.min(time,curr.deadline) - curr.time;
        }

        if(time < 0){
            System.out.println(-1);
        }else{
            System.out.println(time);
        }
    }
}
class Work {
    int time;
    int deadline;

    public Work(int time, int deadline) {
        this.time = time;
        this.deadline = deadline;
    }
}
