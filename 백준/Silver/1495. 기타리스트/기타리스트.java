import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int S = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		int volumns[] = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < volumns.length; i++) {
			volumns[i] = Integer.valueOf(st.nextToken());
		}

		boolean dp[][] = new boolean[N + 1][M + 1];

		dp[0][S] = true;

		for (int volumnIdx = 1; volumnIdx < volumns.length; volumnIdx++) {
			int volumn = volumns[volumnIdx];
			for (int i = 0; i < dp[0].length; i++) {
				if (i - volumn >= 0 && dp[volumnIdx - 1][i]) {
					dp[volumnIdx][i - volumn] = true;
				}
				if (i + volumn <= M && dp[volumnIdx - 1][i]) {
					dp[volumnIdx][i + volumn] = true;
				}
			}
		}

		int max = -1;

		for (int i = M; i >= 0; i--) {
			if (dp[N][i]) {
				max = i;
				break;
			}
		}
		bw.write(max + "\n");
		bw.newLine();
		bw.flush();
		bw.close();
	}

}