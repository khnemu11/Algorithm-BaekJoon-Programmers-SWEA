import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());
		int up = 0;
		int down = 1;
		while (T-- > 0) {
			int length = Integer.valueOf(br.readLine());
			int arr[][] = new int[2][length];
			int sum[][] = new int[2][length];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < length; j++) {
					arr[i][j] = Integer.valueOf(st.nextToken());
				}
			}

			sum[up][0] = arr[up][0];
			sum[down][0] = arr[down][0];
			if (length > 1) {
				sum[up][1] = arr[down][0] + arr[up][1];
				sum[down][1] = arr[up][0] + arr[down][1];
				for (int i = 2; i < length; i++) {
					int max = Math.max(sum[up][i - 2], sum[down][i - 2]);
					sum[up][i] = Math.max(sum[down][i - 1], max) + arr[up][i];
					sum[down][i] = Math.max(sum[up][i - 1], max) + arr[down][i];
				}
			}

			bw.write(String.valueOf(Math.max(sum[down][length - 1], sum[up][length - 1])));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}