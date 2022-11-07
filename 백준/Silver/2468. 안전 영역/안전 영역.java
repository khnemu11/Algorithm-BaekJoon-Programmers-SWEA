import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());

		int area[][] = new int[size][size];
		int max = 1;

		HashSet<Integer> heightSet = new HashSet<>();

		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				int height = Integer.valueOf(st.nextToken());
				heightSet.add(height);
				area[i][j] = height;
			}
		}
		Iterator<Integer> it = heightSet.iterator();
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		while (it.hasNext()) {
			int height = it.next();
//			System.out.println("height : " + height);
			if (height == 1) {
				Math.max(max, 1);
				continue;
			}

			Queue<Coordinate> queue = new LinkedList<>();
			boolean visited[][] = new boolean[size][size];

			int count = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (visited[i][j] || area[i][j] < height) {
						continue;
					}

					queue.add(new Coordinate(i, j));

					while (!queue.isEmpty()) {
						Coordinate curr = queue.poll();
						if (visited[curr.row][curr.col]) {
							continue;
						}
						visited[curr.row][curr.col] = true;

						for (int k = 0; k < 4; k++) {
							int nextRow = curr.row + upDown[k];
							int nextCol = curr.col + leftRight[k];
							if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size
									|| visited[nextRow][nextCol] || area[nextRow][nextCol] < height) {
								continue;
							}
							visited[curr.row][curr.col] = true;
							queue.add(new Coordinate(nextRow, nextCol));
						}
					}

					count++;

				}
			}
//			System.out.println("count : " + count);
			max = Math.max(max, count);
		}
		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
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