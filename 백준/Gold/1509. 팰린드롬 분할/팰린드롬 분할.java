import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 
 * DP[끝 인덱스] = MAX(DP[끝인덱스], DP[시작~중간] +DP[중간+1~끝] )
 * */

public class Main {
	static boolean isPallindrome[][];
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();

		isPallindrome = new boolean[str.length()][str.length()];
		dp = new int[str.length()];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = i + 1;
		}

		for (int i = 0; i < str.length() - 1; i++) {
			isPallindrome[i][i] = true;
			if (str.charAt(i) == str.charAt(i + 1)) {
				isPallindrome[i][i + 1] = true;
			}
		}

		for (int length = 3; length <= str.length(); length++) {
			if (length % 2 == 0) {
				for (int start = 0; start <= str.length() - length; start++) {
					if (isPallindrome[start + 1][start + length - 2]
							&& str.charAt(start) == str.charAt(start + length - 1)) {
						isPallindrome[start][start + length - 1] = true;
					}
				}
			} else {
				for (int mid = length / 2; mid + length / 2 < str.length(); mid++) {
					if (isPallindrome[mid - length / 2 + 1][mid + length / 2 - 1]
							&& str.charAt(mid - length / 2) == str.charAt(mid + length / 2)) {
						isPallindrome[mid - length / 2][mid + length / 2] = true;
					}
				}
			}
		}

		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if (isPallindrome[j][i]) {
					if (j - 1 < 0) {
						dp[i] = Math.min(dp[i], 1);
					} else {
						dp[i] = Math.min(dp[i], dp[j - 1] + 1);
					}
				} else {
					if (j - 1 < 0) {
						dp[i] = Math.min(dp[i], i - j + 1);
					} else {
						dp[i] = Math.min(dp[i], dp[j - 1] + i - j + 1);
					}
				}
			}
		}

		bw.write(dp[dp.length - 1] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
