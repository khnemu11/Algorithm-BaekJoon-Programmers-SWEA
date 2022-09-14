import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		int arr[] = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr[0] = Integer.valueOf(st.nextToken());

		int start = 0;
		int end = 0;

		for (int i = 1; i < N; i++) {
			int curr = Integer.valueOf(st.nextToken());

			if (arr[end] < curr) {
				end++;
				arr[end] = curr;
			} else {
				int l = start;
				int r = end;

				while (l < r) {
					int mid = (l + r) / 2;
					if (arr[mid] < curr) {
						l = mid+1;
					} else {
						r = mid;
					} 
				}
				arr[l] = curr;
			}
		}
		bw.write(String.valueOf(end+1));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
