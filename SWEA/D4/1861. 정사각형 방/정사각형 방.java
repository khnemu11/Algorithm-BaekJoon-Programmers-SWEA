import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			int map[][] = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.valueOf(st.nextToken());
				}
			}

			int upDown[] = { -1, 1, 0, 0 };
			int leftRight[] = { 0, 0, -1, 1 };
			boolean visited[][] = new boolean[N][N];
			int max = 0;
			int maxStartRoom = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (visited[row][col]) {
						continue;
					}
					int startRoom = map[row][col];
					int length = 1;
					Coordinate startCoord = new Coordinate(row, col);

					Queue<Coordinate> q = new LinkedList<>();
					q.add(startCoord);

					while (!q.isEmpty()) {
						Coordinate curr = q.poll();
						visited[curr.row][curr.col] = true;
						for (int k = 0; k < upDown.length; k++) {
							Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

							if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length
									|| visited[next.row][next.col]
									|| Math.abs(map[curr.row][curr.col] - map[next.row][next.col]) != 1) {
								continue;
							}
							visited[next.row][next.col] = true;
							startRoom = Math.min(startRoom, map[next.row][next.col]);
							length++;
							q.add(next);
						}
					}
					if (max < length) {
						max = length;
						maxStartRoom = startRoom;
					} else if (max == length) {
						maxStartRoom = Math.min(startRoom, maxStartRoom);
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(maxStartRoom).append(" ").append(max).append("\n");
			bw.write(sb.toString());
		}
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
}