import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.valueOf(st.nextToken());
		int col = Integer.valueOf(st.nextToken());

		int[][] matrixA = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				matrixA[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				matrixA[i][j] += Integer.valueOf(st.nextToken());
				sb.append(matrixA[i][j]);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
