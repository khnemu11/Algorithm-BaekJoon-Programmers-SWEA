import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *	풀이 과정
 *	

 * */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int a[] = new int[N];
		int b[] = new int[N];
		int c[] = new int[N];
		int d[] = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
			d[i] = Integer.parseInt(st.nextToken());
		}

		int head[] = new int[N * N];
		int tail[] = new int[N * N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				head[N * i + j] = a[i] + b[j];
				tail[N * i + j] = c[i] + d[j];
			}
		}

		long count = 0;

		Arrays.sort(tail);

		for (int num : head) {

			int l = 0;
			int r = tail.length;
			int target = num * -1;
			int start = 0;

			while (l < r) {
				int mid = (l + r) / 2;

				if (tail[mid] < target) {
					l = mid + 1;
				} else {
					r = mid;
				}
			}
			start = l;
			
			l = 0;
			r = tail.length;
			int end = 0;

			while (l < r) {
				int mid = (l + r) / 2;

				if (tail[mid] <= target) {
					l = mid + 1;
				} else {
					r = mid;
				}
			}
			end = l;
			count = count + (end - start);
		}
		bw.write(count + "\n");
		bw.close();
	}
}