import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/* 
 * 	풀이 알고리즘
 * 
 * 	외판원 순회이므로 tsp알고리즘(dp+비트마스크)을 사용
 * 	현재까지 방문한 도시를 비트마스크로 표현
 *  현재까지 방문하지 않는 도시의 비용합을 이용해 dp로 표현하여 최소값 구하기
 *  (현재 위치에서 남은 모든 도시로 가는 비용의 최소값) = min(현재 위치에서 남은 모든 도시로 가는 비용의 최소값, 현재 도시와 연결된 도시 하나의 비용 +연결된 도시 하나를 제외한 남은 모든 도시로 가는 비용의 최소값)
 * */

public class Main {
	static int map[][];
	static int NOT_CONNECTED = 2_000_000;
	static int NOT_VISITED = 4_000_000;
	static int ALL_VISITED = 0;
	static int dp[][]; // dp[현재 위치][방문한 도시들] = 현재 위치에서 남은 방문한 도시들의 비용 합의 최소값
	static int start = 0;
	static int N = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.valueOf(br.readLine());
		ALL_VISITED = (1 << N) - 1;
		dp = new int[N][ALL_VISITED];
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			Arrays.fill(dp[i], NOT_VISITED);
		}

		bw.write(getMinRoute(0, 1) + "\n");
		bw.flush();
	}

	public static int getMinRoute(int from, int visited) { // from에서 출발하여 아직 방문하지 않은 도시들의 비용 합 중 최속값을 반환하는 메소드
		if (visited == ALL_VISITED) { // 모든 도시를 탐색했다면 start로 돌아올 수 있는지 판단한다.
			if (map[from][start] == 0) { // start로 돌아올 수 없다면 갈 수 없는 경로
				return NOT_CONNECTED;
			} else {
				return map[from][start];
			}
		} else if (dp[from][visited] != NOT_VISITED) { // 이미 dp로 최소값을 아는 경로합이면 그대로 리턴
			return dp[from][visited];
		}
		for (int to = 0; to < N; to++) { // from과 연결되어 있고 아직 방문하지 않는 도시를 찾아
											// from->to까지의 비용 + to까지 방문했을 때 방문하지 않는 나머지의 비용합을 구하여 기존 값과 비교
			if (map[from][to] != 0 && (visited & (1 << to)) == 0) { // from과 to가 연결되어있고 to를 아직 방문하지 않았다면
				dp[from][visited] = Math.min(dp[from][visited], getMinRoute(to, visited | (1 << to)) + map[from][to]);
			}
		}

		return dp[from][visited];
	}
}
