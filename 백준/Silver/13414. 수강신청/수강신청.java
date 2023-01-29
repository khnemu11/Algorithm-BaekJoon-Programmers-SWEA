import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.valueOf(st.nextToken());
		int L = Integer.valueOf(st.nextToken());
		Queue<String> q = new LinkedList<>();
		Map<String, Integer> countMap = new HashMap<>();
		while (L-- > 0) {
			String student = br.readLine();
			q.add(student);
			countMap.put(student, countMap.getOrDefault(student, 0) + 1);
		}
		StringBuilder sb = new StringBuilder();
		int count = 0;

		while (!q.isEmpty()) {
			if (count == K) {
				break;
			}
			String student = q.poll();
			countMap.put(student, countMap.get(student) - 1);

			if (countMap.get(student) == 0) {
				sb.append(student + "\n");
				count++;
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
