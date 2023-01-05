import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static long com[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int M = Integer.valueOf(br.readLine());
		com = new long[M + 1][M + 1];

		long result = combination(M - 1, M - N);

		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();
	}

	public static long combination(int n, int r) {
		if (com[n][r] > 0) {
			return com[n][r];
		} else if (r == 0 || n == r) {
			com[n][r] = 1;
			return 1;
		} else {
			com[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
			return com[n][r];
		}
	}
}
