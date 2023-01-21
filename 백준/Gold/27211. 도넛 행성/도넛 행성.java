import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int N = input[0];
		int M = input[1];

		map = new int[N][M];
		visited = new boolean[N][M];
		Queue<Coordinate> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());

				if (map[i][j] == 0) {
					q.add(new Coordinate(i, j));
				}
			}
		}
		int cnt = 0;
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		while (!q.isEmpty()) {
			Coordinate start = q.poll();

			if (visited[start.row][start.col]) {
				continue;
			}

			Queue<Coordinate> space = new LinkedList<>();
			space.add(start);

			while (!space.isEmpty()) {
				Coordinate curr = space.poll();

				if (visited[curr.row][curr.col]) {
					continue;
				}
				visited[curr.row][curr.col] = true;

				for (int k = 0; k < upDown.length; k++) {
					Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
					if (next.row < 0) {
						next.row = map.length - 1;
					} else if (next.row >= map.length) {
						next.row = 0;
					} else if (next.col < 0) {
						next.col = map[0].length - 1;
					} else if (next.col >= map[0].length) {
						next.col = 0;
					}

					if (visited[next.row][next.col] || map[next.row][next.col] == 1) {
						continue;
					}

					space.add(next);
				}
			}

			cnt++;
		}
		bw.write(String.valueOf(cnt));
		bw.flush();
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