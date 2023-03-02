import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
	두 점과의 최소거리 -> 다익스트라
	임의의 두점 a,b를 무조건 지나야함
	경로는 2개가 나타남
	
	시작 -> a -> b ->도착
	시작 -> b -> a ->도착
	
	구해야할 다익스트라를 적용할 최단거리 연산
	
	시작 -> a
	시작 -> b
	b -> a
	a -> b 
	a -> 도착
	b -> 도착
*/
public class Main {
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 노드의 개수
		int E = Integer.valueOf(st.nextToken()); // 간선의 개수
		int start = 1;
		int end = N;
		int INF = 200_000_000;

		for (int i = 0; i <= N; i++) {
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

		st = new StringTokenizer(br.readLine());
		int a = Integer.valueOf(st.nextToken());
		int b = Integer.valueOf(st.nextToken());

		int startToA = getMinCost(start, a);
		int startToB = getMinCost(start, b);
		int AToB = getMinCost(a, b);
		int BToA = getMinCost(b, a);
		int AToEnd = getMinCost(a, end);
		int BToEnd = getMinCost(b, end);

		int minCost = Math.min(startToA + AToB + BToEnd, startToB + BToA + AToEnd);

		if (minCost >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(minCost);
		}
	}

	public static int getMinCost(int start, int end) {
		int distance[] = new int[graph.size()];
		int INF = 200_000_000;

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

		return distance[end];
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