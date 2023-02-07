import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
/*
	풀이 과정
	출발지 고정, 도착지까지 최소거리 -> 우선순위 큐를 이용한 다익스트라
	경로를 기억해야 하므로 각 최소거리가 갱신될 때마다 부모를 저장
*/

public class Main {
	static ArrayList<ArrayList<Node>> graph;
	static int distance[];
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int M = Integer.valueOf(br.readLine());

		graph = new ArrayList<>();
		distance = new int[N + 1];
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
			parents[i] = i;
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			graph.get(from).add(new Node(to, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		int start = Integer.valueOf(st.nextToken());
		int end = Integer.valueOf(st.nextToken());

		dajikstra(start);

		int route[] = getRoute(end);

		bw.write(distance[end] + "\n");
		bw.write(route.length + "\n");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < route.length; i++) {
			sb.append(route[i] + " ");
		}
		bw.write(sb.deleteCharAt(sb.length() - 1).toString() + "\n");

		bw.flush();
	}

	public static void dajikstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		Arrays.fill(distance, 100_000_000);
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Node from = pq.poll();

			if (from.cost > distance[from.val]) {
				continue;
			}

			for (Node to : graph.get(from.val)) {
				if (to.cost + distance[from.val] < distance[to.val]) {
					distance[to.val] = to.cost + distance[from.val];
					pq.add(new Node(to.val, distance[to.val]));
					parents[to.val] = from.val;
				}
			}
		}
	}

	public static int[] getRoute(int end) {
		Stack<Integer> stack = new Stack<>();
		int child = end;
		while (parents[child] != child) {
			stack.add(child);
			child = parents[child];
		}

		stack.add(child);

		int routes[] = new int[stack.size()];
		int idx = 0;
		while (!stack.isEmpty()) {
			routes[idx] = stack.pop();
			idx++;
		}

		return routes;
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
