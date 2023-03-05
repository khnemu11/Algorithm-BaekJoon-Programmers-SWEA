import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 	문제 정의)
 * 	한 시작점부터 모든 노드간의 최소거리를 출력
 *  최소거리 -> 최단경로 알고리즘
 *  다익스트라 < 벨만포드 < 플로이드워셜
 * 
 * 	0) 문제 목표
 * 	한 시작 노드에서 자기 자신을 포함한 모든 노드간의 최소 거리 출력
 * 	
 *  1) 알고리즘 선택
	고정된 시작점과 점사이의 최단 거리 -> 다익스트라, 벨만포드
	가중치가 음수가 없다 -> 다익스트라
	
	2) 그래프 구현 방법 선택
	그래프 만드는 방법 : 배열 또는 리스트
	노드 개수가 최대 20,000이므로 최대 배열 크기는 20*10^3*20*10^3 = 400 * 10^6 = 400mb > 256mb 
	-> 메모리 초과
	
	메모리 효율을 올리기 위해 있는 간선만 저장할 필요가 있음 
	->리스트로 그래프 표현
	
	3) 시간 복잡도 계산
	우선순위 큐를 사용한 다익스트라 알고리즘의 시간 복잡도 
	모든 정점 별 간선의 길이 확인 = O(간선의 개수)
	비용이 작은 간선순서로 힙 정렬 = O((간선의 개수)*log(간선의 개수))
	= (간선의 개수) *log(간선의 개수)
	
	1) 노드와 노드간의 간선의 개수가 1개인 경우 = Elog(간선의 개수 = 노드의 개수) = Elog(V)
	2) 노드와 노드간의 간선의 개수가 1개이상인 경우 = Elog(E)
	  
	우선순위 큐를 사용한 다익스트라 알고리즘의 시간 복잡도 = Elog(E) = 300,000 * log(300,000) = 약 5,483,800 < 1억(1초)
	->시간 내에 가능
*/

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.valueOf(st.nextToken());
		int E = Integer.valueOf(st.nextToken());

		int start = Integer.valueOf(br.readLine());

		ArrayList<ArrayList<Node>> graph = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());

			graph.get(from).add(new Node(to, weight));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		int distance[] = new int[V + 1];
		int INF = V * 10;
		Arrays.fill(distance, INF);
		distance[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) { // 다익스트라가 시작되는 부분
			Node mid = pq.poll();

			if (mid.weight > distance[mid.val]) {
				continue;
			}

			for (Node to : graph.get(mid.val)) {
				if (distance[to.val] > distance[mid.val] + to.weight) {
					distance[to.val] = distance[mid.val] + to.weight;
					pq.add(new Node(to.val, distance[to.val]));
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			if (distance[i] >= INF) {
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
			}
		}

		bw.flush(); // 결과 출력
		bw.close();
	}
}

class Node implements Comparable<Node> {
	int val;
	int weight;

	public Node(int val, int weight) {
		super();
		this.val = val;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}

}
