import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];
	static int map[][];
	static ArrayList<Coordinate> house = new ArrayList<>();
	static ArrayList<Coordinate> chicken = new ArrayList<>();
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.valueOf(st.nextToken());
		int pick = Integer.valueOf(st.nextToken());

		map = new int[size][size];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if (map[i][j] == 1) {
					house.add(new Coordinate(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Coordinate(i, j));
				}
			}
		}
		visited = new boolean[chicken.size()];

		dfs(pick, 0, 0);

		bw.write(String.valueOf(min));
		bw.newLine();
		bw.close();
		br.close();
	}

	public static void dfs(int depth, int curr, int index) {
//		System.out.println("depth : " + curr);
//		System.out.println(Arrays.toString(visited));
		if (depth == curr) {
			int sum = 0;

			for (int i = 0; i < house.size(); i++) {
				int distance = Integer.MAX_VALUE;

				for (int j = 0; j < chicken.size(); j++) {
					if (visited[j]) {
						distance = Math.min(distance, Math.abs(house.get(i).row - chicken.get(j).row)
								+ Math.abs(house.get(i).col - chicken.get(j).col));
					}
				}
//				System.out.println(house.get(i).toString() + "'s distance : " + distance);
				sum += distance;
			}
//			System.out.println("min : " + min);
			min = Math.min(min, sum);

		} else {
			for (int i = index; i < chicken.size(); i++) {
				visited[i] = true;

				dfs(depth, curr + 1, i + 1);

				visited[i] = false;
			}
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

	@Override
	public String toString() {
		return "[ row : " + row + " col : " + col + " ]";
	}
}