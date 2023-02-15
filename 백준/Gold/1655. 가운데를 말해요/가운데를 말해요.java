import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

/*
	풀이 알고리즘
	
	중앙값 : n의개 수 중 n/2번째 수(만약 짝수라면 n/2와 n/2-1 중 작은 수)
	n=3,4 -> 2번째로 가장 작은수 -> 최소힙 poll 2번
	n=5,6 -> 3번째로 가장 작은수 -> 최소힙 poll 3번
	
	이때 poll을 한 것을 다시 넣어주어야 하는데 n이 100,000이므로 메모리 초과 및 시간초과 우려
	중앙값 보다 작으면서 가장 큰값을 저장하여 앞으로 들어오는 값과 비교해 작으면 최대힙에, 크면 최소힙에 넣어 중앙값 표현
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 중앙값을 표현할 최소힙
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 중앙 값보다 작으면서 가장 큰 수를 표현할 최대힙

		for (int i = 0; i < N; i++) {
			int curr = Integer.valueOf(br.readLine());
			if (minHeap.isEmpty()) {
				minHeap.add(curr);
			} else if (!maxHeap.isEmpty() && curr < minHeap.peek() && curr < maxHeap.peek()) {
				minHeap.add(maxHeap.poll());
				maxHeap.add(curr);
			} else {
				minHeap.add(curr);
			}

			if (i != 0 && i % 2 == 0) { // 3번째 부터 짝수 번째 마다 poll하는 로직
				maxHeap.add(minHeap.poll());
			}
			bw.write(minHeap.peek() + "\n");
		}

		bw.flush();
	}

}