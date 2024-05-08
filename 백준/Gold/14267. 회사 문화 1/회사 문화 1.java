import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> graph[] = new ArrayList[n+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        int[] relation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i=1;i<relation.length;i++){
            graph[relation[i]].add(i+1);
        }

        int[] praise = new int[n+1];

        for(int k=0;k<m;k++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            praise[i]+=w;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while(!q.isEmpty()){
            int curr = q.poll();

            for(int next : graph[curr]){
                praise[next] += praise[curr];
                q.add(next);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1;i<praise.length;i++){
            sb.append(praise[i]+" ");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
    }
}