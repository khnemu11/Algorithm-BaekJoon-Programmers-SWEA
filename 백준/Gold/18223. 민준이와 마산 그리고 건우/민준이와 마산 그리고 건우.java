import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[][] minDistance = new int[2][V+1];
        List<Node>graph[] = new ArrayList[V+1];

        for(int i=0;i<=V;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,distance));
            graph[b].add(new Node(a,distance));
        }

        Arrays.fill(minDistance[0],Integer.MAX_VALUE);
        minDistance[0][1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> a.distance - b.distance);
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            if(curr.distance > minDistance[0][curr.val]){
                continue;
            }

            for(Node next : graph[curr.val]){
                if(next.distance + curr.distance < minDistance[0][next.val]){
                    minDistance[0][next.val] = next.distance + curr.distance;
                    pq.add(new Node(next.val,minDistance[0][next.val]));
                }
            }
        }

        Arrays.fill(minDistance[1],Integer.MAX_VALUE);
        minDistance[1][P] = 0;

        pq = new PriorityQueue<>((a,b)-> a.distance - b.distance);
        pq.add(new Node(P,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            if(curr.distance > minDistance[1][curr.val]){
                continue;
            }

            for(Node next : graph[curr.val]){
                if(next.distance + curr.distance < minDistance[1][next.val]){
                    minDistance[1][next.val] = next.distance + curr.distance;
                    pq.add(new Node(next.val,minDistance[1][next.val]));
                }
            }
        }

        if(minDistance[0][P] + minDistance[1][V] == minDistance[0][V]){
            System.out.println("SAVE HIM");
        }else{
            System.out.println("GOOD BYE");
        }
    }
}
class Node{
    int val;
    int distance;

    public Node(int val, int distance){
        this.val = val;
        this.distance = distance;
    }
}