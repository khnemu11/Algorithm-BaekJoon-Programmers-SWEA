import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		List<Integer> department = new ArrayList<>();
		int size = Integer.valueOf(br.readLine());
		visited = new boolean[size][size];
		int total = 0;
		char apartment[][] = new char[size][size];

		for (int i = 0; i < size; i++) {
			apartment[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (!visited[i][j] && apartment[i][j] == '1') {
					int count = dfs(apartment, i, j);
					department.add(count+1);
					total++;
				}
			}
		}
		Collections.sort(department);
		bw.write(String.valueOf(total));
		bw.newLine();
		for(int i=0;i<department.size();i++) {
			bw.write(String.valueOf(department.get(i)));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static int dfs(char[][] apartment, int row, int col) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };
		int count = 0;
		visited[row][col] = true;

		for (int k = 0; k < 4; k++) {
			if (row + upDown[k] < 0 || row + upDown[k] >= apartment.length || col + leftRight[k] < 0
					|| col + leftRight[k] >= apartment.length || visited[row + upDown[k]][col + leftRight[k]]) {
				continue;
			} else if (apartment[row + upDown[k]][col + leftRight[k]] == '1') {
				count = count + dfs(apartment, row + upDown[k], col + leftRight[k]);
				count++;
			}
		}

		return count;
	}
}
