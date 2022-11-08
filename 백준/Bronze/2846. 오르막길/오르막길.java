import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int length = Integer.valueOf(br.readLine());
		int arr[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int max = 0;
		int start = 0;
		int end = 0;
		for (int i = 1; i < length; i++) {
			if (arr[i] > arr[i - 1]) {
				end = i;
			} else {
				max = Math.max(max, arr[end] - arr[start]);
				start = i;
				end = i;
			}
		}

		max = Math.max(arr[end] - arr[start], max);

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
