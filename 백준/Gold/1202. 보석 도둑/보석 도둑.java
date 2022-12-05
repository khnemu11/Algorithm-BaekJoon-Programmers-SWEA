import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int N = row[0];
		int K = row[1];
		ArrayList<Jewel> jewelList = new ArrayList<>();

		int[] bags = new int[K];
		for (int i = 0; i < N; i++) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			jewelList.add(new Jewel(row[1], row[0]));
		}
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.valueOf(br.readLine());
		}
		Collections.sort(jewelList);
		Arrays.sort(bags);
		long sum = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int jewelStart = 0;
		for (int bag : bags) {
			for (int i = jewelStart; i < jewelList.size(); i++) {
				Jewel curr = jewelList.get(i);
				if (bag < curr.weight) {
					break;
				}
				pq.add(curr.cost);
				jewelStart++;
			}
			if (!pq.isEmpty()) {
				sum += pq.poll();
			}
		}

		bw.write(String.valueOf(sum));
		bw.newLine();
		bw.flush();
	}
}

class Jewel implements Comparable<Jewel> {
	int cost;
	int weight;

	public Jewel(int cost, int weight) {
		this.cost = cost;
		this.weight = weight;
	}

	@Override
	public int compareTo(Jewel o) {
		if (o.weight == this.weight) {
			return this.cost - o.cost;
		}

		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "Jewel [cost=" + cost + ", weight=" + weight + "]";
	}
}