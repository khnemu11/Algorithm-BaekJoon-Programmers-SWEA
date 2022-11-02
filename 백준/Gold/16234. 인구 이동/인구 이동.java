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
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int size = Integer.valueOf(st.nextToken());
		int L = Integer.valueOf(st.nextToken());
		int R = Integer.valueOf(st.nextToken());
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		int country[][] = new int[size][size];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				country[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		int count = 0;

		while (true) {
			boolean change = false;
			boolean visited[][] = new boolean[size][size];

			if (change == true) {
				break;
			}

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (visited[i][j]) {
						continue;
					}

					Queue<Coordinate> queue = new LinkedList<>();
					queue.add(new Coordinate(i, j));
					Queue<Coordinate> union = new LinkedList<>();

					int sum = 0;

					while (!queue.isEmpty()) {
						int repeat = queue.size();

						for (int k = 0; k < repeat; k++) {
							Coordinate curr = queue.poll();
							if (visited[curr.row][curr.col]) {
								continue;
							}
							union.add(curr);
							sum += country[curr.row][curr.col];
//							System.out.println("search : " + curr.toString());
							visited[curr.row][curr.col] = true;

							for (int move = 0; move < 4; move++) {
								int nextRow = upDown[move] + curr.row;
								int nextCol = leftRight[move] + curr.col;

								if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size
										|| visited[nextRow][nextCol]) {
									continue;
								}

								int differ = Math.abs(country[curr.row][curr.col] - country[nextRow][nextCol]);

								if (differ < L || differ > R) {
									continue;
								}

								queue.add(new Coordinate(nextRow, nextCol));
							}
						}
					}
//					System.out.println(union.toString());
					if (union.size() > 1) {
						change = true;
						int polulation = sum / union.size();

						while (!union.isEmpty()) {
							Coordinate curr = union.poll();
							country[curr.row][curr.col] = polulation;

						}
//						System.out.println(Arrays.deepToString(country));
					}

				}

			}

//			System.out.println();
			if (!change) {
				break;
			}
			count++;
		}
//		System.out.println(Arrays.deepToString(country));
		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
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
