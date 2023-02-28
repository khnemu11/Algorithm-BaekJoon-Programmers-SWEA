import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	MST
	프림알고리즘 사용
	
	걸린시간 : 11분
*/
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
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

			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(1, 0));
			int visitedCnt = 0;
			long sum = 0;

			while (!pq.isEmpty()) {
				Node curr = pq.poll();

				if (visited[curr.val]) {
					continue;
				}

				sum += curr.cost;
				visited[curr.val] = true;
				visitedCnt++;

				if (visitedCnt == V) {
					break;
				}

				for (Node to : graph.get(curr.val)) {
					if (!visited[to.val]) {
						pq.add(to);
					}
				}
			}

			System.out.println("#" + testcase + " " + sum);
		}
	}

}

class Node implements Comparable<Node> {
	int val;
	int cost;

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

	public Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}
}