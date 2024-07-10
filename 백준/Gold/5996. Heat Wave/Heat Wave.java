import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int Ts = Integer.parseInt(st.nextToken());
        int Te = Integer.parseInt(st.nextToken());

        List<Node> graph[] = new ArrayList[T+1];

        for(int i=0;i<=T;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<C;i++){
            st = new StringTokenizer(br.readLine());

            int R1 = Integer.parseInt(st.nextToken());
            int R2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[R1].add(new Node(R2,c));
            graph[R2].add(new Node(R1,c));
        }

        int[] shortestDistance = new int[T+1];
        Arrays.fill(shortestDistance,Integer.MAX_VALUE);
        shortestDistance[Ts] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> a.distance - b.distance);
        pq.add(new Node(Ts,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            if(curr.distance > shortestDistance[curr.num]){
                continue;
            }

            for(Node next : graph[curr.num]){
                if(shortestDistance[next.num] > shortestDistance[curr.num] + next.distance){
                    shortestDistance[next.num] = shortestDistance[curr.num] + next.distance;
                    pq.add(new Node(next.num,shortestDistance[next.num]));
                }
            }
        }

        System.out.println(shortestDistance[Te]);
    }
}

class Node {
    int num;
    int distance;

    public Node(int num, int distance){
        this.num = num;
        this.distance = distance;
    }
}