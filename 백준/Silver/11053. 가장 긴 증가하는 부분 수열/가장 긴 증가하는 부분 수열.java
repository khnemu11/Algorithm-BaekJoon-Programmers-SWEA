import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] amount;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		amount = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			amount[i] = Integer.valueOf(st.nextToken());
		}

		dp[0] = amount[0];
		int length = 1;
		for (int i = 1; i < N; i++) {
			if (dp[length-1] < amount[i]) {
				length++;
				dp[length-1] = amount[i];

			} else {
				for (int j = 0; j < length; j++) {
					if(dp[j] == amount[i]) {
						break;
					}
					else if (dp[j] > amount[i]) {
						dp[j] = amount[i];
						break;
					}
					
				}
			}
		}
		bw.write(String.valueOf(length));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
