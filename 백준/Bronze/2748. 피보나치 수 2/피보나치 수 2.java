
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static boolean visited[];
	static long fibonacci[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());

		visited = new boolean[n + 1];
		fibonacci = new long[n + 1];
		long result = dp(n);
		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

	public static long dp(int num) {
		if (visited[num]) {
			return fibonacci[num];
		} else {
			visited[num] = true;
			if (num == 0) {
				return 0;
			}
			if (num == 1 || num == 2) {
				fibonacci[num] = 1;
				return 1;
			}
			fibonacci[num] = dp(num - 1) + dp(num - 2);
			return fibonacci[num];
		}
	}
}
