import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[][];
	static Queue<Integer> xQueue = new LinkedList<Integer>();
	static Queue<Integer> yQueue = new LinkedList<Integer>();
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		count = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());

		int col = Integer.valueOf(st.nextToken());
		int row = Integer.valueOf(st.nextToken());
		int box[][] = new int[row][col];
		int goal = 0;
		int currTomatoCount = 0;
		visited = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < col; j++) {
				box[i][j] = Integer.valueOf(st.nextToken());
				if (box[i][j] == 1) {
					xQueue.add(i);
					yQueue.add(j);
					visited[i][j] = true;
					goal++;
					currTomatoCount++;
				} else if (box[i][j] == 0) {
					goal++;
				}
			}
		}

		find(box, currTomatoCount, goal);

		bw.write(String.valueOf(count - 1));
		bw.flush();
		bw.close();
		br.close();
	}

	public static void find(int box[][], int currTomatoCount, int goal) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };
		int size = xQueue.size();
		
		for (int i = 0; i < size; i++) {
			int row = xQueue.poll();
			int col = yQueue.poll();

			box[row][col] = 1;
			for (int k = 0; k < 4; k++) {
				if (row + upDown[k] < 0 || row + upDown[k] >= box.length || col + leftRight[k] < 0
						|| col + leftRight[k] >= box[0].length || visited[row + upDown[k]][col + leftRight[k]]) {
					continue;
				}
				if (box[row + upDown[k]][col + leftRight[k]] == 0) {
					visited[row + upDown[k]][col + leftRight[k]] = true;
					xQueue.add(row + upDown[k]);
					yQueue.add(col + leftRight[k]);
					currTomatoCount++;
				}
			}
		}
		count++;
		if (!xQueue.isEmpty()) {
			find(box, currTomatoCount, goal);
		} else {
			if (currTomatoCount != goal) {
				count = 0;
			}
		}
	}
}