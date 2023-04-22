import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		HashMap<Character, Integer> map[] = new HashMap[M];
		String[] dnas = new String[N];
		for (int i = 0; i < M; i++) {
			map[i] = new HashMap<>();
		}

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			dnas[i] = str;
			for (int j = 0; j < M; j++) {
				map[j].put(str.charAt(j), map[j].getOrDefault(str.charAt(j), 0) + 1);
			}
		}
		StringBuilder sb = new StringBuilder();
		int sum = 0;

		for (int i = 0; i < M; i++) {
			PriorityQueue<DNA> pq = new PriorityQueue<>();
			for (char key : map[i].keySet()) {
				pq.add(new DNA(key, map[i].get(key)));
			}

			DNA dna = pq.poll();
			sb.append(dna.ch);
			sum = sum + N - dna.hammingDistanceSum;
		}
		bw.write(sb.toString() + "\n");
		bw.write(sum + "\n");
		bw.flush();
	}
}

class DNA implements Comparable<DNA> {
	Character ch;
	int hammingDistanceSum;

	public DNA(Character ch, int sum) {
		this.ch = ch;
		this.hammingDistanceSum = sum;
	}

	public int compareTo(DNA o) {
		if (this.hammingDistanceSum == o.hammingDistanceSum) {
			return this.ch - o.ch;
		}
		return o.hammingDistanceSum - this.hammingDistanceSum;
	}
}