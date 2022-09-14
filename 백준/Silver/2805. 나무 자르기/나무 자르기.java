import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		int trees[] = new int[N];

		int start = 0;
		int end = 2000000000 + 1;
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			trees[i] = Integer.valueOf(st.nextToken());
		}

		while (start + 1 != end) {
			int mid = (start + end) / 2;
			long total = 0L;
			boolean possible = false;
			for (int i = 0; i < N; i++) {
				int rest = trees[i] - mid;
				if (rest <= 0) {
					continue;
				}
				else {
					total+=rest;
				}
				
				if (total >= M) {
					possible = true;
					break;
				}
			}

			if (possible) {
				start = mid;
			} else {
				end = mid;
			}
		}
		bw.write(String.valueOf(start));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
