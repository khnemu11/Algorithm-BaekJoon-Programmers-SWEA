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
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int L = Integer.valueOf(st.nextToken());
		boolean broken[] = new boolean[1001];

		st = new StringTokenizer(br.readLine());

		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
			broken[arr[i]] = true;
		}

		int count = 0;
		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			if (!broken[arr[i]]) {
				continue;
			}
			for (int l = 0; l < L; l++) {
				if (arr[i] + l > 1000) {
					break;
				}
				broken[arr[i] + l] = false;
			}
			count++;
		}

		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}