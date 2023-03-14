import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	
	다음 움직임의 부모는 이전 움직임 -> 트리로 구현 가능
	루트의 개수를 출력
 */
public class Main {
	static int parents[][];
	static Coordinate coords[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());

		parents = new int[height][width];
		coords = new Coordinate[height * width];
		String map[][] = new String[height][width];
		Queue<Coordinate> queue = new LinkedList<>();
		int seq = 0;
		for (int i = 0; i < height; i++) {
			String element[] = br.readLine().split("");
			for (int j = 0; j < width; j++) {
				map[i][j] = element[j];
				coords[seq] = new Coordinate(seq, i, j);
				parents[i][j] = seq;
				queue.add(new Coordinate(seq++, i, j));
			}
		}

		HashMap<String, Integer> rowMoveMap = new HashMap<>();
		HashMap<String, Integer> colMoveMap = new HashMap<>();

		rowMoveMap.put("D", 1);
		rowMoveMap.put("U", -1);
		rowMoveMap.put("L", 0);
		rowMoveMap.put("R", 0);

		colMoveMap.put("D", 0);
		colMoveMap.put("U", 0);
		colMoveMap.put("L", -1);
		colMoveMap.put("R", 1);

		while (!queue.isEmpty()) {
			Coordinate currCoordinate = queue.poll();
			Coordinate nextCoordinate = new Coordinate(seq,
					currCoordinate.row + rowMoveMap.get(map[currCoordinate.row][currCoordinate.col]),
					currCoordinate.col + colMoveMap.get(map[currCoordinate.row][currCoordinate.col]));

			if (nextCoordinate.row < 0 || nextCoordinate.col < 0 || nextCoordinate.row >= map.length
					|| nextCoordinate.col >= map[0].length) {
				continue;
			}

			union(currCoordinate, nextCoordinate);
		}

		int cnt = 0;

		boolean visited[] = new boolean[width * height];

		for (int i = 0; i < coords.length; i++) {
			int parent = getParent(coords[i]);
			if (visited[parent]) {
				continue;
			}
			visited[parent] = true;
			cnt++;
		}

		bw.write(cnt + "\n");
		bw.flush();
	}

	public static int getParent(Coordinate child) {
		if (parents[child.row][child.col] == child.seq) {
			return parents[child.row][child.col];
		} else {
			parents[child.row][child.col] = getParent(coords[parents[child.row][child.col]]);
			return parents[child.row][child.col];
		}
	}

	public static void union(Coordinate a, Coordinate b) {
		Coordinate pa = coords[getParent(a)];
		Coordinate pb = coords[getParent(b)];

		parents[pb.row][pb.col] = pa.seq;
	}
}

class Coordinate {
	int seq;
	int row;
	int col;

	public Coordinate(int seq, int row, int col) {
		this.seq = seq;
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}
}