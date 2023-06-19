import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Node> graph[];
	static boolean visited[];
	static int minCost = Integer.MAX_VALUE;
	static int END;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 교차로 개수
		int M = Integer.parseInt(st.nextToken()); // 골목 개수
		int A = Integer.parseInt(st.nextToken()); // 시작 번호
		int B = Integer.parseInt(st.nextToken()); // 끝 번호
		int C = Integer.parseInt(st.nextToken()); // 소지한 돈

		END = B;
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, cost));
			graph[to].add(new Node(from, cost));
		}
		//그래프 탐색
		goNextNode(A, 0, C);
		
		//초기값과 동일하면 도착을 못했단 의미이므로 -1 넣기
		minCost = minCost == Integer.MAX_VALUE ? -1 : minCost;
		
		bw.write(minCost + "\n");
		bw.flush();
	}

	public static void goNextNode(int curr, int maxCost, int money) {
		// 더이상 돈이 없는 경우 탐색 중단
		if (money < 0) {
			return;
		}
		// 목적지에 도달한 경우 골목길 중 최대값을 이용해 최소값을 갱신하고 탐색 중단
		if (curr == END) {
			minCost = Math.min(minCost, maxCost);
			return;
		}
		// 방문처리
		visited[curr] = true;
		// 목적지가 아니라면 다음 노드로 이동
		for (Node next : graph[curr]) {
			if (visited[next.no]) {
				continue;
			}
			visited[next.no] = true;
			goNextNode(next.no, Math.max(maxCost, next.cost), money - next.cost);
			visited[next.no] = false;
		}
	}
}

class Node {
	int no;
	int cost;

	public Node(int no, int cost) {
		this.no = no;
		this.cost = cost;
	}
}