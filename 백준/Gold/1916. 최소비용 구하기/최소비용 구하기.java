import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘

*	두 점간의 최소비용 -> 다익스트라
*/
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.valueOf(br.readLine());
		int E = Integer.valueOf(br.readLine());

		ArrayList<ArrayList<Node>> graph = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			graph.get(from).add(new Node(to, cost));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.valueOf(st.nextToken());
		int end = Integer.valueOf(st.nextToken());

		int distance[] = new int[V + 1];
		int INF = 100_000_000;
		Arrays.fill(distance, INF);
		distance[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node from = pq.poll();
			
			if (from.cost > distance[from.val]) {
				continue;
			}

			for (Node to : graph.get(from.val)) {
				if (distance[to.val] > distance[from.val] + to.cost) {
					distance[to.val] = distance[from.val] + to.cost;
					pq.add(new Node(to.val, distance[to.val]));
				}
			}
		}
		System.out.println(distance[end]);
	}
}

class Node implements Comparable<Node> {
	int val;
	int cost;

	public Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}