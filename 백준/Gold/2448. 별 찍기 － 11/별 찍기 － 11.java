import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static char star[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.valueOf(br.readLine());
		int k = (int) (Math.log(N / 3) / Math.log(2));
		star = new char[N][2 * N - 1];

		for (int i = 0; i < N; i++) {
			Arrays.fill(star[i], ' ');
		}
		draw(0, star[0].length / 2, k);

		for (int i = 0; i < star.length; i++) {
			for (int j = 0; j < star[0].length; j++) {
				bw.write(star[i][j]);
			}
			bw.newLine();
		}
		bw.flush();
		br.close();
	}

	static public void draw(int row, int col, int k) {
		if (k == 0) {
			star[row][col] = '*';

			star[row + 1][col + 1] = '*';
			star[row + 1][col - 1] = '*';

			for (int i = -2; i < 3; i++) {
				star[row + 2][col + i] = '*';
			}
		} else {
			int length = (int) Math.pow(2, k) * 3;
			draw(row, col, k - 1);
			draw(length / 2 + row, col - length / 2, k - 1);
			draw(length / 2 + row, col + length / 2, k - 1);
		}
	}

}