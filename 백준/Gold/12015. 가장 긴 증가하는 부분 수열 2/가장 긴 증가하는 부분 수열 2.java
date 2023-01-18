import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		int num[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int dp[] = new int[size];

		int length = 1;

		dp[0] = num[0];

		for (int i = 1; i < num.length; i++) {
			if (dp[length - 1] < num[i]) {
				length++;
				dp[length - 1] = num[i];
			} else {
				int mid = 0;
				int l = 0;
				int r = length - 1;
				while (l < r) {
					mid = (l + r) / 2;

					if (dp[mid] < num[i]) {
						l = mid + 1;
					} else {
						r = mid;
					}
				}

				dp[l] = num[i];
			}
		}
		System.out.println(length);
	}
}
