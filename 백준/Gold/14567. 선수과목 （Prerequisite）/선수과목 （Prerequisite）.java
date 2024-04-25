import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] prerequisites = new int[N+1];
        List<Integer>graph[] = new ArrayList[N+1];

        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            prerequisites[to]++;
            graph[from].add(to);
        }

        PriorityQueue<Subject> pq = new PriorityQueue<>((a,b)->(a.time - b.time));

        int[] time = new int[N+1];

        for(int i=1;i<=N;i++){
            if(prerequisites[i] == 0){
                pq.add(new Subject(i,1));

            }
        }

        while(!pq.isEmpty()){
            Subject curr = pq.poll();

            if(prerequisites[curr.no] == 0){
                time[curr.no] = curr.time;
            }

            for(int next : graph[curr.no]){
                prerequisites[next]--;

                if(prerequisites[next] == 0){
                    pq.add(new Subject(next,curr.time+1));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=N;i++){
            sb.append(time[i]+" ");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}

class Subject {
    int no;
    int time;

    public Subject(int no,int time){
        this.no = no;
        this.time = time;
    }
}