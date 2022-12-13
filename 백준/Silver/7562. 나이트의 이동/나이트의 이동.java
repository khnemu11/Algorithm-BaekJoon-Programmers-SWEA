import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());
		int UpDown[] = { -2, -2, -1, -1, 1, 1, 2, 2 };
		int leftRight[] = { -1, 1, -2, 2, -2, 2, -1, 1 };

		for (int test_case = 0; test_case < T; test_case++) {
			int size = Integer.valueOf(br.readLine());
			boolean visited[][] = new boolean[size][size];
			int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			Coordinate start = new Coordinate(input[0], input[1]);

			input = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			Coordinate end = new Coordinate(input[0], input[1]);

			Queue<Coordinate> queue = new LinkedList<>();
			queue.add(start);

			int count = 0;
			boolean find = false;

			while (!queue.isEmpty() && !find) {
				int loop = queue.size();

				while (loop-- > 0) {
					Coordinate curr = queue.poll();
					visited[curr.row][curr.col] = true;

					if (curr.equals(end)) {
						find = true;
						break;
					}

					for (int i = 0; i < UpDown.length; i++) {
						Coordinate next = new Coordinate(curr.row + UpDown[i], curr.col + leftRight[i]);
						if (next.row < 0 || next.col < 0 || next.row >= size || next.col >= size
								|| visited[next.row][next.col]) {
							continue;
						}
//						System.out.println(count + ">>" + curr.toString() + " -> " + next.toString());
						visited[next.row][next.col] = true;
						queue.add(next);
					}

				}
				if (!find) {
					count++;
				}

			}
			bw.write(String.valueOf(count));
			bw.newLine();
		}

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

	@Override
	public boolean equals(Object o) {
		Coordinate target = (Coordinate) o;
		if (this.row == target.row && this.col == target.col) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}
}