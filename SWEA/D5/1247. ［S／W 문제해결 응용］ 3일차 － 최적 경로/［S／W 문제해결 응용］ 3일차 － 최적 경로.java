import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution{
	static boolean visited[];
	static Coordinate house[];
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int size = Integer.valueOf(br.readLine());
			house = new Coordinate[size];
			visited = new boolean[size];
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());

			Coordinate start = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
			Coordinate end = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));

			for (int i = 0; i < house.length; i++) {
				house[i] = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
			}
			FindMin(0, 0, start, end);

			StringBuilder sb = new StringBuilder();

			sb.append("#" + test_case + " " + min);
			bw.write(sb.toString());
			bw.newLine();
		}

		bw.flush();
	}

	public static void FindMin(int depth, int sum, Coordinate curr, Coordinate end) {
		if (depth == house.length) {
			int distance = Math.abs(curr.row - end.row) + Math.abs(curr.col - end.col);
			min = Math.min(distance + sum, min);
			return;
		}

		for (int i = 0; i < house.length; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			int distance = Math.abs(curr.row - house[i].row) + Math.abs(curr.col - house[i].col);
			FindMin(depth + 1, sum + distance, house[i], end);
			visited[i] = false;
		}
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