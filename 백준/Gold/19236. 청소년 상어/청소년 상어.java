import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	생선의 무게가 작은순서로 이동
	방향을 이동할 수 없으면 45도 왼쪽 회전
	물고기가 없는 칸에는 상어가 못감
	물고기를 상어가 먹을 수 없으면 종료
	
	걸린시간 : 15분 41초
*/
public class Main {
	static int parents[];
	static int moveRow[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int moveCol[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int TOP = 1;
	static int LEFT_TOP = 2;
	static int LEFT = 3;
	static int LEFT_BOTTOM = 4;
	static int BOTTOM = 5;
	static int RIGHT_BOTTOM = 6;
	static int RIGHT = 7;
	static int RIGHT_TOP = 8;
	static int max;
	static boolean eaten[] = new boolean[17];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish map[][] = new Fish[4][4];

		for (int row = 0; row < map.length; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int col = 0; col < map[0].length; col++) {
				int weight = Integer.valueOf(st.nextToken());
				int direction = Integer.valueOf(st.nextToken());

				map[row][col] = new Fish(row, col, weight, direction);
			}
		}

		Fish shark = new Fish(0, 0, 0, 0);
		move(map, shark);
		System.out.println(max);
	}

	public static void move(Fish map[][], Fish shark) {
		Fish tempMap[][] = copyArr(map);
		Fish tempShark = new Fish(shark.row, shark.col, shark.weight, shark.direction);

		tempShark.weight += tempMap[shark.row][shark.col].weight;
		tempShark.direction = tempMap[shark.row][shark.col].direction;
		eaten[tempMap[shark.row][shark.col].weight] = true; // 맵에 있는 물고기 삭제
		max = Math.max(max, tempShark.weight);
//		System.out.println(tempShark);

		PriorityQueue<Fish> pq = new PriorityQueue<>();
		Fish fishArr[] = new Fish[17];

		for (int i = 0; i < tempMap.length; i++) {
			for (int j = 0; j < tempMap[0].length; j++) {
				if (tempMap[i][j] == null) {
					continue;
				}
				fishArr[tempMap[i][j].weight] = tempMap[i][j];
			}
		}

		for (int i = 0; i < fishArr.length; i++) {
			if (fishArr[i] == null || eaten[i]) {
				continue;
			}
			Fish curr = fishArr[i];

			Coordinate nextCoord = new Coordinate(curr.row + moveRow[curr.direction],
					curr.col + moveCol[curr.direction]);

			for (int k = 0; k < moveRow.length; k++) {
				if (nextCoord.row < 0 || nextCoord.col < 0 || nextCoord.row >= map.length
						|| nextCoord.col >= map[0].length
						|| (nextCoord.row == tempShark.row && nextCoord.col == tempShark.col)) {
					curr.direction = curr.direction + 1 > RIGHT_TOP ? 1 : curr.direction + 1;
					nextCoord = new Coordinate(curr.row + moveRow[curr.direction], curr.col + moveCol[curr.direction]);
					continue;
				}

				Fish next = tempMap[nextCoord.row][nextCoord.col];
				Fish tempFish = new Fish(curr.row, curr.col, curr.weight, curr.direction);
				tempMap[curr.row][curr.col] = new Fish(curr.row, curr.col, next.weight, next.direction);
				tempMap[next.row][next.col] = new Fish(next.row, next.col, tempFish.weight, tempFish.direction);
				fishArr[tempMap[curr.row][curr.col].weight] = tempMap[curr.row][curr.col];
				fishArr[tempMap[next.row][next.col].weight] = tempMap[next.row][next.col];
				break;
			}
		}
//		printArr(tempMap);
		Fish nextShark = new Fish(tempShark.row + moveRow[tempShark.direction],
				tempShark.col + moveCol[tempShark.direction], tempShark.weight, tempShark.direction);

		while (true) {
			if (nextShark.row < 0 || nextShark.col < 0 || nextShark.row >= map.length
					|| nextShark.col >= map[0].length) {
				break;
			}

			if (!eaten[tempMap[nextShark.row][nextShark.col].weight]) {
				eaten[tempMap[nextShark.row][nextShark.col].weight] = true;
				move(tempMap, nextShark);
				eaten[tempMap[nextShark.row][nextShark.col].weight] = false;
			}

			nextShark = new Fish(nextShark.row + moveRow[nextShark.direction],
					nextShark.col + moveCol[nextShark.direction], nextShark.weight, nextShark.direction);
		}

	}

	public static void printArr(Fish map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static Fish[][] copyArr(Fish map[][]) {
		Fish temp[][] = new Fish[map.length][map[0].length];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == null) {
					continue;
				}
				temp[i][j] = new Fish(map[i][j].row, map[i][j].col, map[i][j].weight, map[i][j].direction);
			}
		}
		return temp;
	}

	public static Fish[] copyArr(Fish fishes[]) {
		Fish temp[] = new Fish[fishes.length];

		for (int i = 0; i < temp.length; i++) {
			if (fishes[i] == null) {
				continue;
			}
			temp[i] = new Fish(fishes[i].row, fishes[i].col, fishes[i].weight, fishes[i].direction);
		}

		return temp;
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

class Fish extends Coordinate implements Comparable<Fish> {
	int direction;
	int weight;

	public Fish(int row, int col, int weight, int direction) {
		super(row, col);
		this.direction = direction;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Fish [direction=" + direction + ", weight=" + weight + ", row=" + row + ", col=" + col + "]";
	}

	@Override
	public int compareTo(Fish o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}

}