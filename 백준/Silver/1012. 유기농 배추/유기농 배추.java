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

		int T = Integer.valueOf(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int col = Integer.valueOf(st.nextToken());
			int row = Integer.valueOf(st.nextToken());
			int num = Integer.valueOf(st.nextToken());

			boolean farm[][] = new boolean[row][col];
			boolean visited[][] = new boolean[row][col];
			int count = 0;

			for (int i = 0; i < num; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.valueOf(st.nextToken());
				int y = Integer.valueOf(st.nextToken());
				farm[y][x] = true;
			}
			
			for (int i = 0; i < farm.length; i++) {
				for (int j = 0; j < farm[0].length; j++) {
					if (!visited[i][j] && farm[i][j]) {
						find(farm,visited, j, i);
						count++;
					}
				}
			}
			bw.write(String.valueOf(count));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void find(boolean[][] farm, boolean visited[][], int x, int y) {
		visited[y][x] = true;

		int[] leftRight = { -1, 1, 0, 0 };
		int[] downUp = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			int nextX = leftRight[i] + x;
			int nextY = downUp[i] + y;
			
			if (nextX >= 0 && nextX < farm[0].length && nextY >= 0 && nextY < farm.length && !visited[nextY][nextX] && farm[nextY][nextX]) {

				find(farm,visited,nextX,nextY);
			}
		}
	}
}