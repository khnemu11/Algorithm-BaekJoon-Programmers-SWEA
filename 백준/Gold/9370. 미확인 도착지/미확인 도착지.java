import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			int t = Integer.valueOf(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int s = Integer.valueOf(st.nextToken());
			int g = Integer.valueOf(st.nextToken());
			int h = Integer.valueOf(st.nextToken());
			graph = new ArrayList<>();

			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVal = Integer.valueOf(st.nextToken());
				int toVal = Integer.valueOf(st.nextToken());
				int cost = Integer.valueOf(st.nextToken());
				Node from = new Node(fromVal, cost);
				Node to = new Node(toVal, cost);

				graph.get(fromVal).add(to);
				graph.get(toVal).add(from);
			}

			int sMinCost[] = dijkstra(s, n);
			int gMinCost[] = dijkstra(g, n);
			int hMinCost[] = dijkstra(h, n);

			ArrayList<Integer> goals = new ArrayList<>();

			while (t-- > 0) {
				int goal = Integer.valueOf(br.readLine());
				if (Math.min(sMinCost[g] + gMinCost[h] + hMinCost[goal],
						sMinCost[h] + hMinCost[g] + gMinCost[goal]) == sMinCost[goal]) {
					goals.add(goal);
				}
				// s -> g -> h -> goal
				// s -> h-> g-> goal
			}

			bw.write(goals.stream().sorted().map(x -> String.valueOf(x)).collect(Collectors.joining(" ")));
			bw.newLine();
		}
		bw.flush();
	}

	public static int[] dijkstra(int start, int n) {
		boolean visited[] = new boolean[n + 1];
		int minCost[] = new int[n + 1];
		int INF = 3000000;

		for (int i = 0; i < n + 1; i++) {
			minCost[i] = INF;
		}

		minCost[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node mid = pq.poll();
			visited[mid.val] = true;

			for (Node to : graph.get(mid.val)) {
				if (minCost[to.val] > minCost[mid.val] + to.cost) {
					pq.add(to);
					minCost[to.val] = minCost[mid.val] + to.cost;
				}
			}
		}

		return minCost;
	}
}

class Node implements Comparable<Node> {
	int val;
	int cost;

	public Node() {
	}

	public Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Node [val=" + val + ", cost=" + cost + "]";
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

}