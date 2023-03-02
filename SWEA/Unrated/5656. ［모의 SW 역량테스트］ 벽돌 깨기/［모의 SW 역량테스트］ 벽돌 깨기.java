import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 	0) 문제 목표
 * 		1.2~5번을 N번 반복
 *  	2.맨 위의 블록을 1개 선택
 *      3.선택한 블록 및 4방향의 (블록의 숫자 -1)거리 만큼 파괴
 *      4.파괴된 블록 또한 2번 수행
 *      5.빈공간이 있다면 블록의 행을 올려서 빈공간을 체움
 *      6.모든 블록의 개수 파악 후 최소 개수를 저장
 *      7.최소 개수 출력

	1) 필요한 알고리즘
		맨 위의 블록 선택 -> dfs
		4방향 파괴 -> bfs
		블록을 체움 -> 열별로 남아있는 블록을 담고 가장 마지막의 블록(가장 위의 블록)을 선택을 위해 저장
		블록 개수 -> n^2 탐색
*/

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int topBlocks[]; // 맨 위의 블록의 행값을 저장하는 배열
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };
	static int bombMinCnt = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			int map[][] = init();
//			printArr(map);
			explosion(0, map, topBlocks);
			bw.write("#"+testcase+" "+bombMinCnt + "\n");
		}
		
		bw.flush(); // 결과 출력
		br.close();
		bw.close();
	}

	public static void printArr(int map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int[][] init() throws IOException { // 초기화 메소드
		StringTokenizer st = new StringTokenizer(br.readLine());
		bombMinCnt = Integer.MAX_VALUE;
		N = Integer.valueOf(st.nextToken());
		int W = Integer.valueOf(st.nextToken());
		int H = Integer.valueOf(st.nextToken());
		int map[][] = new int[H][W];
		topBlocks = new int[W];
		Arrays.fill(topBlocks, -1);

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if (topBlocks[j] == -1 && map[i][j] > 0) {
					topBlocks[j] = i;
				}
			}
		}
		return map;
	}

	public static void explosion(int cnt, int oriMap[][], int oriTopBlocks[]) {
		if (cnt == N || !isHasTop(oriTopBlocks)) {
//			System.out.println("final");
//			printArr(oriMap);
			int bomCnt = 0;
			for (int i = 0; i < oriMap.length; i++) {
				for (int j = 0; j < oriMap[0].length; j++) {
					if (oriMap[i][j] > 0) {
						bomCnt++;
					}
				}
			}
			bombMinCnt = Math.min(bombMinCnt, bomCnt);
		} else {
			for (int i = 0; i < oriTopBlocks.length; i++) {
				if (oriTopBlocks[i] < 0) {
					continue;
				}
				int map[][] = copyArr(oriMap);
				int topBlocks[] = copyArr(oriTopBlocks);
				Coordinate top = new Coordinate(oriTopBlocks[i], i);
				Queue<Coordinate> bombs = new LinkedList<>();
				bombs.add(top);

				while (!bombs.isEmpty()) {
					Coordinate bomb = bombs.poll();
					int rangeMax = map[bomb.row][bomb.col] - 1;
//					System.out.println(bomb);
					map[bomb.row][bomb.col] = 0;
					for (int k = 0; k < upDown.length; k++) {
						for (int range = 1; range <= rangeMax; range++) {
							Coordinate next = new Coordinate(bomb.row + upDown[k] * range,
									bomb.col + leftRight[k] * range);
							if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length
									|| map[next.row][next.col] == 0) {
								continue;
							}
							bombs.add(next);
						}
					}
				}
//				printArr(map);
//				System.out.println("down");
				for (int col = 0; col < map[0].length; col++) {
					Queue<Integer> q = new LinkedList<>();
					for (int row = map.length - 1; row >= 0; row--) {
						if (map[row][col] > 0) {
							q.add(map[row][col]);
						}
					}

					if (q.isEmpty()) {
						topBlocks[col] = -1;
					} else {
						int rowIdx = map.length - 1;
						while (!q.isEmpty()) {
							map[rowIdx][col] = q.poll();
							topBlocks[col] = rowIdx;
							rowIdx--;
						}
						for (int r = rowIdx; r >= 0; r--) {
							map[r][col] = 0;
						}
					}
				}
//				System.out.println(cnt);
//				System.out.println(top);
//				printArr(map);
				explosion(cnt + 1, map, topBlocks);
			}
		}
	}

	public static boolean isHasTop(int arr[]) {
		for (int val : arr) {
			if (val >= 0) {
				return true;
			}
		}
		return false;
	}

	public static int[][] copyArr(int arr[][]) {
		int temp[][] = new int[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				temp[i][j] = arr[i][j];
			}
		}

		return temp;
	}

	public static int[] copyArr(int arr[]) {
		int temp[] = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}

		return temp;
	}
}

class Coordinate {
	int row;
	int col;

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}