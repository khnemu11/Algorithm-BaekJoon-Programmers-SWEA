import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//a*알고리즘을 활용하여 현재까지 걸린 횟수와 앞으로 남은 횟수를 더해서 가장 짧은것을 우선수위 큐로 선택해 다음 경로로 선택하는 것을 이용함
		//이때 상하좌우는 상하, 좌우 2개의 배열을 이용해서 반복문을 통해 현재 좌표에서 더하는 방식이 제일 깔끔함
		//벽을 부순 상태에서 탐색하는 것과 안 부순 상태에서 탐색하는 것은 경우가 다르기 때문에 이미 탐색한 좌표인지 확인하기 위해선 2개의 배열이 필요함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] leftRight = { 0, 0, -1, 1 };
		int[] upDown = { -1, 1, 0, 0 };

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		boolean[][][] visited = new boolean[n][m][2];
		char[][] map = new char[n][m];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		Queue<Coordinate> queue = new PriorityQueue<>();
		queue.add(new Coordinate(0, 0, 1, false));

		while (!queue.isEmpty()) {
			Coordinate curr = queue.poll();

			if (curr.row == n - 1 && curr.column == m - 1) {
				bw.write(String.valueOf(curr.count));
				bw.newLine();
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nextRow = curr.row + leftRight[d]; //다음 좌표를 상하좌우 배열을 통해 구하는 부분 
				int nextCol = curr.column + upDown[d];

				if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) //좌표를 벗어난 경우
					continue;

				if (map[nextRow][nextCol] == '0') { // 벽이 아닌 경우
					if (!curr.destroyed && !visited[nextRow][nextCol][0]) { //벽이 아닌데 아직 벽을 부수지 않은 경우
						Coordinate next = new Coordinate(nextRow, nextCol, curr.count+1, false);
						next.cost = n - next.row + m - next.column + next.count;
						queue.add(next);
						visited[nextRow][nextCol][0] = true;
					} else if (curr.destroyed && !visited[nextRow][nextCol][1]) {  //벽이 아닌데 벽을 이미 부순 경우
						Coordinate next = new Coordinate(nextRow, nextCol, curr.count+1, true);
						next.cost = n - next.row + m - next.column + next.count;
						queue.add(next);
						visited[nextRow][nextCol][1] = true;
					}

				} else if (map[nextRow][nextCol] == '1') { // 벽인경우
					if (!curr.destroyed) { //벽인데 아직 벽을 안부순 경우
						Coordinate next = new Coordinate(nextRow, nextCol, curr.count+1, true);
						next.cost = n - next.row + m - next.column + next.count;
						queue.add(next);
						visited[nextRow][nextCol][1] = true;
					}
				}
			}
			if (queue.isEmpty()) {
				bw.write("-1");
				bw.newLine();
				break;
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class Coordinate implements Comparable<Coordinate> {
	int row;
	int column;
	int count;
	int cost;
	boolean destroyed;

	public Coordinate(int row, int column, int count, boolean destroyed) {
		this.row = row;
		this.column = column;
		this.count = count;
		this.destroyed = destroyed;
	}

	@Override
	public int compareTo(Coordinate o) {
		return this.cost - o.cost;
	}
}
