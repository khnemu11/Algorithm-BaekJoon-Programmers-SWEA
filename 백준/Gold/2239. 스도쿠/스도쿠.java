import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

/* 
 *  풀이 알고리즘
 *  백트래킹을 이용
 * */

public class Main {
	static int map[][];
	static ArrayList<Coordinate> emptyList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new int[9][9];

		for (int i = 0; i <9; i++) {
			int nums[] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
			for (int j = 0; j < nums.length; j++) {
				map[i][j] = Integer.valueOf(nums[j]);
				if (map[i][j] == 0) {
					emptyList.add(new Coordinate(i, j));
				}
			}
		}

		select(0);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	public static boolean select(int idx) {
		if (idx == emptyList.size()) {
			return true;
		}

		Coordinate curr = emptyList.get(idx);

		for (int i = 1; i <= 9; i++) {
			if (!isValid(curr, i)) {
				continue;
			}
			map[curr.row][curr.col] = i;
			if (select(idx + 1)) {
				return true;
			}
			map[curr.row][curr.col] = 0;
		}

		return false;
	}

	public static boolean isValid(Coordinate coord, int val) {
		for (int i = 0; i < 9; i++) {
			if (map[i][coord.col] == val) {
				return false;
			}
		}
		for (int i = 0; i < 9; i++) {
			if (map[coord.row][i] == val) {
				return false;
			}
		}

		Coordinate center = new Coordinate((coord.row / 3) * 3 + 1, (coord.col / 3) * 3 + 1);

		int dx[] = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
		int dy[] = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

		for (int k = 0; k < dx.length; k++) {
			Coordinate next = new Coordinate(center.row + dx[k], center.col + dy[k]);

			if (map[next.row][next.col] == val) {
				return false;
			}
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