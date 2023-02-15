import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
	구현 문제 이므로 각 조건에 맞추어서 구현
*/


public class Main {
	static int arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		int R = Integer.valueOf(st.nextToken());
		arr = new int[height][width];

		for (int i = 0; i < height; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		}
		
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int command = Integer.valueOf(st.nextToken());

			if (command == 1) {
				swapUpDown();
			} else if (command == 2) {
				swapLeftRight();
			} else if (command == 3) {
				leftRotate();
			} else if (command == 4) {
				rightRotate();
			} else if (command == 5) {
				subRightRotate();
			} else if (command == 6) {
				subLeftRotate();
			}

		}

		for (int i = 0; i < arr.length; i++) { //결과값 출력
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j] + " ");
			}
			bw.write(sb.deleteCharAt(sb.length() - 1).toString() + "\n");
		}

		bw.flush();
	}

	public static void swapUpDown() {	//위아래 바꾸기
		for (int r = 0; r < arr.length / 2; r++) {
			for (int c = 0; c < arr[0].length; c++) {
				Coordinate up = new Coordinate(r, c);
				Coordinate down = new Coordinate(arr.length - (r + 1), c);
				int temp = arr[up.row][up.col];
				arr[up.row][up.col] = arr[down.row][down.col];
				arr[down.row][down.col] = temp;
			}
		}

	}

	public static void swapLeftRight() {//왼쪽 오른쪽 바꾸기
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[0].length / 2; c++) {
				Coordinate left = new Coordinate(r, c);
				Coordinate right = new Coordinate(r, arr[0].length - (c + 1));
				int temp = arr[left.row][left.col];
				arr[left.row][left.col] = arr[right.row][right.col];
				arr[right.row][right.col] = temp;
			}
		}
	}

	public static void leftRotate() { //왼쪽 돌리기
		int temp[][] = new int[arr[0].length][arr.length];
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[0].length; c++) {
				temp[c][temp[0].length - (r + 1)] = arr[r][c];
			}
		}
		copy(temp);
	}

	public static void rightRotate() { //오른쪽 돌리기
		int temp[][] = new int[arr[0].length][arr.length];
		for (int c = arr[0].length - 1; c >= 0; c--) {
			for (int r = 0; r < arr.length; r++) {
				temp[(arr[0].length - 1) - c][r] = arr[r][c];
			}
		}
		copy(temp);
	}

	public static void subRightRotate() { //부분 오른쪽 돌리기
		int initRow[] = { 0, 0, arr.length / 2, arr.length / 2 }; //각 부분 영역별 초기 좌표를 설정해서 반복문으로 4구역 루프
		int initCol[] = { 0, arr[0].length / 2, arr[0].length / 2, 0 }; //각 다음 좌표 이동 값
		int upDown[] = { 0, arr.length / 2, 0, -arr.length / 2 };
		int leftRight[] = { arr[0].length / 2, 0, -arr[0].length / 2, 0 };

		int temp[][] = new int[arr.length][arr[0].length];

		for (int k = 0; k < upDown.length; k++) {
			Coordinate start = new Coordinate(initRow[k], initCol[k]);
			for (int r = start.row; r < start.row + arr.length / 2; r++) {
				for (int c = start.col; c < start.col + arr[0].length / 2; c++) {
					temp[r + upDown[k]][c + leftRight[k]] = arr[r][c];
				}
			}
		}

		copy(temp);
	}

	public static void subLeftRotate() { //부분 왼쪽 돌리기
		int initRow[] = { 0, arr.length / 2, arr.length / 2, 0 };
		int initCol[] = { 0, 0, arr[0].length / 2, arr[0].length / 2 };
		int upDown[] = { arr.length / 2, 0, -arr.length / 2, 0 };
		int leftRight[] = { 0, arr[0].length / 2, 0, -arr[0].length / 2 };

		int temp[][] = new int[arr.length][arr[0].length];

		for (int k = 0; k < upDown.length; k++) {
			Coordinate start = new Coordinate(initRow[k], initCol[k]);
			for (int r = start.row; r < start.row + arr.length / 2; r++) {
				for (int c = start.col; c < start.col + arr[0].length / 2; c++) {
					temp[r + upDown[k]][c + leftRight[k]] = arr[r][c];
				}
			}
		}

		copy(temp);
	}

	public static void copy(int temp[][]) {
		arr = new int[temp.length][temp[0].length];
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[0].length; c++) {
				arr[r][c] = temp[r][c];
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
}