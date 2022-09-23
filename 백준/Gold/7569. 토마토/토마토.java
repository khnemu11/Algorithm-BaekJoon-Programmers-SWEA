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
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int col = Integer.valueOf(st.nextToken());
		int row = Integer.valueOf(st.nextToken());
		int height = Integer.valueOf(st.nextToken());
		int goal = 0;
		int[][][] tomato = new int[row][col][height];
		Queue<Integer> nextX = new LinkedList<>();
		Queue<Integer> nextY = new LinkedList<>();
		Queue<Integer> nextZ = new LinkedList<>();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < row; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < col; k++) {
					tomato[j][k][i] = Integer.valueOf(st.nextToken());
					if (tomato[j][k][i] == 1) {
						nextX.add(j);
						nextY.add(k);
						nextZ.add(i);
					} else if (tomato[j][k][i] == 0) {
						goal++;
					}
				}
			}
		}
		int count = 0;
		int ripe = 0;
//		System.out.println(Arrays.deepToString(tomato));
		while (!nextX.isEmpty()) {
			int size = nextX.size();
			count++;
			for (int q = 0; q < size; q++) {
				int currX = nextX.poll();
				int currY = nextY.poll();
				int currZ = nextZ.poll();
				int xMove[] = { -1, 1, 0, 0, 0, 0 };
				int yMove[] = { 0, 0, -1, 1, 0, 0 };
				int zMove[] = { 0, 0, 0, 0, -1, 1 };

//				System.out.println("X : " + currX + " Y: " + currY + " Z: " + currZ);
				for (int k = 0; k < 6; k++) {
					if (currX + xMove[k] < 0 || currX + xMove[k] >= row || currY + yMove[k] < 0
							|| currY + yMove[k] >= col || currZ + zMove[k] < 0 || currZ + zMove[k] >= height
							|| tomato[currX + xMove[k]][currY + yMove[k]][currZ + zMove[k]] == -1
							|| tomato[currX + xMove[k]][currY + yMove[k]][currZ + zMove[k]] == 1) {
						continue;
					}
					tomato[currX + xMove[k]][currY + yMove[k]][currZ + zMove[k]] = 1;
					nextX.add(currX + xMove[k]);
					nextY.add(currY + yMove[k]);
					nextZ.add(currZ + zMove[k]);
					ripe++;
				}
			}

//			for (int i = 0; i < height; i++) {
//				for (int j = 0; j < row; j++) {
//					for (int k = 0; k < col; k++) {
//						System.out.print(tomato[j][k][i] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
		}

		if (ripe != goal) {
//			System.out.println("ripe : " + ripe + " goal : " + goal);
			bw.write("-1");
			bw.newLine();
		} else {
			bw.write(String.valueOf(count - 1));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
