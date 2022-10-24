import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int size = Integer.valueOf(st.nextToken());
		int target = Integer.valueOf(st.nextToken());

		long dp[] = new long[target + 1];
		dp[0] = 1; //초기값 1을 더하기 위한 위치
		for (int i = 0; i < size; i++) {
			int coin = Integer.valueOf(br.readLine());
			for (int j = coin; j <= target; j++) {
				dp[j] = dp[j - coin] + dp[j];
			}
		}
		bw.write(String.valueOf(dp[target]));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}