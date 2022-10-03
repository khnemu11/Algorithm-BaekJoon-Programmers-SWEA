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
		int row = Integer.valueOf(st.nextToken());
		int col = Integer.valueOf(st.nextToken());
		char arr[][] = new char[row][col];
		int result = 1;
		for (int i = 0; i < row; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		for (int length = row; length > 1; length--) {
			for (int i = 0; i <= row - length; i++) {
				for (int j = 0; j <= col - length; j++) {
					if (arr[i][j] == arr[i + length - 1][j]
							&& arr[i + length - 1][j] == arr[i + length - 1][j + length - 1]
							&& arr[i + length - 1][j + length - 1] == arr[i][j + length - 1]) {
						result = length * length;
						break;
					}
				}
				if (result > 1) {
					break;
				}
			}
			if (result > 1) {
				break;
			}
		}

		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
