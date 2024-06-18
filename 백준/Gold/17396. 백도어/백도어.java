import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] view = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Node>[]graph = new ArrayList[N];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end,distance));
            graph[end].add(new Node(start,distance));
        }

        long[] minDistance = new long[N];

        Arrays.fill(minDistance,Long.MAX_VALUE);
        minDistance[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->Long.compare(a.distance,b.distance));
        pq.add(new Node(0,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            if(minDistance[curr.seq] < curr.distance){
                continue;
            }

            for(Node next : graph[curr.seq]){
                if(next.seq !=N-1 && view[next.seq] == 1){
                    continue;
                }
                if(minDistance[next.seq] > minDistance[curr.seq] + next.distance){
                    minDistance[next.seq] = minDistance[curr.seq] + next.distance;
                    pq.add(new Node(next.seq,minDistance[next.seq]));
                }
            }
        }

        minDistance[N-1] = minDistance[N-1] == Long.MAX_VALUE ? -1 : minDistance[N-1];

        System.out.println(minDistance[N-1]);
    }
}
class Node{
    int seq;
    long distance;

    public Node(int seq, long distance){
        this.seq = seq;
        this.distance = distance;
    }
}
