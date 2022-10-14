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

		int size = Integer.valueOf(br.readLine());
		int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int target = Integer.valueOf(br.readLine());

		Arrays.sort(num);

		int count = 0;

		for (int l = 0; l < size; l++) {
			for (int r = l + 1; r < size; r++) {
				if (num[l] + num[r]==target) {
					count++;
					break;
				}
			}
		}

		bw.write(String.valueOf(count));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}