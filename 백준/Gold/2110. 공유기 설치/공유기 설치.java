import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int C = input[1];
		int N = input[0];
		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.valueOf(br.readLine());
		}
		Arrays.sort(arr);

		int min = 1;
		int max = arr[N - 1] - arr[0];
		int result = 0;

		while (min <= max) {
			int mid = (min + max) / 2;
			int cnt = 1;
			int start = 0;
			for (int i = 1; i < N; i++) {
				if (mid <= arr[i] - arr[start]) {
					start = i;
					cnt++;
				}
			}

			if (cnt < C) {
				max = mid - 1;
			} else {
				result = Math.max(result, mid);
				min = mid + 1;
			}
		}
		bw.write(String.valueOf(result) + "\n");
		bw.flush();
	}

}
