import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
	풀이 알고리즘
	단순 완전탐색으로 탐색시 시간 복잡도가 n!으로 최대 16!으론 연산 불가
	dp+바트 마스킹을 활용한 tsp알고리즘 필요
	dp[출발하는 도시][방문한 도시]을 의미하며 방문한 도시는 이진법으로 표현
	방문처리는 |(1<<방문 도시 번호), & (1<<방문 처리 번호)로 처리
*/

public class Main {
	static int N;
	static int map[][];
	static int dp[][];
	static int min = Integer.MAX_VALUE;
	static int INF = 17 * 1_000_000;
	static int start = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.valueOf(br.readLine());
		map = new int[N][N];
		dp = new int[N][(1 << N) - 1];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			Arrays.fill(dp[i], INF * 2);
		}

		int min = getMinRoute(start, 1);
		bw.write(min + "\n");
		bw.flush();
	}

	public static int getMinRoute(int from, int visited) {
		if (visited == (1 << N) - 1) {
			if (map[from][start] == 0) {
				return INF;
			}
			return map[from][start];
		}
		if (dp[from][visited] != INF * 2) {
			return dp[from][visited];
		} else {
			for (int to = 0; to < N; to++) {
				if ((visited & (1 << to)) == 0 && map[from][to] != 0) {
					dp[from][visited] = Math.min(dp[from][visited],
							getMinRoute(to, visited | (1 << to)) + map[from][to]);
				}
			}
			return dp[from][visited];
		}
	}
}
