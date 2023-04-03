import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 풀이 과정
 * 
 * 모든 노드와 노드간의 최소거리 -> 플로이드 워셜
 * 
 * INF가 0인 것을 유의하여 min (시작->끝 , 시작->중간 + 중간->끝)을 진행할 때
 * 시작->중간, 중간->끝 중 하나가 0이면 INf이므로 연산 X
 * 시작->끝이 0이면 최대값으로 여겨야함
 * 
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		int graph[][] = new int[N + 1][N + 1];

		int E = Integer.valueOf(br.readLine());

		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			if (graph[from][to] == 0) { // 0이면 값이 없을 때이므로 따로 계산
				graph[from][to] = cost;
			} else { // 0이 아니면 최소 경로만 저장
				graph[from][to] = graph[from][to] > cost ? cost : graph[from][to];
			}

		}

		for (int mid = 1; mid < graph.length; mid++) {
			for (int start = 1; start < graph.length; start++) {
				for (int end = 1; end < graph.length; end++) {
					if (graph[start][mid] == 0 || graph[mid][end] == 0 || start == end) { // 중간지를 경유하는 것 중 하나라도
																							// INF(0)이거나 시작->시작으로 가는 점은
						continue;
					} else if (graph[start][end] == 0) { // 0이면 값이 없을 때이므로 따로 계산
						graph[start][end] = graph[start][mid] + graph[mid][end];
					} else { // 0이 아니면 최소 경로만 저장
						graph[start][end] = graph[start][mid] + graph[mid][end] < graph[start][end]
								? graph[start][mid] + graph[mid][end]
								: graph[start][end];
					}

				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(graph[i][j] + " ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}
}
