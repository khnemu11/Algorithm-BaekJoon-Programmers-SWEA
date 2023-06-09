import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String a = br.readLine();
		String b = br.readLine();

		String small = a.length() > b.length() ? b : a;
		String big = a.length() > b.length() ? a : b;

		int dp[][] = new int[small.length() + 1][big.length() + 1];
		int max = 0;
		for (int i = 0; i < small.length(); i++) {
			for (int j = 0; j < big.length(); j++) {
				if (small.charAt(i) == big.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
					max = Math.max(max, dp[i + 1][j + 1]);
				}
			}
		}

		bw.write(max + "\n");
		bw.close();
	}
}