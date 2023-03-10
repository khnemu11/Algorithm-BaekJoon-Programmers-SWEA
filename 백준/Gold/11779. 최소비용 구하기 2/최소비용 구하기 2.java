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
	풀이 알고리즘

	두 점과의 최소거리 -> 다익스트라
	경로 역추적 -> 부모의 좌표를 각 노드의 최단경로가 갱신 될 때 마다 저장함
 	
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int E = Integer.valueOf(br.readLine());

		int parents[] = new int[N + 1];
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			parents[i] = i;
		}

		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			graph.get(from).add(new Node(to, cost));
		}

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

		int start = Integer.valueOf(stringTokenizer.nextToken());
		int end = Integer.valueOf(stringTokenizer.nextToken());

		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		int distance[] = new int[N + 1];
		Arrays.fill(distance, 100_000_001);
		distance[start] = 0;
		pQueue.add(new Node(start, distance[start]));

		while (!pQueue.isEmpty()) { // 다익스트라를 이용한 최단경로 탐색
			Node midNode = pQueue.poll();

			if (distance[midNode.val] < midNode.cost) {
				continue;
			}

			for (Node to : graph.get(midNode.val)) {
				if (distance[to.val] > distance[midNode.val] + to.cost) {
					parents[to.val] = midNode.val; // 자식의 부모를 현재 경유점으로 설정
					distance[to.val] = distance[midNode.val] + to.cost;

					pQueue.add(new Node(to.val, distance[to.val]));
				}
			}
		}

		int curr = end;
		int length = 1;
		Stack<Integer> stack = new Stack<>();

		while (parents[curr] != curr) { // 경로 역추적
			stack.add(curr);
			curr = parents[curr];
			length++;
		}

		StringBuilder sBuilder = new StringBuilder(start + " ");

		while (!stack.isEmpty()) {
			sBuilder.append(stack.pop() + " ");
		}

		bw.write(distance[end] + "\n");
		bw.write(length + "\n");
		bw.write(sBuilder.toString() + "\n");

		bw.flush();
	}

}

class Node implements Comparable<Node> {
	int val;
	int cost;

	public Node(int val, int cost) {
		super();
		this.val = val;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

}