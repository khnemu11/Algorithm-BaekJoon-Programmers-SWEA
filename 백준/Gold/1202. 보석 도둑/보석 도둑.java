import java.io.*;
import java.util.*;

/*
	풀이 알고리즘
	
	무게가 오름차순으로 보석을 정렬
	무게를 오름차순으로 가방을 정렬
	
	현재 가방에 넣을 수 있는 보석을 전부 가격 maxHeap의 우선순위큐에 저장
	현재 가격 중 가장 높은 것 꺼내기
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 보석 개수
		int K = Integer.valueOf(st.nextToken()); // 가방 개수

		PriorityQueue<Jewel> weightQueue = new PriorityQueue<>((x, y) -> (x.weight - y.weight));
		PriorityQueue<Jewel> valQueue = new PriorityQueue<>((x, y) -> (y.val - x.val));
		PriorityQueue<Integer> bagQueue = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			int val = Integer.valueOf(st.nextToken());
			weightQueue.add(new Jewel(val, weight));
		}

		for (int i = 0; i < K; i++) {
			bagQueue.add(Integer.valueOf(br.readLine()));
		}

		long sum = 0;
		while (!bagQueue.isEmpty()) {
			int bagSize = bagQueue.poll();
			while (!weightQueue.isEmpty() && weightQueue.peek().weight <= bagSize) {
				valQueue.add(weightQueue.poll());
			}
			if (!valQueue.isEmpty()) {
				sum += valQueue.poll().val;
			}
		}

		System.out.println(sum);
	}
}

class Jewel {
	int val;
	int weight;

	public Jewel(int val, int weight) {
		this.val = val;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Jewel [val=" + val + ", weight=" + weight + "]";
	}

}