import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class UserSolution {
	Map<Integer, Integer> cityToIdxMap;
	List<City> graph[];
	List<City> reverse[];
	int MAX_CITY = 600;
	int CITY_NUM = 0;
	int MAX_COST = 100;

	public int init(int N, int sCity[], int eCity[], int mCost[]) {
		cityToIdxMap = new HashMap<>();
		graph = new ArrayList[MAX_CITY + 1];
		reverse = new ArrayList[MAX_CITY + 1];

		for (int i = 0; i <= MAX_CITY; i++) {
			graph[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			if (cityToIdxMap.get(sCity[i]) == null) {
				cityToIdxMap.put(sCity[i], cityToIdxMap.size());
			}
			if (cityToIdxMap.get(eCity[i]) == null) {
				cityToIdxMap.put(eCity[i], cityToIdxMap.size());
			}

			int startIdx = cityToIdxMap.get(sCity[i]);
			int endIdx = cityToIdxMap.get(eCity[i]);
//			System.out.println(
//					sCity[i] + "(" + startIdx + ")" + " -> " + eCity[i] + "(" + endIdx + ")" + " = " + mCost[i]);
			graph[startIdx].add(new City(eCity[i], endIdx, mCost[i]));
			reverse[endIdx].add(new City(sCity[i], startIdx, mCost[i]));
		}
		CITY_NUM = cityToIdxMap.size();
//		System.out.println(Arrays.toString(graph));
//		System.out.println(cityToIdxMap);
		return cityToIdxMap.size();
	}

	public void add(int sCity, int eCity, int mCost) {
		graph[cityToIdxMap.get(sCity)].add(new City(eCity, cityToIdxMap.get(eCity), mCost));
		reverse[cityToIdxMap.get(eCity)].add(new City(sCity, cityToIdxMap.get(sCity), mCost));

		return;
	}

	public int cost(int mHub) {
		return getMinCostSum(reverse, mHub) + getMinCostSum(graph, mHub);
	}

	public int getMinCostSum(List<City> graph[], int start) {
		int distance[] = new int[CITY_NUM];
		Arrays.fill(distance, MAX_COST * CITY_NUM + 1);
		distance[cityToIdxMap.get(start)] = 0;
		PriorityQueue<City> pq = new PriorityQueue<>();
		pq.add(new City(start, cityToIdxMap.get(start), 0));

		int visitedNum = 0;

		while (!pq.isEmpty()) {
			City curr = pq.poll();
			
//			System.out.println(curr);
			
			if (distance[curr.idx] < curr.cost) {
				continue;
			}

			visitedNum++;

			if (visitedNum == CITY_NUM) {
				break;
			}

			for (City end : graph[curr.idx]) {
				if (distance[end.idx] > distance[curr.idx] + end.cost) {
					distance[end.idx] = distance[curr.idx] + end.cost;
					pq.add(new City(end.name, end.idx, distance[end.idx]));
				}
			}
		}

		return Arrays.stream(distance).sum();
	}
}

class City implements Comparable<City> {
	int name;
	int idx;
	int cost;

	public City(int name, int idx, int cost) {
		this.name = name;
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(City o) {
		return this.cost - o.cost;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", idx=" + idx + ", cost=" + cost + "]";
	}
}
