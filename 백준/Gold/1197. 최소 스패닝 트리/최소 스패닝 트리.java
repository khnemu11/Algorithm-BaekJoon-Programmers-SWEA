import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	mst문제
	정점의 개수(10,000)가 간선(100,000)보다 작음 ->프림
*/
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.valueOf(st.nextToken());
		int E = Integer.valueOf(st.nextToken());

		boolean visited[] = new boolean[V + 1];
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}

		int start = 1; // 1 정점부터 시작
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start, 0));

		int cnt = 0;
		int cost = 0;

		while (!q.isEmpty()) {
			Node curr = q.poll();

			if (visited[curr.val]) {
				continue;
			}

			visited[curr.val] = true;
			cost += curr.cost;
			cnt++;

			if (cnt == V) {
				break;
			}

			for (Node to : graph.get(curr.val)) {
				if (visited[to.val]) {
					continue;
				}
				q.add(to);
			}
		}

		System.out.println(cost);
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