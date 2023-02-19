import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*풀이 알고리즘
 * 
 * N개중에 k개를 골랐을 때의 인구수 == 전체 인구수 - N개중에 N-k개를 고르는 것
 * 최소 고르는 개수 : 1개, 최대 고르는 개수 : N/2개
 * 숫자를 고르고 서로 모두 이어져 있는지 확인->그래프로 관리
 * 고른 숫자 집단과 나머지 숫자 집단이 독립적으로 모두 이어져 있다면 서로의 인구수차이를 구하기
 * 최소값만 최신화
 * */

public class Main {
	static int min = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int N;
	static boolean visited[];
	static int populations[];

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		init();
		pick(new boolean[N + 1], 1, 0);

		if (min == Integer.MAX_VALUE) { // 지역군을 나눌 수 없는 경우
			bw.write("-1\n");
		} else { // 지역군이 나눠질 수 있는 경우
			bw.write(min + "\n");
		}

		bw.flush();
		bw.close();
	}

	public static void init() throws IOException { // 그래프, 지역별 인구를 초기화하는 메소드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(br.readLine());
		populations = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i < populations.length; i++) {	//인구수 초기화
			populations[i] = Integer.valueOf(st.nextToken());
		}

		for (int from = 1; from <= N; from++) {	//그래프 초기화
			st = new StringTokenizer(br.readLine());

			int childNum = Integer.valueOf(st.nextToken());

			while (st.hasMoreTokens()) {
				int to = Integer.valueOf(st.nextToken());
				graph.get(from).add(to);
			}
		}

		br.close();
	}

	public static void pick(boolean city[], int idx, int count) { // (고른 도시,현재 고를 도시 인덱스, 현재까지 고른 도시의 수)
																	// 어떻게 지역구를 나눌 것인지 도시를 선택하고 지역구의 차이를 구하는 메소드
		if (idx == city.length || count == N / 2) { // 도시 선택을 모두 끝냈거나 이미 절반을 선택했을 경우
			if (count == 0) { // 어떤 도시도 선택하지 않았을 경우
				return;
			}
			int aStart = 0;
			int bStart = 0;

			for (int i = 1; i < city.length; i++) { // a와 b지역군 시작점 구하기
				if (aStart > 0 && bStart > 0) {
					break;
				} else if (city[i]) {
					aStart = i;
				} else {
					bStart = i;
				}
			}
			boolean visited[] = new boolean[N + 1];
			goCity(visited, city, aStart); // a지역구 이동
			goCity(visited, city, bStart); // b지역구 이동

			boolean isValid = true;

			for (int i = 1; i < visited.length; i++) { // a지역구와 b지역구가 모두 연결되어 있는지 확인하는 부분
														// 모든 구역을 방문 했다면 연결되어 있는 것
				if (!visited[i]) {
					isValid = false;
					break;
				}
			}

			if (isValid) { // 나눌 수 있는 지역구라면 고른 도시의 합과 고르지 않는 도시의 합의 차이를 구함
				int aSum = 0;
				int bSum = 0;

				for (int i = 1; i < populations.length; i++) {
					if (city[i]) {
						aSum += populations[i];
					} else {
						bSum += populations[i];
					}
				}

				min = Math.min(Math.abs(aSum - bSum), min); // 최소값 최신화
			}
		} else {
			pick(city, idx + 1, count); // 도시 선택
			city[idx] = true;
			pick(city, idx + 1, count + 1); // 도시 미선택
			city[idx] = false;
		}
	}

	public static void goCity(boolean visited[], boolean city[], int start) { // 지역구의 시작점을 따라 연결된 도시를 정해진 지역구만 이동하는 메소드
		visited[start] = true;//방문 처리
		
		for (int to : graph.get(start)) { //현재 도시와 연결된 도시 반복문 
			if (visited[to] || city[start] != city[to]) { //이미 방문했거나 서로 다른 지역구라면 패스
				continue;
			}
			visited[to] = true; //방문 처리
			goCity(visited, city, to); //다음 도시로 이동
		}
	}
}