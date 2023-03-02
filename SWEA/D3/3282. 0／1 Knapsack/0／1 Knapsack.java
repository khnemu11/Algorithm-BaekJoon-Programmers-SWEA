import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
	물건 개수 : 100
	가방 최대 무게 : 1000
	dp로 냅색 진행 시 최대 시간 복잡도 = 100*1000 = 100,000
    무게 별 가치가 가장 높은 물건을 고르는 것이 더 좋다고 판단
*/
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.valueOf(st.nextToken()); // 물건 개수
			int K = Integer.valueOf(st.nextToken()); // 가방 무게

			int dp[][] = new int[N + 1][K + 1];
			PriorityQueue<Product> pq = new PriorityQueue<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				int weight = Integer.valueOf(st.nextToken());
				int cost = Integer.valueOf(st.nextToken());
				pq.add(new Product(weight, cost));	//가치 / 무게 순으로 정렬
			}

			for (int i = 1; i < dp.length; i++) {
				Product product = pq.poll();	
                
				for (int bagWeight = 1; bagWeight < dp[0].length; bagWeight++) {
					if (product.weight <= bagWeight	//현재 가방의 무게가 현재 물건을 담을 수 있고 이전 물건을 담았을 때 최대의 가치 값보다 현재 물건을 담았을 때의 가치 값이 더 큰경우 
							&& dp[i - 1][bagWeight] < dp[i - 1][bagWeight - product.weight] + product.cost) {
						dp[i][bagWeight] = dp[i - 1][bagWeight - product.weight] + product.cost;
					} else {
						dp[i][bagWeight] = dp[i - 1][bagWeight];
					}
				}
			}
			System.out.println("#"+testcase+" "+dp[N][K]);
		}
	}
}

class Product implements Comparable<Product> {
	int weight;
	int cost;

	public Product(int weight, int cost) {
		this.weight = weight;
		this.cost = cost;
	}

	public int compareTo(Product o) {
		if (cost / (double) weight > o.cost / (double) o.weight) {
			return -1;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return "Product [weight=" + weight + ", cost=" + cost + "]";
	}

}