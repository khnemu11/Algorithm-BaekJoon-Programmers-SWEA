import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		int relation[][] = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					relation[i][j] = 0;
				} else {
					relation[i][j] = Integer.MAX_VALUE / 2; // 오버플로우로 인한 INF설정
				}
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.valueOf(st.nextToken()) - 1;
			int col = Integer.valueOf(st.nextToken()) - 1;
			relation[row][col] = 1;
			relation[col][row] = 1;
		}
		for (int n = 0; n < N; n++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					relation[i][j] = Math.min(relation[i][j], relation[i][n] + relation[n][j]);
				}
			}
		}

		int minUser = 0;
		int minSum = Arrays.stream(relation[0]).sum();
		for (int i = 1; i < N; i++) {
			int min = Arrays.stream(relation[i]).sum();

			if (minSum > min) {
				minUser = i;
				minSum = min;
			}
		}
		bw.write(String.valueOf(minUser + 1));
		bw.flush();
		br.close();
		bw.close();
	}
}
