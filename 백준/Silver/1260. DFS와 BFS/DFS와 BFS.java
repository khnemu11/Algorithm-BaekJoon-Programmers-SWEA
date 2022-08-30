import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> graph;
	static Queue<Integer> bfsRoute;
	static Queue<Integer> dfsRoute;
	static boolean[] bfsvisited;
	static boolean[] dfsvisited;
	static boolean[] existed;

	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int vertices = Integer.valueOf(st.nextToken());
		int edges = Integer.valueOf(st.nextToken());
		int start = Integer.valueOf(st.nextToken());
		bfsvisited = new boolean[vertices + 1];
		dfsvisited = new boolean[vertices + 1];
		existed = new boolean[vertices + 1];
		graph = new ArrayList<>();
		dfsRoute = new LinkedList<>();
		bfsRoute = new LinkedList<>();
		for (int i = 0; i <= vertices; i++) {
			graph.add(new ArrayList<Integer>());
		}
		// 그래프 및 존재여부 값 지정
		for (int i = 0; i < edges; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
			existed[from] = true;
			existed[to] = true;
		}
		// 연결된 노드 오름차순 정렬
		for (int i = 1; i <= vertices; i++) {
			Collections.sort(graph.get(i));
		}
		// dfs 실행
		
		if(existed[start]) {
			dfs(start);
			System.out.println();
			bfs(start);
		}
		else {
			System.out.println(start);
			System.out.println(start);
		}

		// 출력 문구
//		StringBuilder sb = new StringBuilder();
//		while (!dfsRoute.isEmpty()) {
//			sb.append(dfsRoute.poll());
//			sb.append(" ");
//		}
//
//		sb.deleteCharAt(sb.length() - 1);
//		sb.append("\n");
//		System.out.println();
//		while (!bfsRoute.isEmpty()) {
//			sb.append(bfsRoute.poll());
//			sb.append(" ");
//		}
//		sb.deleteCharAt(sb.length() - 1);
//
//		System.out.println(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void dfs(int from) {
		dfsvisited[from] = true;
		System.out.print(from+" ");
		for (int i = 0; i < graph.get(from).size(); i++) {
			if (!dfsvisited[graph.get(from).get(i)]) {
				dfs(graph.get(from).get(i));
			}
		}
	}

	static void bfs(int from) {
		bfsvisited[from] = true;
		Queue<Integer> next = new LinkedList<>();
		next.add(from);
		
		while(!next.isEmpty()) {
			int nextNode = next.poll();
			System.out.print(nextNode+" ");
			for (int i = 0; i < graph.get(nextNode).size(); i++) {
				if (!bfsvisited[graph.get(nextNode).get(i)]) {
					bfsvisited[graph.get(nextNode).get(i)] = true;
					next.add(graph.get(nextNode).get(i));
				}
			}
		}
		
//		Queue<Integer> next = new LinkedList<>();
//		for (int i = 0; i < graph.get(from).size(); i++) {
//			if (!bfsvisited[graph.get(from).get(i)]) {
//				bfsvisited[graph.get(from).get(i)] = true;
//				bfsRoute.add(graph.get(from).get(i));
//				next.add(graph.get(from).get(i));
//			}
//		}
//		while (!next.isEmpty()) {
//			int nextNode = next.poll();
//			bfs(nextNode);
//		}
	}
}
