import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=  new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Node> graph[] = new ArrayList[n+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st=  new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end,weight));
            graph[end].add(new Node(start,weight));
        }

        st=  new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->(a.value - b.value));
        pq.add(new Node(start,0));
        int[]distance = new int[n+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start] = 0;
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();

            if(distance[curr.seq] < curr.value){
                continue;
            }

            for(Node next : graph[curr.seq]){
                if(distance[next.seq] > distance[curr.seq] + next.value){
                    distance[next.seq] = distance[curr.seq] + next.value;
                    pq.add(new Node(next.seq,distance[next.seq]));
                }
            }
        }

        System.out.println(distance[end]);
    }
}
class Node{
    int seq;
    int value;

    public Node(int seq, int value){
        this.seq=  seq;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "seq=" + seq +
                ", value=" + value +
                '}';
    }
}