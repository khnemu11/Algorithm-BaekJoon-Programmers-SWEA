import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//package defalut;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
///*
// * 풀이 알고리즘
// * 
// *	0) 문제 목표
// *		바뀐 등수를 출력할 수 있으면 출력하고 못하면 impossible을 출력
// *
// *  1) 알고리즘
// *     등수의 오름차순 -> 등수 낮은 쪽을 부모, 높은 쪽을 자식으로 가진 그래프
// *     등수가 바뀌면 기존의 부모 관계를 뒤집는다. -> 뒤집기 편하게 하기 위해 NxN으로 그래프 구현
// *     N이 최대 500이므로 O(V+E)인 O(25500)
// *     -> 제한 시간내에 가능
// * 
// *       
// */
//public class Main {
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		int T = Integer.valueOf(br.readLine());
//
//		for (int testcase = 1; testcase <= T; testcase++) {
//			int N = Integer.valueOf(br.readLine());
//			int lastYearParantNum[] = new int[N + 1];
//			int thisYearParantNum[] = new int[N + 1];
//			boolean lastYearGraph[][] = new boolean[N + 1][N + 1];
//			boolean thisYearGraph[][] = new boolean[N + 1][N + 1];
//
//			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
//
//			int parent = Integer.valueOf(stringTokenizer.nextToken());
//			int cnt = 1;
//			while (stringTokenizer.hasMoreTokens()) {
//				int child = Integer.valueOf(stringTokenizer.nextToken());
//				lastYearGraph[parent][child] = true;
//				thisYearGraph[parent][child] = true;
//				lastYearParantNum[child] += cnt;
//				parent = child;
//				cnt++;
//			}
//
//			int M = Integer.valueOf(br.readLine());
//
//			for (int i = 0; i < M; i++) {
//				stringTokenizer = new StringTokenizer(br.readLine());
//				int a = Integer.valueOf(stringTokenizer.nextToken());
//				int b = Integer.valueOf(stringTokenizer.nextToken());
//
//				if (lastYearGraph[a][b]) {
//					thisYearGraph[a][b] = false;
//					thisYearGraph[b][a] = true;
//					thisYearParantNum[b]--;
//					thisYearParantNum[a]++;
//				} else {
//					thisYearGraph[b][a] = false;
//					thisYearGraph[a][b] = true;
//					thisYearParantNum[b]++;
//					thisYearParantNum[a]--;
//				}
//			}
//
//			Queue<Integer> queue = new LinkedList<>();
//			System.out.println(Arrays.toString(thisYearParantNum));
//			for (int i = 1; i < thisYearParantNum.length; i++) {
//				if (thisYearParantNum[i] == 0) {
//					queue.add(i);
//				}
//			}
//
//			boolean isValid = true;
//			StringBuilder sBuilder = new StringBuilder();
//			while (!queue.isEmpty()) {
//				if (queue.size() > 1) {
//					isValid = false;
//					break;
//				}
//				int curr = queue.poll();
//
//				sBuilder.append(curr + " ");
//
//				for (int i = 1; i < thisYearGraph[0].length; i++) {
//					if (thisYearGraph[curr][i]) {
//						thisYearParantNum[i]--;
//
//						if (thisYearParantNum[i] == 0) {
//							queue.add(i);
//						}
//					}
//				}
//			}
//			System.out.println(sBuilder.deleteCharAt(sBuilder.length() - 1).toString());
//			if (isValid) {
//				bw.write(sBuilder.deleteCharAt(sBuilder.length() - 1).toString() + "\n");
//			} else {
//				bw.write("IMPOSSIBLE\n");
//			}
//		}
//
//		bw.flush(); // 결과 출력
//		br.close();
//		bw.close();
//	}
//}
public class Main {
	static ArrayList<Coordinate> oneList = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	static int count[] = { 0, 5, 5, 5, 5, 5 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int map[][] = new int[10][10];
		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if (map[i][j] == 1) {
					oneList.add(new Coordinate(i, j));
				}
			}
		}

		select(0, 0, map);
		if (min == Integer.MAX_VALUE) {
			bw.write("-1\n");
		} else {
			bw.write(min + "\n");
		}

		bw.close();
		br.close();
	}

	public static void select(int idx, int cnt, int oriMap[][]) {
		if (cnt > 25) {
			return;
		} else if (idx >= oneList.size()) {
			min = Math.min(min, cnt);
			return;
		}
		Coordinate start = oneList.get(idx);

		if (oriMap[start.row][start.col] == 0) {
			select(idx + 1, cnt, oriMap);
		} else {
			for (int length = 1; length <= 5; length++) {
				if (count[length] <= 0) {
					continue;
				}

				if (!canAttach(start, length, oriMap)) {
//					System.out.println("cannont " + length + " size at " + start);
					break;
				}
				int map[][] = attach(start, length, oriMap);
//				printArr(map);
				count[length]--;
				select(idx + 1, cnt + 1, map);
				count[length]++;
			}
		}
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

	public static int[][] copyArr(int map[][]) {
		int temp[][] = new int[map.length][map[0].length];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	public static boolean canAttach(Coordinate start, int length, int map[][]) {
		for (int i = start.row; i < start.row + length; i++) {
			for (int j = start.col; j < start.col + length; j++) {
				if (i < 0 || j < 0 || i >= map.length || j >= map[0].length) {
					return false;
				}
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static int[][] attach(Coordinate start, int length, int map[][]) {
		int temp[][] = new int[map.length][map[0].length];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i >= start.row && i < start.row + length && j >= start.col && j < start.col + length) {
					temp[i][j] = 0;
				} else {
					temp[i][j] = map[i][j];
				}

			}
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

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

}