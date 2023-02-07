import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 풀이 과정
 * 
 * 시계방향(오른쪽->아래->왼쪽->위)으로 탐색
 * 이때 이미 탐색했거나 배열 이외의 좌표인경우 다음 방향으로 가도록 설정한다.
 * */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			int map[][] = new int[N][N];
			boolean visited[][] = new boolean[N][N];
			int upDown[] = { 0, 1, 0, -1 };
			int leftRight[] = { 1, 0, -1, 0 };

			Coordinate curr = new Coordinate(0, 0);

			int move = 0;

			for (int i = 1; i <= N * N; i++) {
				visited[curr.row][curr.col] = true;
				map[curr.row][curr.col] = i;
				Coordinate next = new Coordinate(curr.row + upDown[move], curr.col + leftRight[move]);
				if (!isValid(next, map, visited)) {
					move = move + 1 == 4 ? 0 : move + 1;
					next = new Coordinate(curr.row + upDown[move], curr.col + leftRight[move]);
				}
				curr = next;
			}

			StringBuilder sb = new StringBuilder();

			sb.append("#").append(test_case).append("\n");
			bw.write(sb.toString());
			for (int i = 0; i < map.length; i++) {
				sb.setLength(0);
				for (int j = 0; j < map[0].length; j++) {
					sb.append(map[i][j]).append(" ");
				}
				bw.write(sb.deleteCharAt(sb.length() - 1).append("\n").toString());
			}
		}

		bw.flush();
	}

	public static boolean isValid(Coordinate coord, int map[][], boolean visited[][]) {
		if (coord.row < 0 || coord.row >= map.length || coord.col < 0 || coord.col >= map[0].length
				|| visited[coord.row][coord.col]) {
			return false;
		}
		return true;
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}