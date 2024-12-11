import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> graph[] = new ArrayList[N+1];

        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s].add(e);
            graph[e].add(s);
        }

        int min = Integer.MAX_VALUE;
        int chicken[] = new int[2];

        for(int i=1;i<=N;i++){
            for(int j=i+1;j<=N;j++){
                boolean[] visited = new boolean[N+1];
                int sum =0;

                Queue<Delivery> q = new LinkedList<>();
                q.add(new Delivery(i,0));
                q.add(new Delivery(j,0));

                while(!q.isEmpty()){
                    Delivery curr = q.poll();

                    if(visited[curr.location]){
                        continue;
                    }

                    sum+= curr.time*2;
                    visited[curr.location] = true;

                    for(int next : graph[curr.location]){
                        if(visited[next]){
                            continue;
                        }

                        q.add(new Delivery(next,curr.time+1));
                    }
                }

                if(min > sum){
                    min = sum;
                    chicken[0] = i;
                    chicken[1] = j;
                }
            }
        }

        System.out.println(chicken[0]+" "+chicken[1]+" "+min);
    }

}
class Delivery{
    int location;
    int time;

    public Delivery(int location, int time){
        this.location = location;
        this.time = time;
    }
}