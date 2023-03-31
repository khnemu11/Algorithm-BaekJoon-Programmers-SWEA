import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
	풀이 과정
	
	0,0은 언제나 공기층
	0,0부터 시작해서 처음 닿는 치즈(1)까지 이동해서 닿은 치즈 녹이기
	닿은 치즈만 녹이고 다시 0,0부터 닿는 치즈 구하기
	총 닿는 회수 구하기==시간
	
	이때 다 없어지기 직전의 치즈 개수가 필요함
	치즈를 녹이기 전에 최근 치즈의 개수를 저장
*/
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.valueOf(st.nextToken());
		int W = Integer.valueOf(st.nextToken());

		int map[][] = new int[H][W];
		int cheeseCnt = 0;
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if (map[i][j] == 1) {
					cheeseCnt++;
				}
			}
		}

		int dx[] = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };
		int time = 0;
		int beforeCheese = cheeseCnt;
		while (cheeseCnt > 0) {
			Queue<Coordinate> q = new LinkedList<>();
			q.add(new Coordinate(0, 0));

			Queue<Coordinate> targetQ = new LinkedList<>();
			boolean visited[][] = new boolean[map.length][map[0].length];
			while (!q.isEmpty()) {
				Coordinate curr = q.poll();
				visited[curr.row][curr.col] = true;

				if (map[curr.row][curr.col] == 1) {
					targetQ.add(curr);
					continue;
				}
				for (int k = 0; k < dx.length; k++) {
					Coordinate next = new Coordinate(curr.row + dx[k], curr.col + dy[k]);

					if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
						continue;
					}
					if (visited[next.row][next.col]) {
						continue;
					}
					visited[next.row][next.col] = true;
					q.add(next);
				}
			}
			beforeCheese = cheeseCnt;
			while (!targetQ.isEmpty()) {
				Coordinate cheese = targetQ.poll();
				map[cheese.row][cheese.col] = 0;
				cheeseCnt--;
			}

			time++;
		}
		bw.write(time + "\n");
		bw.write(beforeCheese + "\n");
		bw.flush();
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
