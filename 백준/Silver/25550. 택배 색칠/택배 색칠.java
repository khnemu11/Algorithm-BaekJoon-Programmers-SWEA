import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static boolean possible[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.valueOf(st.nextToken());
		int col = Integer.valueOf(st.nextToken());

		int boxes[][] = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				boxes[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		long result = 0;
		int[] upDown = { -1, 1, 0, 0 };
		int[] leftRight = { 0, 0, -1, 1 };

		for (int i = 1; i < row - 1; i++) {

			for (int j = 1; j < col - 1; j++) {
				if (boxes[i][j] == 1 || boxes[i][j] == 0) {
					continue;
				}
				int max = boxes[i][j] - 1;

				for (int k = 0; k < 4; k++) {
					max = Math.min(max, boxes[i + upDown[k]][j + leftRight[k]]);
				}
				result += max;
			}
			
		}

		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

}