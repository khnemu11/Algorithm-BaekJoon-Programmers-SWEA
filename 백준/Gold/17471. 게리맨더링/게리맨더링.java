import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	최대 고르는 수 : N/2
	조합으로 해결
	먼저 지역구를 고르고 나머지 지역구가 모두 연결되어 있는지 확인
	-> 지역구 2개를 dfs로 돌려 나머지를 모두 방문하는지 확인
	
	걸린시간 : 38분
*/
public class Main {
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int populationArr[];
	static boolean visited[];
	static boolean citiesA[];
	static int total; // 총 인구수
	static int minDiffer = Integer.MAX_VALUE;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		populationArr = new int[N + 1];
		citiesA = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			populationArr[i] = Integer.valueOf(st.nextToken());
			total += populationArr[i];
		}

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int from = 1; from <= N; from++) {
			st = new StringTokenizer(br.readLine());

			int size = Integer.valueOf(st.nextToken());

			for (int i = 0; i < size; i++) {
				int to = Integer.valueOf(st.nextToken());
				graph.get(from).add(to);
			}
		}

		pick(1, 0, 0);

		if (minDiffer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minDiffer);
		}
	}

	public static void pick(int idx, int cnt, int sumA) {
		if (idx >= populationArr.length || cnt >= N / 2) {
			if (cnt == 0) { // 아무것도 고르지 않았으면
				return;
			}
			visited = new boolean[N + 1];
			for (int i = 1; i < citiesA.length; i++) {
				if (citiesA[i]) { // A의 도시인 경우
					makeRouteA(i);
					break;
				}
			}

			for (int i = 1; i < citiesA.length; i++) {
				if (!citiesA[i]) { // B의 도시인 경우
					makeRouteB(i);
					break;
				}
			}

			for (int i = 1; i < visited.length; i++) {
				if (!visited[i]) { // A와 B 둘다 방문하지 않았다면 선거구 분할 불가
//					System.out.println("Cannot");
					return;
				}
			}


			minDiffer = Math.min(minDiffer, Math.abs(sumA  - (total- sumA)));
		} else {
			pick(idx + 1, cnt, sumA);

			citiesA[idx] = true;
			pick(idx + 1, cnt + 1, sumA + populationArr[idx]);
			citiesA[idx] = false;
		}
	}

	public static void makeRouteA(int from) { // A구역 선거구를 만드는 메소드(출발점)
		visited[from] = true;
		for (int to : graph.get(from)) {
			if (!visited[to] && citiesA[to]) {
				visited[to] = true;
				makeRouteA(to);
			}
		}
	}

	public static void makeRouteB(int from) { // B구역 선거구를 만드는 메소드(출발점)
		visited[from] = true;
		for (int to : graph.get(from)) {
			if (!visited[to] && !citiesA[to]) {
				visited[to] = true;
				makeRouteB(to);
			}
		}
	}
}