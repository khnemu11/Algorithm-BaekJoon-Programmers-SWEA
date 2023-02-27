import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	최소 경로가 아니므로 경로가 연결 되어 있기만 하면 됨
	경로 연결 -> 유니온-파인드
	중량이 가장 많이 실을 수 있는 경로를 선택해야함-> 중량제한이 큰 노드를 선택
	그래프로 표현하기엔 노드의 수가 너무 많음(10만개)
	매 줄마다 판단할 필요가 있음
*/
public class Main {
	static int parents[];
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 섬 개수
		int M = Integer.valueOf(st.nextToken()); // 다리 수

		parents = new int[N + 1];
		boolean visited[] = new boolean[N + 1];
		graph = new ArrayList<>();
		for (int i = 0; i < parents.length; i++) { // 부모 노드 초기화
			parents[i] = i;
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromIdx = Integer.valueOf(st.nextToken());
			int toIdx = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());

			graph.get(fromIdx).add(new Node(toIdx, weight));
			graph.get(toIdx).add(new Node(fromIdx, weight));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());

		int start = Integer.valueOf(st.nextToken());
		int end = Integer.valueOf(st.nextToken());

		for (Node to : graph.get(start)) {
			pq.add(to);
		}
		int min = Integer.MAX_VALUE;
		visited[start] = true;

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			min = Math.min(curr.weight, min);

			if (curr.val == end) {
				break;
			}
			visited[curr.val] = true;

			for (Node to : graph.get(curr.val)) {
				if (visited[to.val]) {
					continue;
				}

				pq.add(to);
			}
		}
		System.out.println(min);
	}
}

class Node implements Comparable<Node> {
	int val;
	int weight;

	public Node(int val, int weight) {
		this.val = val;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Node [val=" + val + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Node o) {
		return o.weight - this.weight;
	}
}