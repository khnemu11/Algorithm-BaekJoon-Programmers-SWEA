import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int rowLength = Integer.valueOf(st.nextToken());
		int colLength = Integer.valueOf(st.nextToken());
		int arr[][] = new int[rowLength][colLength];
		int sum[][] = new int[rowLength][colLength];
		for (int i = 0; i < rowLength; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < colLength; j++) {
				arr[i][j] = Integer.valueOf(st.nextToken());
				if (j > 0) {
					sum[i][j] = sum[i][j - 1] + arr[i][j];
				} else {
					sum[i][j] = arr[i][j];
				}
			}
		}
		int T = Integer.valueOf(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int fromRow = Integer.valueOf(st.nextToken()) - 1;
			int fromCol = Integer.valueOf(st.nextToken()) - 1;
			int toRow = Integer.valueOf(st.nextToken()) - 1;
			int toCol = Integer.valueOf(st.nextToken()) - 1;
			int result = 0;
			for (int i = 0; i <= toRow - fromRow; i++) {
				if (fromCol > 0) {
					result += sum[fromRow + i][toCol] - sum[fromRow + i][fromCol - 1];
				} else {
					result += sum[fromRow + i][toCol];
				}
			}
			bw.write(String.valueOf(result));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
