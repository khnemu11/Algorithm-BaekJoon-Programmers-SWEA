import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 
 * 도시와 도시간의 최소거리 -> 다익스트라 알고리즘
 * 
 * 특정 도시와 모든 도시와의 왕복거리를 재야함.
 * 역방향 그래프를 이용해 도착 도시(N)를 시작 도시로 설정하면 정방향 그래프에서 특정 도시(N)까지의 최소 거리를 구할 수 있다.
 * -> 다익스트라 2번으로 왕복 비용 계산
 * 
 * 도시의 최대 개수 : 600
 * 간선의 최대 개수 : 1400
 * 모든 함수 호출 최대 개수 : 50
 * 테스트 케이스 개수 : 25개
 * 
 * O(n) = 600*log(1400) * 50 * 25 < 3억 
 * 
 * */

class UserSolution {
	Map<Integer, Integer> cityToIdxMap; // 도시 이름 -> 인덱스를 연결하는 map
	List<City> graph[]; // 정방향 그래프
	List<City> reverse[]; // 역방향 그래프
	int MAX_CITY = 600; // 도시 최대 개수
	int cityNum = 0; // 도시 개수
	int MAX_COST = 100; // 최대 간선 비용

	public int init(int N, int sCity[], int eCity[], int mCost[]) {
		cityToIdxMap = new HashMap<>();
		graph = new ArrayList[MAX_CITY + 1];
		reverse = new ArrayList[MAX_CITY + 1];

		// 도시의 최대 개수만큼 생성
		for (int i = 0; i <= MAX_CITY; i++) {
			graph[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			// 처음 등장하는 시작 or 도착 도시인경우 해당 도시에 인덱스(현재 도시 개수)를 부여
			// 현재 도시 개수를 인덱스로 부여해야 도시의 개수의 크기의 배열 그래프 생성 가능
			if (cityToIdxMap.get(sCity[i]) == null) {
				cityToIdxMap.put(sCity[i], cityToIdxMap.size());
			}
			if (cityToIdxMap.get(eCity[i]) == null) {
				cityToIdxMap.put(eCity[i], cityToIdxMap.size());
			}
			// 그래프에 간선 추가
			add(sCity[i], eCity[i], mCost[i]);
		}
		// 도시 인덱스의 개수 = 도시의 개수
		cityNum = cityToIdxMap.size();

		return cityNum;
	}

	public void add(int sCity, int eCity, int mCost) {
		graph[cityToIdxMap.get(sCity)].add(new City(cityToIdxMap.get(eCity), mCost));
		reverse[cityToIdxMap.get(eCity)].add(new City(cityToIdxMap.get(sCity), mCost));
		return;
	}

	public int cost(int mHub) {
		// 최소 비용 = (모든 도시 -> mhub) + (mhub -> 모든 도시)
		return getMinCostSum(reverse, mHub) + getMinCostSum(graph, mHub);
	}

	// 다익스트라를 이용해 특정 도시 -> 모든 도시 간의 최단거리를 구하는 메소드 (그래프, 시작 도시 이름)
	public int getMinCostSum(List<City> graph[], int start) {
		int distance[] = new int[cityNum];
		// 최대 비용 + 1만큼 INF설정
		Arrays.fill(distance, MAX_COST * cityNum + 1);
		distance[cityToIdxMap.get(start)] = 0;
		PriorityQueue<City> pq = new PriorityQueue<>();
		pq.add(new City(cityToIdxMap.get(start), 0));

		int visited = 0;	//방문한 도시의 개수

		while (!pq.isEmpty()) {
			if (visited == cityNum) {
				break;
			}

			City curr = pq.poll();

			if (distance[curr.idx] < curr.cost) {
				continue;
			}

			visited++;

			for (City end : graph[curr.idx]) {
				if (distance[end.idx] > distance[curr.idx] + end.cost) {
					distance[end.idx] = distance[curr.idx] + end.cost;
					pq.add(new City(end.idx, distance[end.idx]));
				}
			}
		}

		// 모든 도시의 최단거리의 합을 리턴
		return Arrays.stream(distance).sum();
	}
}

class City implements Comparable<City> {
	int idx;
	int cost;

	public City(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(City o) {
		return this.cost - o.cost;
	}
}
