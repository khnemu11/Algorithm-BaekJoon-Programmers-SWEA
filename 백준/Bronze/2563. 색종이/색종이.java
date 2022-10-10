import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		boolean board[][] = new boolean[101][101];

		int num = Integer.valueOf(br.readLine());
		int count = 0;

		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int col = Integer.valueOf(st.nextToken());
			int row = Integer.valueOf(st.nextToken());

			for (int j = row; j < row + 10; j++) {
				for (int k = col; k < col + 10; k++) {
					if (board[j][k] == false) {
						count++;
						board[j][k] = true;
					}
				}
			}
		}
		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}