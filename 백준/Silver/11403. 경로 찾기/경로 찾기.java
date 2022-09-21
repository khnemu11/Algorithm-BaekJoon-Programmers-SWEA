import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int relation[][] = new int[N][N];
		int INF = 10000;
		visited = new int[101][101];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.valueOf(st.nextToken());
				if (val == 0) {
					relation[i][j] = INF;
				} else {
					relation[i][j] = val;
				}

			}
		}
		for (int n = 0; n < N; n++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					relation[i][j] = Math.min(relation[i][j], relation[i][n] + relation[n][j]);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < N; j++) {
				if (relation[i][j] < INF) {
					sb.append(1);
				}
				else {
					sb.append(0);
				}
				sb.append(" ");
			}
			bw.write(sb.deleteCharAt(sb.length()-1).toString());
			bw.newLine();
		}

		bw.flush();
		br.close();
		bw.close();
	}
}
