import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());

		Integer map[][] = new Integer[size][size];
		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		int cnt = 1;
		int maxCnt = 5;
		int maxVal = 0;
		Queue<Integer[][]> q = new LinkedList<>();
		q.add(map);
		while (cnt <= 5) {
//			System.out.println("==========" + cnt + "=========");
			int loop = q.size();
			while (loop-- > 0) {
				map = q.poll();
				/*
				 * System.out.println("==========start========="); for (int i = 0; i < size;
				 * i++) { for (int j = 0; j < size; j++) { System.out.print(map[i][j] + " "); }
				 * System.out.println(); } System.out.println();
				 */
				for (int k = 0; k < upDown.length; k++) {
					Integer movedMap[][] = new Integer[size][size];
					boolean sumed[][] = new boolean[size][size];
					for (int i = 0; i < movedMap.length; i++) {
						Arrays.fill(movedMap[i], 0);
					}
					PriorityQueue<Block> pq = new PriorityQueue<>();
					for (int i = 0; i < map.length; i++) {
						for (int j = 0; j < map[0].length; j++) {
							Block block = new Block(i, j, map[i][j], 0);

							if (map[block.row][block.col] == 0) {
								continue;
							}

							do {
								block.row = block.row + upDown[k];
								block.col = block.col + leftRight[k];
								block.distance = block.distance + 1;

							} while (isValid(block.row, block.col, size));

							block.row = block.row - upDown[k];
							block.col = block.col - leftRight[k];
							block.distance = block.distance - 1;

//							System.out.println("move: " + block);
							pq.add(block);
						}
					}
					while (!pq.isEmpty()) {
						Block curr = pq.poll();

						while (movedMap[curr.row][curr.col] != 0) {
							curr.row -= upDown[k];
							curr.col -= leftRight[k];
						}

						if (isValid(curr.row + upDown[k], curr.col + leftRight[k], size)
								&& movedMap[curr.row + upDown[k]][curr.col + leftRight[k]] == curr.val
								&& !sumed[curr.row + upDown[k]][curr.col + leftRight[k]]) {
							movedMap[curr.row + upDown[k]][curr.col + leftRight[k]] = curr.val * 2;
							maxVal = Math.max(movedMap[curr.row + upDown[k]][curr.col + leftRight[k]], maxVal);
							sumed[curr.row + upDown[k]][curr.col + leftRight[k]] = true;
						} else {
							movedMap[curr.row][curr.col] = curr.val;
							maxVal = Math.max(curr.val, maxVal);
						}
					}

					/*
					 * for (int i = 0; i < size; i++) { for (int j = 0; j < size; j++) {
					 * System.out.print(movedMap[i][j] + " "); } System.out.println(); }
					 * System.out.println();
					 */
					q.add(movedMap);
				}
			}

			cnt++;
		}

		System.out.println(maxVal);
	}

	public static boolean isValid(int row, int col, int size) {
		if (row < 0 || col < 0 || row >= size || col >= size) {
			return false;
		}

		return true;
	}
}

class Block implements Comparable<Block> {
	int row;
	int col;
	int val;
	int distance;

	public Block(int row, int col, int val, int distance) {
		this.row = row;
		this.col = col;
		this.val = val;
		this.distance = distance;
	}

	@Override
	public int compareTo(Block o) {
		return this.distance - o.distance;
	}

	@Override
	public String toString() {
		return "Block [row=" + row + ", col=" + col + ", val=" + val + ", distance=" + distance + "]";
	}

}