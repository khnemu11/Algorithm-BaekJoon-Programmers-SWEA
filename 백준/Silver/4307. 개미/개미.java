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

		int T = Integer.valueOf(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int length = Integer.valueOf(st.nextToken());
			int size = Integer.valueOf(st.nextToken());
			int arr[] = new int[size];

			for (int i = 0; i < size; i++) {
				arr[i] = Integer.valueOf(br.readLine());
			}

			int left = Arrays.stream(arr).min().getAsInt();
			int right = Arrays.stream(arr).max().getAsInt();

			int max = Math.max(length - left, right);

			int leftMid = 1;
			int	rightMid = length;
			
			for (int i = 0; i < size; i++) {
				if (arr[i] >= length / 2) {
					rightMid = Math.min(arr[i], rightMid);
				} else {
					leftMid = Math.max(arr[i], leftMid);
				}
			}
			
			int min = Math.max(length - rightMid, leftMid);
			
			StringBuilder sb = new StringBuilder();

			sb.append(min);
			sb.append(" ");
			sb.append(max);

			bw.write(sb.toString());
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
}