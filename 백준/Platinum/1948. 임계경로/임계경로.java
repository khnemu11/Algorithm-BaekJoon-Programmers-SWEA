package defalut;

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
		 출발-> 목표 그래프와 목표-> 출발 역그래프 생성
		 출발 도시부터 목표 도시까지 최장거리 연산
	     목표 -> 중간점 -> 출발까지의 거리가 최장거리인지 확인
 *       
 *       걸린시간 : 2시간
*/
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int parentsNum[];
		int distance[]; // 인덱스 노드까지 가는 최대 거리의 간선 개수
		ArrayList<ArrayList<Node>> graph = new ArrayList<>(); // 출발->목표까지의 그래프
		ArrayList<ArrayList<Node>> reverse = new ArrayList<>(); // 목표->출발까지의 그래프

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

		while (!pQueue.isEmpty()) { // 다익스트라를 이용해 최장거리 연산
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
		queue.add(new Node(end, 0)); // 도착지부터 출발

		int edgeNum = 0;
		while (!queue.isEmpty()) { // 도착지 -> 중간->출발까지의 거리가 최장거리인지 확인후 해당 간선 더하기
			Node curr = queue.poll(); //현재도시 ->도착지 까지의 거리가 담긴 노드
			
			for (Node to : reverse.get(curr.val)) {
				if (distance[to.val] + to.cost + curr.cost == distance[end] && !visited[curr.val][to.val]) {	//출발 -> 중간 -> 현재도시 -> 도착지까지의 거리가 최장거리이며 이미 탐색한 간선인지 확인
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
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

}
