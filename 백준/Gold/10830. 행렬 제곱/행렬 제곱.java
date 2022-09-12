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

		int size = Integer.valueOf(st.nextToken());
		long exponent = Long.valueOf(st.nextToken());

		long matrix[][] = new long[size][size];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				matrix[i][j] = Long.valueOf(st.nextToken()) % 1000;
			}
		}

		long[][] result = pow(matrix, exponent);

		for (int i = 0; i < result.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < result.length; j++) {
				sb.append(result[i][j]);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length() - 1);
			bw.write(sb.toString());
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static long[][] multifly(long[][] a, long[][] b) {
		long[][] c = new long[a.length][a.length];

		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				for (int k = 0; k < c.length; k++) {
					c[i][j] = (c[i][j] + a[i][k] * b[k][j] % 1000) % 1000;
				}
			}

		}
		return c;
	}

	public static long[][] pow(long[][] matrix, long exponent) {
		if (exponent == 1) {
			return matrix;
		} else {
			long result[][];
			long[][] temp = pow(matrix, exponent / 2);
			if (exponent % 2 == 1) {
				result = multifly(multifly(temp, temp), matrix);
			} else {
				result = multifly(temp, temp);
			}
			return result;
		}
	}
}
