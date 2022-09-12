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

		int rowA = Integer.valueOf(st.nextToken());
		int colA = Integer.valueOf(st.nextToken());

		int matrixA[][] = new int[rowA][colA];

		for (int i = 0; i < rowA; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < colA; j++) {
				matrixA[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int rowB = Integer.valueOf(st.nextToken());
		int colB = Integer.valueOf(st.nextToken());

		int matrixB[][] = new int[rowB][colB];

		for (int i = 0; i < rowB; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < colB; j++) {
				matrixB[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		int matrixC[][] = new int[rowA][colB];

		for (int i = 0; i < rowA; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < colB; j++) {
				for (int k = 0; k < colA; k++) {
					matrixC[i][j] = matrixC[i][j] + matrixA[i][k] * matrixB[k][j];
				}
				sb.append(matrixC[i][j]);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);
			bw.write(sb.toString());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
