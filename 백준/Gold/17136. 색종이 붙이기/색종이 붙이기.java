import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 
 * 각 색종이별 남은 개수를 계산하여 사용할 수 있는 색종이를 사용하는 방법
 * 1이 시작하는 위치를 미리 저장
 * 만약 색종이를 모두 사용했는데 1이 남아있다면 색종이로 덮기 불가
 * 
 * */

public class Main {
	static ArrayList<Coordinate> oneList = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	static int count[] = { 0, 5, 5, 5, 5, 5 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int map[][] = new int[10][10];
		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if (map[i][j] == 1) {
					oneList.add(new Coordinate(i, j));
				}
			}
		}

		select(0, 0, map);
		if (min == Integer.MAX_VALUE) {
			bw.write("-1\n");
		} else {
			bw.write(min + "\n");
		}

		bw.close();
		br.close();
	}

	public static void select(int idx, int cnt, int oriMap[][]) {
		if (cnt > 25) {
			return;
		} else if (idx >= oneList.size()) {
			min = Math.min(min, cnt);
			return;
		}
		Coordinate start = oneList.get(idx);

		if (oriMap[start.row][start.col] == 0) {
			select(idx + 1, cnt, oriMap);
		} else {
			for (int length = 1; length <= 5; length++) {
				if (count[length] <= 0) {
					continue;
				}

				if (!canAttach(start, length, oriMap)) {
					break;
				}
				int map[][] = attach(start, length, oriMap);
				count[length]--;
				select(idx + 1, cnt + 1, map);
				count[length]++;
			}
		}
	}

	public static int[][] copyArr(int map[][]) {
		int temp[][] = new int[map.length][map[0].length];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	public static boolean canAttach(Coordinate start, int length, int map[][]) {
		for (int i = start.row; i < start.row + length; i++) {
			for (int j = start.col; j < start.col + length; j++) {
				if (i < 0 || j < 0 || i >= map.length || j >= map[0].length) {
					return false;
				}
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static int[][] attach(Coordinate start, int length, int map[][]) {
		int temp[][] = new int[map.length][map[0].length];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i >= start.row && i < start.row + length && j >= start.col && j < start.col + length) {
					temp[i][j] = 0;
				} else {
					temp[i][j] = map[i][j];
				}

			}
		}
		return temp;
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

}