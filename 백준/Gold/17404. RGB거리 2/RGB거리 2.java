import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * dp[시작 색][끝 색][순서]
 * 모든 집의 비용의 최소값 = min(마지막이 빨강색인 집의 최소값,마지막이 파랑색인 집의 최소값,마지막이 초록색인 집의 최소값)
 * 
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.valueOf(br.readLine());
		int minCosts[][][] = new int[3][3][n + 1]; // dp 최소 비용
		int firstCost[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		for (int i = 0; i < minCosts.length; i++) {
			for (int j = 0; j < minCosts[0].length; j++) {
				Arrays.fill(minCosts[i][j], 2_000_000);
			}
		}
		
		int seq =1;//순서 
		
		for(int color=0;color<3;color++) {
			minCosts[color][color][seq] = firstCost[color];
		}
		
		seq++; // 다음 집 번호로
		
		// 최소비용 계산
		for (; seq <= n; seq++) {
			int cost[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			
			for (int start = 0; start < minCosts.length; start++) {
				for (int end = 0; end < minCosts.length; end++) {
					minCosts[start][end][seq] =Math.min(minCosts[start][(end+1)%3][seq - 1], minCosts[start][(end+2)%3][seq - 1]) + cost[end];
				}
			}
		}
		
		int minCost = Integer.MAX_VALUE; //최소비용

		for (int start = 0; start < minCosts.length; start++) {
			for (int end = 0; end < minCosts.length; end++) {
				//시작 색, 끝 색 같으면 불가능한 색 조합이므로 패스
				if (start == end) {
					continue;
				}
				
				minCost = Math.min(minCosts[start][end][n], minCost);
			}
		}

		bw.write(minCost + "\n");
		bw.flush();
	}
}
