import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 과정
 * 
 * 모든 노드는 노드와 연결되어 있음
 * ->그래프 표현 어려움
 * 
 * 현재위치와 거리가 1000(가능한 것)인 편의점을 지속적으로 뽑아내면서 페스티벌 위치까지 나오면 성공
 * 이때 가능한 편의점은 다시 방문할 필요가 없으므로 편의점 후보 집단에서 제거 => 큐로 구현
 * 안나오면 실패
 * 
 * 시간복잡도 = 100*100 = 100,000 <1억
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine()); // 테스트 케이스 개수

		while (T-- > 0) {
			int n = Integer.valueOf(br.readLine()); // 편의점의 개수
			StringTokenizer st = new StringTokenizer(br.readLine());
			Coordinate start = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));

			Queue<Coordinate> stores = new LinkedList<>(); // 편의점 및 페스티벌 큐

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				stores.add(new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken())));
			}

			st = new StringTokenizer(br.readLine());
			Coordinate goal = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
			stores.add(goal);

			Queue<Coordinate> candidates = new LinkedList<>(); // 도달할 수 있는 편의점 후보 큐 배열
			candidates.add(start);
			boolean find = false; // 페스티벌에 갈 수 있는지 판별하는 변수

			while (!candidates.isEmpty()) { // 출발할 수 있는 편의점이 존재하는 한 도달할 수 있는 편의점 탐색
				Coordinate curr = candidates.poll();

				if (curr.isEquals(goal)) { // 목적지에 도달했다면 반복문 탈출
					find = true;
					break;
				}

				int size = stores.size(); // 편의점의 개수만큼 탐색

				while (size-- > 0) {
					Coordinate store = stores.poll(); // 현재 편의점

					if (Math.abs(curr.x - store.x) + Math.abs(curr.y - store.y) <= 1000) { // 거리 이내(1000)에 들어가는 편의점이면
																							// 편의점 큐에서 제거 및 후보 편의점 등록
						candidates.add(store);
					} else { // 다시 편의점 후보에 등록
						stores.add(store);
					}
				}
			}

			if (find) {
				bw.write("happy\n");
			} else {
				bw.write("sad\n");
			}
		}

		bw.flush();
	}
}

class Coordinate {
	int x;
	int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isEquals(Coordinate o) {
		if (o.x != x) {
			return false;
		}
		if (o.y != y) {
			return false;
		}

		return true;
    }
}
