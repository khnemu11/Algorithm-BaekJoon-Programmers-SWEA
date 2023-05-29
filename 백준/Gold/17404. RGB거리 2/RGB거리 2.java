import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 
 * 모든 집의 비용의 최소값 = min(마지막이 빨강색인 집의 최소값,마지막이 파랑색인 집의 최소값,마지막이 초록색인 집의 최소값)
 * dp[빨강][n번째 집의 최소값] = min(dp[초록][n-1번째 집의 최소값],dp[파랑][n-1번째 집의 최소값]) + 빨강 
 * 이때 첫번째와 마지막 집의 색이 같으면 안되므로 첫번째를 제외하고 dp 배열 생성
 * 이후 첫번째 색과 마지막 집의 색을 고려해서 최종 최소 비용 계산
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int red = 0;
		int green = 1;
		int blue = 2;

		int n = Integer.valueOf(br.readLine());
		int minCosts[][][] = new int[3][3][n + 1]; // dp 최소 비용
		int firstCost[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		for (int i = 0; i < minCosts.length; i++) {
			for (int j = 0; j < minCosts[0].length; j++) {
				Arrays.fill(minCosts[i][j], 2_000_000);
			}
		}

		minCosts[red][red][1] = firstCost[red];
		minCosts[blue][blue][1] = firstCost[blue];
		minCosts[green][green][1] = firstCost[green];

		// 최소비용 계산

		for (int i = 2; i <= n; i++) {
			int cost[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			minCosts[red][blue][i] = Math.min(minCosts[red][green][i - 1], minCosts[red][red][i - 1]) + cost[blue];
			minCosts[red][green][i] = Math.min(minCosts[red][red][i - 1], minCosts[red][blue][i - 1]) + cost[green];
			minCosts[red][red][i] = Math.min(minCosts[red][green][i - 1], minCosts[red][blue][i - 1]) + cost[red];

			minCosts[blue][red][i] = Math.min(minCosts[blue][blue][i - 1], minCosts[blue][green][i - 1]) + cost[red];
			minCosts[blue][blue][i] = Math.min(minCosts[blue][green][i - 1], minCosts[blue][red][i - 1]) + cost[blue];
			minCosts[blue][green][i] = Math.min(minCosts[blue][red][i - 1], minCosts[blue][blue][i - 1]) + cost[green];

			minCosts[green][red][i] = Math.min(minCosts[green][blue][i - 1], minCosts[green][green][i - 1]) + cost[red];
			minCosts[green][blue][i] = Math.min(minCosts[green][green][i - 1], minCosts[green][red][i - 1])
					+ cost[blue];
			minCosts[green][green][i] = Math.min(minCosts[green][red][i - 1], minCosts[green][blue][i - 1])
					+ cost[green];
		}

		// n번째 까지의 빨강, 파랑, 초록의 최소 비용을 첫번째 집의 비용과 더해 계산
//		int minCost = Math.min(firstCost[blue],firstCost[green]) + minCosts[red][n-1];
//		minCost = Math.min(Math.min(firstCost[red],firstCost[green]) + minCosts[blue][n-1], minCost);
//		minCost = Math.min(Math.min(firstCost[red],firstCost[blue]) + minCosts[green][n-1], minCost);
//		
		int minCost = Integer.MAX_VALUE;

		for (int start = 0; start < minCosts.length; start++) {
			for (int end = 0; end < minCosts.length; end++) {
				if (start == end) {
					continue;
				}
				minCost = Math.min(minCosts[start][end][n], minCost);
			}
		}
//		System.out.println(Arrays.deepToString(minCosts));

		bw.write(minCost + "\n");
		bw.flush();
	}
}
