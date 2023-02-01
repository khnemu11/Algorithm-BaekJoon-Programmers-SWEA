import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 * 풀이 알고리즘
	1) 입력 데이터 크기를 바탕으로 2차원 배열로 저장
	2) 플로이드 와샬로 각 출발 별 끝점까지의 최단거리 구하기
	3) 각 최단거리라면 역 추적을 위해 부모 인덱스 수정
*/

public class Main {
	static ArrayList<Integer> path;
	static int parents[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		int costs[][] = new int[N + 1][N + 1];
		parents = new int[N + 1][N + 1];
		int INF = 1000000000;

		for (int i = 0; i <= N; i++) {
			Arrays.fill(costs[i], INF);
			costs[i][i] = 0;
			Arrays.fill(parents[i], 0);
		}

		int M = Integer.valueOf(br.readLine());

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			if (costs[start][end] > cost) {
				parents[start][end] = start;
				costs[start][end] = cost;
			}
		}

		for (int mid = 1; mid <= N; mid++) {
			for (int start = 1; start <= N; start++) {
				for (int end = 1; end <= N; end++) {
					if (costs[start][end] > costs[start][mid] + costs[mid][end]) {
						parents[start][end] = mid;
						costs[start][end] = costs[start][mid] + costs[mid][end];
					}
				}
			}
		}

		for (int i = 1; i < costs.length; i++) {
			for (int j = 1; j < costs[0].length; j++) {
				if (costs[i][j] >= INF) {
					System.out.print("0 ");
				} else {
					System.out.print(costs[i][j] + " ");
				}
			}
			System.out.println();
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					System.out.println(0);
					continue;
				}

				path = new ArrayList<>();
				path.add(i);
				getPath(i, j);
				path.add(j);

				StringBuilder sb = new StringBuilder();
				sb.append(path.size() + " ");
				boolean isValid = true;
				for (int n : path) {
					if (n == 0) {
						isValid = false;
						break;
					}
					sb.append(n + " ");
				}
				if (isValid) {
					System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
				} else {
					System.out.println(0);
				}

			}
		}
	}

	public static void getPath(int start, int end) {
		if (parents[start][end] == start || parents[start][end] == end) {
			return;
		} else {
			int mid = parents[start][end];
			getPath(start, mid);
			path.add(mid);
			getPath(mid, end);
		}
	}
}
