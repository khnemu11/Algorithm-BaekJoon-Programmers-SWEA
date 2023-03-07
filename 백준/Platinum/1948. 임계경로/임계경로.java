import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 *	0) 문제 목표
 *		출발도시부터 목표도시까지의 최장거리와 최장거리가 되는 도로의 수를 출력
 *
 *  1) 문제 알고리즘
 *   	출발도시부터 목표도시까지의 최장거리 -> 다익스트라 응용
 *      최장거리가 되는 도로의 수
 *       if (출발~도착 < 출발~중간 + 중간~도착){       		중간 도시를 경유하는 것이 더 오래걸리는 경우
 *       	출발~도착까지 간선의 수 = 출발~중간간선의 수 +1
 *       }
 *       else if (출발~도착 == 출발~중간 + 중간~도착){		중간 도시를 경유하는 것과 아닌 것의 시간이 같은 경우
 *       	출발~도착까지 간선의 수 = 출발~중간간선의 수 +1 + 출발~도착의 간선의 수
 *       }											중간 도시를 경유하는 것이 더 짧게걸리는 경우 최신화하지 않음
 *       											
 *       각 중간도시를 다시 방문하지 않아야 해당 알고리즘이 가능
 *       -> 해당 도시로 들어오는 간선이 더이상 없다.
 *       -> 위상정렬 이용
 *       -> 위상정렬을 이용해 중간도시를 다시 방문하지 않는 다익스트라 알고리즘 이용
 *       
 *       걸린시간 : 52분
*/
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int parentsNum[];
		int distance[]; // 인덱스 노드까지 가는 최대 거리의 간선 개수
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		ArrayList<ArrayList<Node>> reverse = new ArrayList<>();

		int N = Integer.valueOf(br.readLine());
		int M = Integer.valueOf(br.readLine());

		parentsNum = new int[N + 1];
		distance = new int[N + 1];
		boolean visited[][] = new boolean[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			reverse.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(stringTokenizer.nextToken());
			int to = Integer.valueOf(stringTokenizer.nextToken());
			int cost = Integer.valueOf(stringTokenizer.nextToken());
			graph.get(from).add(new Node(to, cost));
			reverse.get(to).add(new Node(from, cost));

			parentsNum[from]++;
		}

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int start = Integer.valueOf(stringTokenizer.nextToken());
		int end = Integer.valueOf(stringTokenizer.nextToken());

		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		pQueue.add(new Node(start, 0));

		while (!pQueue.isEmpty()) {
			Node curr = pQueue.poll();

			if (distance[curr.val] > curr.cost) {
				continue;
			}

			for (Node child : graph.get(curr.val)) {
				if (distance[curr.val] + child.cost > distance[child.val]) {
					distance[child.val] = distance[curr.val] + child.cost;
					pQueue.add(new Node(child.val, distance[child.val]));
				}
			}
		}

		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(end, 0));

		int edgeNum = 0;
		while (!queue.isEmpty()) {
			Node curr = queue.poll();
//			System.out.println(curr);
			for (Node to : reverse.get(curr.val)) {
//				System.out.println(to.val + " : " + (distance[to.val] + to.cost + curr.cost) + " vs " + distance[end]);
				if (distance[to.val] + to.cost + curr.cost == distance[end] && !visited[curr.val][to.val]) {
					visited[curr.val][to.val] = true;
					edgeNum++;
					queue.add(new Node(to.val, distance[end] - distance[to.val]));
				}
			}
		}

		bw.write(distance[end] + "\n");
		bw.write(edgeNum + "\n");
		bw.flush(); // 결과 출력
		br.close();
		bw.close();
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
	public String toString() {
		return "Node [val=" + val + ", cost=" + cost + "]";
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

}