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
		int size = Integer.valueOf(br.readLine());

		int num[] = new int[size];
		int dp[] = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());

		num[0] = Integer.valueOf(st.nextToken());
		dp[0] = num[0];
		int max = dp[0];

		for (int i = 1; i < size; i++) {
			num[i] = Integer.valueOf(st.nextToken());
			for (int j = i - 1; j >= 0; j--) {
				if (num[j] < num[i]) {
					dp[i] = Math.max(dp[j] + num[i], dp[i]);
				}
			}

			if (dp[i] == 0) {
				dp[i] = num[i];
			}

			max = Math.max(dp[i], max);
//			System.out.println("max : " + max);
//			System.out.println(Arrays.toString(dp));
		}
		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
