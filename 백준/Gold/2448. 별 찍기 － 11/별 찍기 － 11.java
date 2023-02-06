import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 
 * 풀이 알고리즘
   각 윗점을 기준으로 위, 왼쪽아래, 오른쪽 아래로 다시 분할하는 형태로 이루어져 있다.
   높이=3, 밑변의 길이= 5,높이=6, 밑변의 길이=11,높이=12, 밑변의 길이= 23 ... 높이 = N, 밑변의 길이 = N*2-1
   각 점을 담을 배열의 크기는 N X (N*2-1)
   최초 윗점의 좌표는 (0,(N*2-1)/2)
   다음 윗점 : (현재 윗점의 행,현재높이/2),  다음 왼쪽아래 : (현재 윗점의 행-현재높이/2,현재높이/2), 다음 오른쪽 아래 : (현재 윗점의 행+현재높이/2,현재높이/2)
   최소 높이는 3이므로 높이가 3이 될때 기본 삼각형값을 배열에 입력
*/

public class Main {
	static char map[][];
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.valueOf(br.readLine());
		map = new char[N][N * 2 - 1]; //배열의 크기는 점화식에 따라 N X (N*2-1)

		for (int i = 0; i < map.length; i++) {
			Arrays.fill(map[i], ' '); //공백으로 배열 초기화
		}

		draw(new Coordinate(0, map[0].length / 2), N); //시작 윗점은 첫번째 행의 중간 열

		for (int i = 0; i < map.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j]);
			}
			bw.write(sb.toString() + "\n");
		}

		bw.flush();
	}

	public static void draw(Coordinate top, int height) { //윗점을 기준으로 삼각형을 재귀로 탐색하여 그리는 매소드
		if (height == 3) { //최소크기의 삼각형일 때 *입력
			map[top.row][top.col] = '*';
			map[top.row + 1][top.col - 1] = '*';
			map[top.row + 1][top.col + 1] = '*';

			for (int i = -2; i < 3; i++) {
				map[top.row + 2][top.col + i] = '*';
			}
		} else {
			int nextHeight = height / 2; //다음 높이
			draw(top, nextHeight); //윗점
			draw(new Coordinate(top.row + nextHeight, top.col - nextHeight), nextHeight); //왼쪽 아래
			draw(new Coordinate(top.row + nextHeight, top.col + nextHeight), nextHeight); //오른쪽 아래
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