import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	가로 세로 길이가 100이하로 충분히 작음
	100 x 100 방문 배열로 직사각형의 좌표를 방문처리하고 dfs영역 구하기
*/
public class Main {
	static boolean visited[][];
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };
	static int size = 0;
	static int M, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		int K = Integer.parseInt(st.nextToken()); // 직사각형 개수
		visited = new boolean[M][N];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());

			Coordinate leftBottom = new Coordinate(row, col); // 왼쪽 아래 좌표

			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());

			Coordinate rightTop = new Coordinate(row, col); // 오른쪽 위 좌표

			for (int i = leftBottom.row; i < rightTop.row; i++) {
				for (int j = leftBottom.col; j < rightTop.col; j++) {
					visited[i][j] = true;
				}
			}
		}
		
		PriorityQueue<Integer> areaPQ = new PriorityQueue<>(); //영역을 작은순서로 정렬
		
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				if (visited[i][j]) {
					continue;
				}
				size = 0;
				move(new Coordinate(i, j));
				areaPQ.add(size);
			}
		}

		bw.write(areaPQ.size() + "\n");
		StringBuilder sb = new StringBuilder();
		while (!areaPQ.isEmpty()) {
			sb.append(areaPQ.poll() + " ");
		}
		bw.write(sb.deleteCharAt(sb.length() - 1).toString() + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void move(Coordinate curr) { // 방문하지 않은 알파벳으로 이동하는 메소드
		visited[curr.row][curr.col] = true;
		size++;

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

			if (next.row < 0 || next.col < 0 || next.row >= M || next.col >= N) {
				continue;
			}
			if (visited[next.row][next.col]) {
				continue;
			}
			move(next);
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