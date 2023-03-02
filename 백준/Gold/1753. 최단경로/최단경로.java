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
 * 	0) 문제 목표
 * 	한 시작 노드에서 자기 자신을 포함한 모든 노드간의 최소 거리 출력
 * 	
 *  1) 알고리즘 선택
	고정된 시작점과 점사이의 최단 거리 -> 다익스트라, 벨만포드
	가중치가 음수가 없다 -> 다익스트라
	
	2) 그래프 구현 방법 선택
	그래프 만드는 방법 : 배열 또는 리스트
	노드 개수가 최대 20,000이므로 최대 배열 크기는 20,000*20,000 = 400mb > 256mb 
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
	static int start;
	static int INF;
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		setGraph(); // 그래프 초기화
		int minCost[] = getMinCost(start); // 다익스트라를 이용해 시작노드부터 모든 노드간의 최단거리 배열 구하기

		for (int i = 1; i < minCost.length; i++) {
			if (minCost[i] >= INF) { // 현재 비용이 최대값 보다 같거나 큰경우 == 시작점에서 도달할 수 없는 노드
				bw.write("INF\n");
			} else {
				bw.write(minCost[i] + "\n");
			}
		}

		bw.flush(); // 결과 출력
		bw.close();
	}

	public static void setGraph() throws IOException { // 그래프 초기화 메소드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		graph = new ArrayList<>();

		int V = Integer.valueOf(st.nextToken()); // 정점의 개수
		int E = Integer.valueOf(st.nextToken()); // 간선의 개수
		INF = V * 10;// 최대값은 (V-1)*(비용의 최대값==10) == (V-1)*10 이므로 이보다 큰 값을 INF(도달할 수 없는 노드)으로 설정

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>()); // 정점의 개수 +1개 만큼 리스트 생성 (인덱스는 0부터 시작하므로)
		}

		start = Integer.valueOf(br.readLine()); // 시작점

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken()); // 출발 노드
			int to = Integer.valueOf(st.nextToken()); // 도착 노드
			int cost = Integer.valueOf(st.nextToken()); // 비용

			graph.get(from).add(new Node(to, cost));
		}
		br.close();
	}

	public static int[] getMinCost(int start) { // 시작점부터 모든 점까지의 최단거리배열을 구하는 메소드 (시작점)
		int distance[] = new int[graph.size()]; // 최단거리 배열
		Arrays.fill(distance, INF); // INF로 배열 초기화
		distance[start] = 0; // 시작점 -> 시작점의 비용은 0

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0)); // 시작점을 우선순위큐에 추가

		while (!pq.isEmpty()) { // 다익스트라 시작
			Node from = pq.poll();

			if (from.cost > distance[from.val]) { // (시작점 -> 현재 노드의 비용) > (시작점 -> 현재 노드까지의 최소비용) -> 이미 방문한 노드거나 최단거리가
													// 아닌 노드
				continue;
			}

			for (Node to : graph.get(from.val)) {
				// (시작점 -> 도착점의 최소비용) > (시작점 -> 중간점(from) + 중간점 ->도착점의 비용) -> 최소 비용 최신화
				if (distance[to.val] > distance[from.val] + to.cost) {
					distance[to.val] = distance[from.val] + to.cost;
					pq.add(new Node(to.val, distance[to.val])); // 최단거리를 구할 다음 노드를 추가
				}
			}
		}

		return distance;
	}
}

class Node implements Comparable<Node> {
	int val; // 노드 번호
	int cost; // 비용

	public Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}

	public int compareTo(Node o) { // 비용 별 오름차 순
		return this.cost - o.cost;

	}
}