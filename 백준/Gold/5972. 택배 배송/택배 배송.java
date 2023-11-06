import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
*
* 걸린시간 : 10분
* */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);

		List<List<Node>> graph = new ArrayList<>();

		for(int i=0;i<=N;i++){
			graph.add(new ArrayList<>());
		}

		for(int i=0;i<M;i++){
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);

			graph.get(to).add(new Node(from,cost));
			graph.get(from).add(new Node(to,cost));
		}


		int[] distance = new int[N+1];
		Arrays.fill(distance,Integer.MAX_VALUE);
		distance[1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0));

		while(!pq.isEmpty()) {
			Node curr = pq.poll();

			if (distance[curr.seq] < curr.cost) {
				continue;
			}

			for (Node next : graph.get(curr.seq)) {
				if (distance[next.seq] > distance[curr.seq] + next.cost) {
					distance[next.seq] = distance[curr.seq] + next.cost;
					pq.add(new Node(next.seq, distance[next.seq]));
				}
			}
		}

		System.out.println(distance[N]);
	}
}

class Node implements Comparable<Node>{
	int seq;
	int cost;

	public Node(int seq, int cost){
		this.seq=  seq;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}