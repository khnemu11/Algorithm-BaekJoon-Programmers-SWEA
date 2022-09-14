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
		int K = Integer.valueOf(st.nextToken());
		int N = Integer.valueOf(st.nextToken());
		int lines[] = new int[K];

		long start = 1;
		long end = Integer.MAX_VALUE + 1L;

		for (int i = 0; i < K; i++) {
			lines[i] = Integer.valueOf(br.readLine());
		}

		while (start+1 != end) {
			long mid = (start + end) / 2;
			boolean possible = false;
			long count = 0;
			for (int i = 0; i < K; i++) {
				count += (long)lines[i] / mid;
				if (count >= N) {
					possible = true;
					break;
				}
			}

			if (possible) {
				start = mid;
			}
			else {
				end = mid;
			}
//			System.out.println("start "+start+" end : "+end);
		}
		bw.write(String.valueOf(start));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
