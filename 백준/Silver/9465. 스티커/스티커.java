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

			for (int i = 1; i < length; i++) {
				sum[up][i] = sum[down][i - 1] + arr[up][i];
				sum[down][i] = sum[up][i - 1] + arr[down][i];

				if (i - 2 >= 0 && Math.max(sum[up][i - 2], sum[down][i - 2]) + arr[up][i] > sum[up][i]) {
					sum[up][i] = Math.max(sum[up][i - 2], sum[down][i - 2]) + arr[up][i];
				}
				if (i - 2 >= 0 && Math.max(sum[up][i - 2], sum[down][i - 2]) + arr[down][i] > sum[down][i]) {
					sum[down][i] = Math.max(sum[up][i - 2], sum[down][i - 2]) + arr[down][i];
				}

			}
			bw.write(String.valueOf(Math.max(sum[down][length-1], sum[up][length-1])));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}