import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int k = Integer.valueOf(br.readLine());

		int left = 1;
		int right = k;
		int result = 0;
		while (left <= right) {
			long count = 0;
			int mid = (left + right) / 2;

			for (int i = 1; i <= N; i++) {
				count += Math.min(N, mid / i);
			}
			if (count < k) {
				left = mid + 1;
			} else if (count >= k) {
				result = mid;
				right = mid - 1;
			}
		}

		System.out.println(result);
	}
}
