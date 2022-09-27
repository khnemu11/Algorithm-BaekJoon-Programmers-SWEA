import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());
		int k = Integer.valueOf(br.readLine());

		boolean visited[] = new boolean[100001];
		List<Integer> primeList = new ArrayList<>();

		for (int i = 2; i <= n; i++) {
			if (!visited[i]) {
				primeList.add(i);
				for (int j = i * 2; j <= n; j = j + i) {
					visited[j] = true;
				}
			}
		}
		int count = 1;
		for (int i = 2; i <= n; i++) {
			int maxIndex = primeList.size() - 1;
			while (i % primeList.get(maxIndex) != 0) {
				maxIndex--;
			}
			if (primeList.get(maxIndex) <= k) {
				count++;
			}
		}

		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
}
