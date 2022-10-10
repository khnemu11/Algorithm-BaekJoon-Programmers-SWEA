import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int colLength = Integer.valueOf(st.nextToken());
			int rowLength = Integer.valueOf(st.nextToken());

			if (colLength == 0 && rowLength == 0) {
				break;
			}

			boolean map[][] = new boolean[rowLength][colLength];
			visited = new boolean[rowLength][colLength];
			Stack<Integer> rows = new Stack<>();
			Stack<Integer> cols = new Stack<>();

			for (int i = 0; i < rowLength; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < colLength; j++) {
					if (st.nextToken().equals("1")) {
						map[i][j] = true;
						rows.add(i);
						cols.add(j);
					}
				}
			}
			int count = 0;
			while (!rows.isEmpty()) {
				int row = rows.pop();
				int col = cols.pop();

				if (visited[row][col]) {
					continue;
				}
				dfs(row, col, map);
				count++;
			}
			bw.write(String.valueOf(count));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int row, int col, boolean map[][]) {
		int[] upDown = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] leftRight = { -1, 0, 1, -1, 1, -1, 0, 1 };
		visited[row][col] = true;
		for (int i = 0; i < upDown.length; i++) {
			if (row + upDown[i] < 0 || row + upDown[i] >= visited.length || col + leftRight[i] < 0
					|| col + leftRight[i] >= visited[0].length || visited[row + upDown[i]][col + leftRight[i]]
					|| !map[row + upDown[i]][col + leftRight[i]]) {
				continue;
			}
			dfs(row + upDown[i], col + leftRight[i],map);
		}
	}
}