import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int max = 0;
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.valueOf(st.nextToken());
		int col = Integer.valueOf(st.nextToken());
		int arr[][] = new int[row][col];
		visited = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				dfs(0, i, j, 0, arr);

				int sum = 0;
				if (i - 1 >= 0 && i + 1 < row) {
					sum = arr[i - 1][j] + arr[i][j] + arr[i + 1][j];
					if (j - 1 >= 0 && max < sum + arr[i][j - 1]) {
						max = sum + arr[i][j - 1];
					}
					if (j + 1 < col && max < sum + arr[i][j + 1]) {
						max = sum + arr[i][j + 1];
					}
				}
				if (j - 1 >= 0 && j + 1 < col) {
					sum = arr[i][j - 1] + arr[i][j] + arr[i][j + 1];
					if (i - 1 >= 0 && max < sum + arr[i - 1][j]) {
						max = sum + arr[i - 1][j];
					}
					if (i + 1 < row && max < sum + arr[i + 1][j]) {
						max = sum + arr[i + 1][j];
					}
				}
			}
		}
		bw.write(String.valueOf(max));
		bw.newLine();
		br.close();
		bw.close();
	}

	public static void dfs(int depth, int row, int col, int sum, int arr[][]) {
		if (depth == 4) {
			if (max < sum) {
				max = sum;
			}
		} else {
			int upDown[] = { -1, 1, 0, 0 };
			int leftRight[] = { 0, 0, -1, 1 };
			
			for (int i = 0; i < 4; i++) {
				int nextRow = upDown[i] + row;
				int nextCol = leftRight[i] + col;

				if (nextRow >= arr.length || nextRow < 0 || nextCol < 0 || nextCol >= arr[0].length
						|| visited[nextRow][nextCol]) {
					continue;
				} else {
					visited[row][col] = true;
					dfs(depth + 1, nextRow, nextCol, sum + arr[row][col], arr);
					visited[row][col] = false;
				}
			}
		}
	}
}
