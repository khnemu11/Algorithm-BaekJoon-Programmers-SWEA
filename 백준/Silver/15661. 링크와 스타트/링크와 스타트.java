import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
	
*/

public class Main {
	static int min = Integer.MAX_VALUE;
	static int status[][];
	static int N;
	static int totalSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(br.readLine());

		status = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				status[i][j] = Integer.valueOf(st.nextToken());
				totalSum += status[i][j];
			}
		}
		boolean visited[] = new boolean[N + 1];

		select(1, visited, 0);

		System.out.println(min);
	}

	public static void select(int idx, boolean visited[], int cnt) {
		if (idx > N) {
			if (cnt == 0 || cnt == N) {
				return;
			}
			int startSum = 0;
			int linkSum = 0;
			for (int from = 1; from < visited.length; from++) {
				for (int to = from + 1; to < visited.length; to++) {
					if (visited[from] && visited[to]) {
						startSum += status[from][to];
						startSum += status[to][from];
					}
					if (!visited[from] && !visited[to]) {
						linkSum += status[from][to];
						linkSum += status[to][from];
					}
				}
			}

			min = Math.min(min, Math.abs(linkSum - startSum));

			return;
		} else {
			select(idx + 1, visited, cnt);

			visited[idx] = true;
			select(idx + 1, visited, cnt + 1);
			visited[idx] = false;
		}
	}
}